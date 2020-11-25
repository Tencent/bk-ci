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
 *
 */

package com.tencent.devops.gitci.job

import com.tencent.devops.gitci.dao.GitRequestEventBuildDao
import com.tencent.devops.gitci.dao.GitRequestEventDao
import org.jooq.DSLContext
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class GitRequestEventCleanJob @Autowired constructor(
    private val gitRequestEventDao: GitRequestEventDao,
    private val gitRequestEventBuildDao: GitRequestEventBuildDao,
    private val dslContext: DSLContext
) {
    companion object {
        private val logger = LoggerFactory.getLogger(GitRequestEventCleanJob::class.java)
    }

    /**
     * 每天清理一次一个月前的数据
     */
    @Scheduled(cron = "0 0 4 * * ?")
    fun cleanTable() {
        var endId = Long.MAX_VALUE
        val endTime = LocalDateTime.now().minusMonths(1)

        logger.info("GitRequestEventCleanJob start...")

        while (true) {
            val allIds =
                gitRequestEventDao.getRequestIdByCreateTime(dslContext, endTime = endTime, endId = endId)
            if (allIds.isEmpty()) {
                break
            }
            endId = allIds.last()

            val usedIds =
                gitRequestEventBuildDao.getIdByEventIds(dslContext, allIds).asSequence().toSet()

            val cleanIds = allIds.asSequence().filterNot { usedIds.contains(it) }.toList()
            if (cleanIds.isEmpty()) {
                continue
            }

            val result = gitRequestEventDao.deleteByIds(dslContext, cleanIds)

            logger.info("clean git request event , endId : $endId , allIds size:${allIds.size} , usedIds size : ${usedIds.size} , cleanIds size:${cleanIds.size} , del size:$result")
        }

        logger.info("GitRequestEventCleanJob finish...")
    }
}
