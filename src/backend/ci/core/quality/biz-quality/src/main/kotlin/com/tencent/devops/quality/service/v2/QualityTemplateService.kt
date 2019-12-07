/*
 * Tencent is pleased to support the open source community by making BK-CI 蓝鲸持续集成平台 available.
 *
 * Copyright (C) 2019 THL A29 Limited, a Tencent company.  All rights reserved.
 *
 * BK-CI 蓝鲸持续集成平台 is licensed under the MIT license.
 *
 * A copy of the MIT License is included in this file.
 *
 *
 * Terms of the MIT License:
 * ---------------------------------------------------
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy,
 * modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT
 * LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN
 * NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.tencent.devops.quality.service.v2

import com.tencent.devops.common.api.pojo.Page
import com.tencent.devops.common.api.util.HashUtil
import com.tencent.devops.quality.api.v2.pojo.ControlPointPosition
import com.tencent.devops.quality.api.v2.pojo.RuleIndicatorSet
import com.tencent.devops.quality.api.v2.pojo.RuleTemplate
import com.tencent.devops.quality.api.v2.pojo.op.TemplateData
import com.tencent.devops.quality.api.v2.pojo.op.TemplateIndicatorMap
import com.tencent.devops.quality.api.v2.pojo.op.TemplateUpdateData
import com.tencent.devops.quality.dao.v2.QualityIndicatorDao
import com.tencent.devops.quality.dao.v2.QualityRuleTemplateDao
import com.tencent.devops.quality.dao.v2.QualityTemplateIndicatorMapDao
import org.jooq.DSLContext
import org.jooq.impl.DSL
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class QualityTemplateService @Autowired constructor(
    private val dslContext: DSLContext,
    private val ruleTemplateDao: QualityRuleTemplateDao,
    private val indicatorService: QualityIndicatorService,
    private val controlPointService: QualityControlPointService,
    private val ruleTemplateIndicatorDao: QualityTemplateIndicatorMapDao,
    private val indicatorDao: QualityIndicatorDao
) {

    fun userListIndicatorSet(): List<RuleIndicatorSet> {
        return ruleTemplateDao.listIndicatorSetEnable(dslContext)?.map { record ->
            val indicatorIds = ruleTemplateIndicatorDao.queryTemplateMap(record.id, dslContext)?.map { it.indicatorId }
                ?: listOf()
            val indicators = indicatorService.serviceList(indicatorIds)
            RuleIndicatorSet(
                hashId = HashUtil.encodeLongId(record.id),
                name = record.name,
                desc = record.desc,
                indicators = indicators
            )
        } ?: listOf()
    }

    fun userList(): List<RuleTemplate> {
        val templateList = ruleTemplateDao.listTemplateEnable(dslContext)
        return templateList?.map {
            val indicatorIds = ruleTemplateIndicatorDao.queryTemplateMap(it.id, dslContext)?.map { it.indicatorId }
                    ?: listOf()
            val controlPoint = controlPointService.serviceGet(it.controlPoint)
            val indicators = indicatorService.serviceList(indicatorIds)
            RuleTemplate(
                hashId = HashUtil.encodeLongId(it.id),
                name = it.name,
                desc = it.desc,
                indicators = indicators,
                stage = it.stage,
                controlPoint = it.controlPoint,
                controlPointName = controlPoint?.name ?: "",
                controlPointPosition = ControlPointPosition(it.controlPointPosition),
                availablePosition = listOf(ControlPointPosition("BEFORE"), ControlPointPosition("AFTER"))
            )
        } ?: listOf()
    }

    fun opList(userId: String, page: Int?, pageSize: Int?): Page<TemplateData> {
        val data = ruleTemplateDao.list(userId, page!!, pageSize!!, dslContext).map { record ->
            val templateIndicatorMap = ruleTemplateIndicatorDao.listByTemplateId(record.id, dslContext)
            val indicatorIds = templateIndicatorMap.map { it.indicatorId }.toHashSet()
            val indicatorList = indicatorDao.listByIds(dslContext, indicatorIds)

            val templateIndicatorMaps = templateIndicatorMap.map { it1 ->
                val indicatorInst = indicatorList?.filter { it2 -> it2.id == it1.indicatorId }?.getOrNull(0)
                val indicatorName = if (indicatorInst != null) {
                    "${indicatorInst.elementName}-${indicatorInst.elementDetail}-${indicatorInst.cnName}"
                } else null
                TemplateIndicatorMap(
                    id = it1.id,
                    templateId = it1.templateId,
                    indicatorId = it1.indicatorId,
                    indicatorName = indicatorName,
                    operation = it1.operation,
                    threshold = it1.threshold
                )
            }
            val controlPoint = controlPointService.serviceGetByType(record.controlPoint)
            TemplateData(
                id = record.id,
                name = record.name,
                type = record.type,
                desc = record.desc,
                stage = record.stage,
                elementType = record.controlPoint,
                elementName = controlPoint?.name,
                controlPointPostion = record.controlPointPosition,
                enable = record.enable,
                indicatorNum = templateIndicatorMap.size,
                indicatorDetail = templateIndicatorMaps
            )
        }
        val count = ruleTemplateDao.count(dslContext)
        return Page(page, pageSize, count, data)
    }

    fun opCreate(userId: String, templateUpdateData: TemplateUpdateData): Boolean {
        dslContext.transaction { configuration ->
            val tDSLContext = DSL.using(configuration)
            val templateId = ruleTemplateDao.create(userId, templateUpdateData.templateUpdate, tDSLContext)

            if (templateUpdateData.indicatorDetail != null) {
                templateUpdateData.indicatorDetail!!.forEach { it.templateId = templateId }
            }
            ruleTemplateIndicatorDao.batchCreate(tDSLContext, templateUpdateData.indicatorDetail)
        }
        return true
    }

    fun opDelete(userId: String, id: Long): Boolean {
        logger.info("user($userId) is deleting the rule($id)")
        dslContext.transaction { configuration ->
            val tDSLContext = DSL.using(configuration)
            ruleTemplateDao.delete(userId, id, tDSLContext)
            ruleTemplateIndicatorDao.deleteRealByTemplateId(tDSLContext, id)
        }
        return true
    }

    fun opUpdate(userId: String, id: Long, templateUpdateData: TemplateUpdateData): Boolean {
        logger.info("user($userId) is updating the rule($id)")
        dslContext.transaction { configuration ->
            val tDSLContext = DSL.using(configuration)
            ruleTemplateDao.update(userId, id, templateUpdateData.templateUpdate, tDSLContext)
            ruleTemplateIndicatorDao.deleteRealByTemplateId(tDSLContext, id)
            ruleTemplateIndicatorDao.batchCreate(tDSLContext, templateUpdateData.indicatorDetail)
        }
        return true
    }

    companion object {
        private val logger = LoggerFactory.getLogger(QualityTemplateService::class.java)
    }
}