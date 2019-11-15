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

package com.tencent.devops.quality.dao.v2

import com.tencent.devops.common.api.util.PageUtil
import com.tencent.devops.model.quality.tables.TQualityControlPoint
import com.tencent.devops.model.quality.tables.records.TQualityControlPointRecord
import com.tencent.devops.quality.api.v2.pojo.QualityControlPoint
import com.tencent.devops.quality.api.v2.pojo.op.ControlPointUpdate
import org.jooq.DSLContext
import org.jooq.Record1
import org.jooq.Record2
import org.jooq.Result
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class QualityControlPointDao {
    fun get(dslContext: DSLContext, elementType: String): TQualityControlPointRecord? {
        with(TQualityControlPoint.T_QUALITY_CONTROL_POINT) {
            return dslContext.selectFrom(this)
                    .where(ELEMENT_TYPE.eq(elementType))
                    .fetch()
                    .firstOrNull()
        }
    }

    fun list(dslContext: DSLContext): Result<TQualityControlPointRecord>? {
        with(TQualityControlPoint.T_QUALITY_CONTROL_POINT) {
            return dslContext.selectFrom(this)
                    .fetch()
        }
    }

    fun getByType(dslContext: DSLContext, type: String): TQualityControlPointRecord? {
        with(TQualityControlPoint.T_QUALITY_CONTROL_POINT) {
            return dslContext.selectFrom(this)
                    .where(ELEMENT_TYPE.eq(type))
                    .fetch()
                    .firstOrNull()
        }
    }

    fun list(page: Int, pageSize: Int, dslContext: DSLContext): Result<TQualityControlPointRecord> {
        val sqlLimit = PageUtil.convertPageSizeToSQLLimit(page, pageSize)
        with(TQualityControlPoint.T_QUALITY_CONTROL_POINT) {
            return dslContext.selectFrom(this)
                    .orderBy(CREATE_TIME.desc())
                    .limit(sqlLimit.offset, sqlLimit.limit)
                    .fetch()
        }
    }

    fun count(dslContext: DSLContext): Long {
        with(TQualityControlPoint.T_QUALITY_CONTROL_POINT) {
            return dslContext.selectCount().from(this)
                .fetchOne(0, Long::class.java)
        }
    }

    fun update(userId: String, id: Long, controlPointUpdate: ControlPointUpdate, dslContext: DSLContext): Long {
        return with(TQualityControlPoint.T_QUALITY_CONTROL_POINT) {
            val update = dslContext.update(this)

            with(controlPointUpdate) {
                if (!elementType.isNullOrBlank()) update.set(ELEMENT_TYPE, elementType)
                if (!name.isNullOrBlank()) update.set(NAME, name)
                if (!stage.isNullOrBlank()) update.set(STAGE, stage)
                if (!availablePosition.isNullOrBlank()) update.set(AVAILABLE_POSITION, availablePosition)
                if (!defaultPosition.isNullOrBlank()) update.set(DEFAULT_POSITION, defaultPosition)
                if (enable != null) update.set(ENABLE, enable)
            }
            update.set(UPDATE_TIME, LocalDateTime.now())
                    .set(UPDATE_USER, userId)
                    .where(ID.eq(id))
                    .execute().toLong()
        }
    }

    fun getStages(dslContext: DSLContext): Result<Record1<String>> {
        return with(TQualityControlPoint.T_QUALITY_CONTROL_POINT) {
            dslContext.select(STAGE).from(this)
                    .where(STAGE.isNotNull)
                    .groupBy(STAGE)
                    .fetch()
        }
    }

    /**
     * 返回的第一个字段是elementType，第二个字段是elementName
     */
    fun getElementNames(dslContext: DSLContext): Result<Record2<String, String>> {
        return with(TQualityControlPoint.T_QUALITY_CONTROL_POINT) {
            dslContext.select(ELEMENT_TYPE, NAME).from(this)
                    .where(NAME.isNotNull)
                    .groupBy(ELEMENT_TYPE, NAME)
                    .fetch()
        }
    }

    fun serviceCreateOrUpdate(dslContext: DSLContext, userId: String, controlPoint: QualityControlPoint): Int {
        return with(TQualityControlPoint.T_QUALITY_CONTROL_POINT) {
            dslContext.insertInto(this,
                    ELEMENT_TYPE,
                    NAME,
                    STAGE,
                    AVAILABLE_POSITION,
                    DEFAULT_POSITION,
                    ENABLE,
                    CREATE_USER,
                    UPDATE_USER,
                    CREATE_TIME,
                    UPDATE_TIME,
                    ATOM_VERSION,
                    TEST_PROJECT)
                    .values(
                            controlPoint.type,
                            controlPoint.name,
                            controlPoint.stage,
                            controlPoint.availablePos.joinToString(",") { it.name },
                            controlPoint.defaultPos.name,
                            controlPoint.enable,
                            userId,
                            userId,
                            LocalDateTime.now(),
                            LocalDateTime.now(),
                            controlPoint.atomVersion,
                            controlPoint.testProject
                    ).onDuplicateKeyUpdate()
                    .set(ELEMENT_TYPE, controlPoint.type)
                    .set(NAME, controlPoint.name)
                    .set(STAGE, controlPoint.stage)
                    .set(AVAILABLE_POSITION, controlPoint.availablePos.joinToString(",") { it.name })
                    .set(DEFAULT_POSITION, controlPoint.defaultPos.name)
                    .set(ENABLE, controlPoint.enable)
                    .set(UPDATE_USER, userId)
                    .set(UPDATE_TIME, LocalDateTime.now())
                    .set(TEST_PROJECT, controlPoint.testProject)
                    .execute()
        }
    }

    fun cleanTestProject(dslContext: DSLContext, controlPointType: String): Int {
        return with(TQualityControlPoint.T_QUALITY_CONTROL_POINT) {
            dslContext.update(this)
                    .set(TEST_PROJECT, "")
                    .where(ELEMENT_TYPE.eq(controlPointType))
                    .execute()
        }
    }
}