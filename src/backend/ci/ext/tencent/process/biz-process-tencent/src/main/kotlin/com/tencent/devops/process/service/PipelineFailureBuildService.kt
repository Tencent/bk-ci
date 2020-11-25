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

package com.tencent.devops.process.service

import com.tencent.devops.common.event.pojo.pipeline.PipelineBuildFinishBroadCastEvent
import com.tencent.devops.common.pipeline.enums.BuildStatus
import com.tencent.devops.process.dao.PipelineFailureBuildDao
import com.tencent.devops.process.dao.TencentPipelineBuildDao
import com.tencent.devops.process.engine.service.PipelineRuntimeService
import org.jooq.DSLContext
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.sql.Timestamp

@Service
class PipelineFailureBuildService @Autowired constructor(
    private val pipelineRuntimeService: PipelineRuntimeService,
    private val dslContext: DSLContext,
    private val pipelineFailureBuildDao: PipelineFailureBuildDao,
    private val tencentPipelineBuildDao: TencentPipelineBuildDao
) {

    fun onPipelineFinish(event: PipelineBuildFinishBroadCastEvent) {
        val buildStatus = try {
            BuildStatus.valueOf(event.status)
        } catch (t: Throwable) {
            logger.warn("Fail to convert the build status(${event.status})", t)
            return
        }
        val buildInfo = pipelineRuntimeService.getBuildInfo(event.buildId)
        if (buildInfo == null) {
            logger.warn("[${event.pipelineId}] build (${event.buildId}) is not exist")
            return
        }
        // 去掉Cancel的和人工审核打回的
        if (BuildStatus.isFailure(buildStatus) &&
            buildStatus != BuildStatus.CANCELED &&
            buildStatus != BuildStatus.REVIEW_ABORT) {
            val successBuildHistory = tencentPipelineBuildDao.listSuccessBuild(dslContext,
                event.pipelineId, buildInfo.buildNum)
            // 比当前构建号大的构建已经执行成功了，那就不需要在写入当前的构建了
            if (successBuildHistory.isNotEmpty) {
                logger.info("[${event.projectId}|${event.pipelineId}|${event.buildId}] There are success builds success finished - (${successBuildHistory.map { "${it.buildId}|${it.buildNum}" }.toList()})")
                return
            }
            val startTime = buildInfo.startTime!!
            val endTime = buildInfo.endTime ?: System.currentTimeMillis()
            val count = pipelineFailureBuildDao.insert(
                dslContext = dslContext,
                projectId = event.projectId,
                pipelineId = event.pipelineId,
                buildId = event.buildId,
                buildNum = buildInfo.buildNum,
                startTime = Timestamp(startTime).toLocalDateTime(),
                endTime = Timestamp(endTime).toLocalDateTime()
            )
            logger.info("[${event.projectId}|${event.pipelineId}|${event.buildId}] Insert $count records")
        } else {
            val count = pipelineFailureBuildDao.delete(
                dslContext = dslContext,
                pipelineId = event.pipelineId,
                buildNum = buildInfo.buildNum)
            logger.info("[${event.projectId}|${event.pipelineId}|${event.buildId}] Delete $count records")
        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(PipelineFailureBuildService::class.java)
    }
}
