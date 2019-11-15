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

package com.tencent.devops.dispatch.cron

import com.tencent.devops.common.client.Client
import com.tencent.devops.common.pipeline.enums.ChannelCode
import com.tencent.devops.common.redis.RedisOperation
import com.tencent.devops.common.service.gray.Gray
import com.tencent.devops.dispatch.service.VMService
import com.tencent.devops.dispatch.service.vm.QueryVMs
import com.tencent.devops.dispatch.utils.ShutdownVMAfterBuildUtils
import com.tencent.devops.dispatch.utils.VMLock
import com.tencent.devops.dispatch.utils.redis.RedisUtils
import com.tencent.devops.process.api.service.ServicePipelineResource
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

/**
 * deng
 * 17/01/2018
 */
@Component
class VMCheckJob @Autowired constructor(
    private val client: Client,
    private val redisUtils: RedisUtils,
    private val redisOperation: RedisOperation,
    private val queryVMs: QueryVMs,
    private val vmService: VMService,
    private val vmAfterBuildUtils: ShutdownVMAfterBuildUtils,
    private val gray: Gray
) {

    // 20 minutes
    @Scheduled(initialDelay = 2000*60, fixedDelay = 2000*60)
    fun heartbeatCheck() {
        logger.info("Start to check the idle VM")
        if (gray.isGray()) {
            logger.info("The dispatch is gray dispatch, ignore")
            return
        }
        try {
            val allPowerOnVM = queryVMs.queryAllPowerOnVM()
            if (allPowerOnVM.isEmpty()) {
                return
            }
            allPowerOnVM.forEach {
                val vm = vmService.queryVMByName(it.name) ?: return@forEach
                if (vm.inMaintain) {
                    return@forEach
                }
                val redisLock = VMLock(redisOperation, vm.ip)
                try {
                    if (!redisLock.tryLock()) {
                        logger.info("It fail to lock the redis for the vm ${vm.ip}")
                        return@forEach
                    }
                    val redisBuild = redisUtils.getRedisBuild(vm.ip)
                    if (redisBuild == null) {
                        val vmShutdown = vmAfterBuildUtils.isShutdown(vm.ip)
                        if (vmShutdown.first) {
                            logger.warn("The vm(${vm.ip}) is not exist in the redis, try to stop it")
                            vmService.directShutdownVM(vm.id)
                        } else {
                            logger.warn("The vm(${vm.ip}) is not shutdown after the build(${vmShutdown.second})")
                        }
                    } else {
                        // Check if the pipeline if running
                        try {
                            val channelCode = if (redisBuild.channelCode.isNullOrBlank()) {
                                ChannelCode.BS
                            } else {
                                ChannelCode.valueOf(redisBuild.channelCode!!)
                            }
                            val result = client.get(ServicePipelineResource::class).isPipelineRunning(redisBuild.projectId,
                                    redisBuild.buildId, channelCode)
                            if (result.isNotOk() || result.data == null) {
                                logger.warn("Fail to check if the build(${redisBuild.buildId}) is running because of ${result.message} for the vm(${vm.ip})")
                                return@forEach
                            }
                            if (!result.data!!) {
                                logger.warn("The build(${redisBuild.buildId}) of pipeline(${redisBuild.pipelineId}) and project(${redisBuild.projectId}) is not running, try to shutdown the vm")
                                redisUtils.deleteRedisBuild(vm.ip)
                                vmService.directShutdownVM(vm.id)
                            }
                        } catch (e: Exception) {
                            logger.warn("Fail to check if the build(${redisBuild.buildId}) running", e)
                        }
                    }
                } finally {
                    redisLock.unlock()
                }
            }
        } catch (t: Throwable) {
            logger.warn("Fail to check the idle vm", t)
        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(VMCheckJob::class.java)
    }
}