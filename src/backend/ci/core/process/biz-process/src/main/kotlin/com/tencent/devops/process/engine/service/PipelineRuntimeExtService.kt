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

package com.tencent.devops.process.engine.service

import com.tencent.devops.common.pipeline.enums.BuildStatus
import com.tencent.devops.common.redis.RedisLock
import com.tencent.devops.common.redis.RedisOperation
import com.tencent.devops.process.engine.dao.PipelineBuildHistoryDao
import com.tencent.devops.process.engine.pojo.BuildInfo
import org.jooq.DSLContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PipelineRuntimeExtService @Autowired constructor(
    val redisOperation: RedisOperation,
    val pipelineBuildDao: PipelineBuildHistoryDao,
    val dslContext: DSLContext
) {
    private val nextBuildKey = "pipelineNextQueueInfo:concurrency"
    private val expiredTimeInSeconds = 60L

    fun popNextQueueBuildInfo(pipelineId: String): BuildInfo? {

        val redisLock = RedisLock(redisOperation, "$nextBuildKey:$pipelineId", expiredTimeInSeconds)
        try {
            redisLock.lock()
            val buildInfo = pipelineBuildDao.convert(pipelineBuildDao.getOneQueueBuild(dslContext, pipelineId))
            if (buildInfo != null) {
                pipelineBuildDao.updateStatus(
                    dslContext = dslContext,
                    buildId = buildInfo.buildId,
                    oldBuildStatus = BuildStatus.QUEUE,
                    newBuildStatus = BuildStatus.QUEUE_CACHE
                )
            }
            return buildInfo
        } finally {
            redisLock.unlock()
        }
    }

    fun queueCanPend2Start(pipelineId: String, buildId: String): Boolean {
        val redisLock = RedisLock(redisOperation, "$nextBuildKey:$pipelineId", expiredTimeInSeconds)
        try {
            redisLock.lock()
            val buildRecord = pipelineBuildDao.getOneQueueBuild(dslContext, pipelineId)
            if (buildRecord != null) {
                if (buildId == buildRecord.buildId) {
                    return pipelineBuildDao.updateStatus(
                        dslContext = dslContext,
                        buildId = buildRecord.buildId,
                        oldBuildStatus = BuildStatus.QUEUE,
                        newBuildStatus = BuildStatus.QUEUE_CACHE
                    )
                }
            }
            return false
        } finally {
            redisLock.unlock()
        }
    }
}
