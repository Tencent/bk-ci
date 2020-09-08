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

package com.tencent.devops.log.cron

import com.tencent.devops.common.api.exception.OperationException
import com.tencent.devops.common.api.util.timestamp
import com.tencent.devops.common.redis.RedisLock
import com.tencent.devops.common.redis.RedisOperation
import com.tencent.devops.log.dao.v2.IndexDaoV2
import com.tencent.devops.log.dao.LogStatusDao
import com.tencent.devops.log.dao.LogTagDao
import org.jooq.DSLContext
import org.jooq.impl.DSL
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

/**
 * 清理`T_LOG_INDICES_V2` `T_LOG_STATUS_V2`三个月前的构建
 */
@Component
class CleanBuildJob @Autowired constructor(
    private val redisOperation: RedisOperation,
    private val dslContext: DSLContext,
    private val indexDaoV2: IndexDaoV2,
    private val logStatusDao: LogStatusDao,
    private val logTagDao: LogTagDao
) {

    private var expireBuildInDay = 30 * 6 // 半年

    @Scheduled(cron = "0 0 3 * * ?")
    fun cleanBuilds() {
        logger.info("Start to clean builds")
        val redisLock = RedisLock(redisOperation, CLEAN_BUILD_JOB_REDIS_KEY, 20)
        try {
            val lockSuccess = redisLock.tryLock()
            if (!lockSuccess) {
                logger.info("The other process is processing clean job")
                return
            }
            clean()
            logger.info("Finish cleaning the builds")
        } catch (t: Throwable) {
            logger.warn("Fail to clean builds", t)
        } finally {
            redisLock.unlock()
        }
    }

    fun expire(expired: Int) {
        logger.info("Update the expired from $expireBuildInDay to $expired")
        if (expired <= 30) {
            logger.warn("The expired is illegal")
            throw OperationException("The expired param is illegal")
        }
        expireBuildInDay = expired
    }

    fun getExpire() = expireBuildInDay

    private fun clean() {
        logger.info("Cleaning the builds")
        while (true) {
            val records = indexDaoV2.listOldestBuilds(dslContext, 10)
            if (records.isEmpty()) {
                logger.info("The record is empty")
                return
            }

            val buildIds = records.filter {
                expire(it.createdTime.timestamp())
            }.map { it.buildId }.toSet()

            if (buildIds.isEmpty()) {
                logger.info("Done cleaning the builds")
                return
            }
            logger.info("The builds[$buildIds] need to be cleaned")
            cleanInDB(buildIds)
        }
    }

    private fun cleanInDB(buildIds: Set<String>) {
        if (buildIds.isEmpty()) {
            return
        }
        dslContext.transaction { configuration ->
            val context = DSL.using(configuration)
            val indexDaoCnt = indexDaoV2.delete(context, buildIds)
            val statusCnt = logStatusDao.delete(context, buildIds)
            val subTagCnt = logTagDao.delete(context, buildIds)
            logger.info("[$indexDaoCnt|$statusCnt|$subTagCnt] Delete the builds")
        }
    }

    private fun expire(timestamp: Long): Boolean {
        return (System.currentTimeMillis() / 1000 - timestamp) >= TimeUnit.DAYS.toSeconds(expireBuildInDay.toLong()) // expire in 90 days
    }

    companion object {
        private val logger = LoggerFactory.getLogger(CleanBuildJob::class.java)
        private const val CLEAN_BUILD_JOB_REDIS_KEY = "log:clean:build:job:lock:key"
    }
}