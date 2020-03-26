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

package com.tencent.devops.common.service.gray

import com.google.common.cache.CacheBuilder
import com.tencent.devops.common.redis.RedisOperation
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Component
class Gray {

    @Value("\${project.gray:#{null}}")
    private val grayFlag: String? = null

    var gray: Boolean? = null
    private val redisKey = "project:setting:gray" // 灰度项目列表存在redis的标识key

    private val cache = CacheBuilder.newBuilder()
        .maximumSize(2000)
        .expireAfterWrite(30, TimeUnit.DAYS) // 临时快速上线解决数量过大导致加载慢的问题，FIXME
        .build<String/*Redis Keys*/, Set<String>/*Project Names*/>()

    fun isGray(): Boolean {
        if (gray == null) {
            synchronized(this) {
                if (gray == null) {
                    gray = !grayFlag.isNullOrBlank() && grayFlag!!.toBoolean()
                }
            }
        }
        return gray!!
    }

    fun isGrayProject(projectId: String, redisOperation: RedisOperation): Boolean {
        return grayProjectSet(redisOperation).contains(projectId)
    }

    fun isGrayMatchProject(projectId: String, redisOperation: RedisOperation): Boolean {
        return isGrayMatchProject(projectId, grayProjectSet(redisOperation))
    }

    fun isGrayMatchProject(projectId: String, grayProjectSet: Set<String>): Boolean {
        return isGray() == grayProjectSet.contains(projectId)
    }

    fun grayProjectSet(redisOperation: RedisOperation): Set<String> {
        var projects = cache.getIfPresent(getGrayRedisKey())
        if (projects != null) {
            return projects
        }
        synchronized(this) {
            projects = cache.getIfPresent(getGrayRedisKey())
            if (projects != null) {
                return projects!!
            }
            logger.info("Refresh the local gray projects")
            projects = (redisOperation.getSetMembers(getGrayRedisKey()) ?: emptySet()).filter { !it.isBlank() }.toSet()
            cache.put(getGrayRedisKey(), projects!!)
        }
        return projects!!
    }

    fun getGrayRedisKey() = redisKey

    companion object {
        private val logger = LoggerFactory.getLogger(Gray::class.java)
    }
}
