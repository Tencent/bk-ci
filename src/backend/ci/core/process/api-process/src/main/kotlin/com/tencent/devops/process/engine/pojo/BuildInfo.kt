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

package com.tencent.devops.process.engine.pojo

import com.tencent.devops.common.api.pojo.ErrorType
import com.tencent.devops.common.pipeline.enums.BuildStatus
import com.tencent.devops.common.pipeline.enums.ChannelCode

data class BuildInfo(
    val projectId: String,
    val pipelineId: String,
    val buildId: String,
    val version: Int,
    val buildNum: Int,
    val trigger: String,
    val status: BuildStatus,
    val startUser: String,
    val startTime: Long?,
    val endTime: Long?,
    val taskCount: Int,
    val firstTaskId: String,
    val parentBuildId: String?,
    val parentTaskId: String?,
    val channelCode: ChannelCode,
    var errorType: ErrorType?,
    var errorCode: Int?,
    var errorMsg: String?
) {

    fun isFinish() = when {
        status.name == BuildStatus.STAGE_SUCCESS.name && endTime != null && endTime > 0 && startTime != null && endTime > startTime -> true
        else -> BuildStatus.isFinish(status)
    }

    fun isSuccess() = when {
        status.name == BuildStatus.STAGE_SUCCESS.name && endTime != null && endTime > 0 && startTime != null && endTime > startTime -> true
        else -> BuildStatus.isSuccess(status)
    }

    fun isFailure() = BuildStatus.isFailure(status)

    fun isCancel() = BuildStatus.isCancel(status)

    fun isStageSuccess() = status == BuildStatus.STAGE_SUCCESS

    fun isReadyToRun() = BuildStatus.isReadyToRun(status)
}