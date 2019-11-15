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

package com.tencent.devops.quality.util

import com.tencent.devops.notify.pojo.RtxNotifyMessage
import com.tencent.devops.quality.pojo.RuleCheckSingleResult

object RtxUtil {
    fun makeEndMessage(
        projectName: String,
        pipelineName: String,
        buildNo: String,
        time: String,
        interceptList: List<String>,
        url: String,
        receivers: Set<String>
    ): RtxNotifyMessage {
        val thresholdListString = interceptList.joinToString("；")
        val message = RtxNotifyMessage()
        message.addAllReceivers(receivers)
        message.title = "【蓝盾质量红线】拦截通知"
        message.body = "$pipelineName(#$buildNo)被拦截\n" +
                "所属项目：$projectName\n" +
                "拦截时间：$time\n" +
                "拦截指标：$thresholdListString\n" +
                "详情链接：$url"
        message.sender = "蓝鲸助手"
        return message
    }

    fun makeAuditMessage(
        projectName: String,
        pipelineName: String,
        buildNo: String,
        time: String,
        resultList: List<RuleCheckSingleResult>,
        url: String,
        receivers: Set<String>
    ): RtxNotifyMessage {
        val body = StringBuilder()
        body.append("$pipelineName(#$buildNo)被拦截，需要审核\n")
        body.append("所属项目：$projectName\n")
        body.append("拦截时间：$time\n")
        resultList.forEach { result ->
            body.append("拦截规则：${result.ruleName}\n")
            body.append("拦截指标：\n")
            result.messagePairs.forEach {
                body.append(it.first + "\n")
            }
        }
        body.append("审核链接：$url")

        val message = RtxNotifyMessage()
        message.addAllReceivers(receivers)
        message.title = "【蓝盾质量红线】审核通知"
        message.body = body.toString()
        message.sender = "蓝鲸助手"
        return message
    }
}