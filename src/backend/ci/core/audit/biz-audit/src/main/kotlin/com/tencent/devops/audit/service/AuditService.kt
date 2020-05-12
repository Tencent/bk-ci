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

package com.tencent.devops.audit.service


import com.tencent.devops.audit.api.pojo.Audit
import com.tencent.devops.audit.api.pojo.AuditInfo
import com.tencent.devops.audit.dao.*
import com.tencent.devops.common.api.exception.ParamBlankException
import com.tencent.devops.common.api.model.SQLPage
import com.tencent.devops.common.api.util.timestamp
import org.jooq.DSLContext
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AuditService @Autowired constructor(
        private val auditDao: AuditDao,
        private val dslContext: DSLContext
) {

     fun createAudit(audit: Audit): Long {
        checkParam(audit)
        val auditId = auditDao.create(
                dslContext = dslContext,
                resourceType = audit.resourceType,
                resourceId = audit.resourceId,
                resourceName = audit.resourceName,
                userId = audit.userId,
                action = audit.action,
                actionContent = audit.actionContent,
                projectId = audit.projectId
        )
        return auditId
    }

    fun userList(
            userId: String?,
            projectId: String,
            reourceType: String,
            status: String?,
            resourceName: String?,
            startTime: String?,
            endTime: String?,
            offset: Int,
            limit: Int
    ): Pair<SQLPage<AuditInfo>, Boolean> {
        val count = auditDao.countByResourceTye(dslContext, userId, projectId, reourceType, resourceName,status,startTime,endTime)
        val auditRecordList = auditDao.listByResourceTye(
                dslContext = dslContext,
                reourceType = reourceType,
                userId = userId,
                projectId = projectId,
                resourceName = resourceName,
                status = status,
                startTime = startTime,
                endTime = endTime,
                offset = offset,
                limit = limit
        )
        val auditRecordMap = auditRecordList.toSet()
        val auditList = auditRecordMap.map {
            var statusStr = ""
            if(it.status.equals("1")){
                statusStr="成功"
            }else{
                statusStr = "失败"
            }
            AuditInfo(
                    status = statusStr,
                    resourceType = it.resourceType,
                    resourceId = it.resourceId,
                    resourceName = it.resourceName,
                    userId = it.userId,
                    updatedTime = it.updatedTime.timestamp(),
                    action = it.action,
                    actionContent = it.actionContent
            )
        }
        return Pair(SQLPage(count, auditList), true)
    }



    private fun checkParam(audit: Audit) {
        if (audit.resourceType.isBlank()) {
            throw ParamBlankException("Invalid resourceType")
        }
        if (audit.resourceId.isBlank()) {
            throw ParamBlankException("Invalid resourceId")
        }
        if (audit.resourceName.isBlank()) {
            throw ParamBlankException("Invalid resourceName")
        }
        if (audit.userId.isBlank()) {
            throw ParamBlankException("Invalid userId")
        }
        if (audit.action.isBlank()) {
            throw ParamBlankException("Invalid action")
        }
        if (audit.actionContent.isBlank()) {
            throw ParamBlankException("Invalid actionContent")
        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(AuditService::class.java)
    }
}
