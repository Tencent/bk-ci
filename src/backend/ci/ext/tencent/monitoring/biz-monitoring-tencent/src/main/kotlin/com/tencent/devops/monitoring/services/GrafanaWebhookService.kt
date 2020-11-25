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
package com.tencent.devops.monitoring.services

import com.tencent.devops.common.api.pojo.Result
import com.tencent.devops.common.api.util.JsonUtil
import com.tencent.devops.common.client.Client
import com.tencent.devops.common.notify.enums.EnumEmailFormat
import com.tencent.devops.monitoring.pojo.GrafanaMessage
import com.tencent.devops.monitoring.pojo.GrafanaNotification
import com.tencent.devops.monitoring.pojo.NocNoticeBusData
import com.tencent.devops.monitoring.pojo.enums.GrafanaNotifyTypeEnum
import com.tencent.devops.notify.api.service.ServiceNotifyResource
import com.tencent.devops.notify.pojo.EmailNotifyMessage
import com.tencent.devops.notify.pojo.RtxNotifyMessage
import com.tencent.devops.notify.pojo.WechatNotifyMessage
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

@Service
@RefreshScope
class GrafanaWebhookService @Autowired constructor(
    private val nocNoticeService: NocNoticeService,
    private val client: Client
) {
    private val logger = LoggerFactory.getLogger(GrafanaWebhookService::class.java)

    @Value("\${alert.users}")
    private val alertUsersStr: String? = "rdeng,irwinsun"

    private lateinit var alertUsers: Set<String>

    @PostConstruct
    fun init() {
        alertUsers = alertUsersStr!!.split(",").toSet()
        logger.info("alert User= $alertUsers")
    }

    /**
     * grafana回调接口
     */
    fun webhookCallBack(grafanaNotification: GrafanaNotification): Result<Boolean> {
        logger.info("the grafanaNotification is:$grafanaNotification")
        // 只有处于alerting告警状态的信息才发送监控消息
        if ("alerting".equals(grafanaNotification.state, true)) {
            val notifyTitle = grafanaNotification.title
            val message = grafanaNotification.message
            val grafanaMessage = JsonUtil.to(message, GrafanaMessage::class.java) // 转换消息内容json串
            val notifyType = grafanaMessage.notifyType ?: GrafanaNotifyTypeEnum.RTX_WECHAT_EMIAL
            val notifyReceivers = grafanaMessage.notifyReceivers ?: alertUsers
            var notifyMessage = grafanaMessage.notifyMessage
            val evalMatches = grafanaNotification.evalMatches
            val busiDataList = mutableListOf<NocNoticeBusData>()
            if (null != evalMatches && evalMatches.isNotEmpty()) {
                notifyMessage += "（"
                evalMatches.forEach {
                    val metricName = it.metric
                    val metricValue = it.value
                    notifyMessage += " 监控对象：$metricName，当前值为：$metricValue；"
                    busiDataList.add(NocNoticeBusData(metricName, metricValue))
                }
                notifyMessage += "）"
            }
            // 发送noc告警
            val allowSendNocList = listOf(
                GrafanaNotifyTypeEnum.NOC,
                GrafanaNotifyTypeEnum.RTX_WECHAT_NOC,
                GrafanaNotifyTypeEnum.EMAIL_NOC,
                GrafanaNotifyTypeEnum.ALL
            )
            if (allowSendNocList.contains(notifyType)) {
                val sendNocResult = nocNoticeService.sendNocNotice(
                    notifyReceivers = notifyReceivers,
                    notifyTitle = notifyTitle,
                    notifyMessage = notifyMessage,
                    busiDataList = busiDataList
                )
                logger.info("the sendNocResult is:$sendNocResult")
            }
            // 发送微信消息
            val allowSendWechatList = listOf(
                GrafanaNotifyTypeEnum.WECHAT,
                GrafanaNotifyTypeEnum.RTX_WECHAT,
                GrafanaNotifyTypeEnum.RTX_WECHAT_EMIAL,
                GrafanaNotifyTypeEnum.RTX_WECHAT_NOC,
                GrafanaNotifyTypeEnum.ALL
            )
            if (allowSendWechatList.contains(notifyType)) {
                val wechatNotifyMessage = WechatNotifyMessage().apply {
                    addAllReceivers(notifyReceivers)
                    body = notifyMessage
                }
                logger.info("send the wechat wechatNotifyMessage: $wechatNotifyMessage")
                val sendWechatResult = client.get(ServiceNotifyResource::class).sendWechatNotify(wechatNotifyMessage)
                logger.info("the sendWechatResult is:$sendWechatResult")
            }
            // 发送企业微信消息
            val allowSendRtxList = listOf(
                GrafanaNotifyTypeEnum.RTX,
                GrafanaNotifyTypeEnum.RTX_WECHAT,
                GrafanaNotifyTypeEnum.RTX_WECHAT_EMIAL,
                GrafanaNotifyTypeEnum.RTX_WECHAT_NOC,
                GrafanaNotifyTypeEnum.ALL
            )
            if (allowSendRtxList.contains(notifyType)) {
                val rtxNotifyMessage = RtxNotifyMessage().apply {
                    addAllReceivers(notifyReceivers)
                    body = notifyMessage
                    title = notifyTitle
                }
                logger.info("send the rtx rtxNotifyMessage: $rtxNotifyMessage")
                val sendRtxResult = client.get(ServiceNotifyResource::class).sendRtxNotify(rtxNotifyMessage)
                logger.info("the sendRtxResult is:$sendRtxResult")
            }
            // 发送监控告警邮件
            val allowSendEmailList = listOf(
                GrafanaNotifyTypeEnum.EMAIL,
                GrafanaNotifyTypeEnum.RTX_WECHAT_EMIAL,
                GrafanaNotifyTypeEnum.EMAIL_NOC,
                GrafanaNotifyTypeEnum.ALL
            )
            if (allowSendEmailList.contains(notifyType)) {
                val emailNotifyMessage = EmailNotifyMessage().apply {
                    addAllReceivers(notifyReceivers)
                    format = EnumEmailFormat.HTML
                    body = notifyMessage
                    title = notifyTitle
                    sender = "DevOps"
                }
                logger.info("send the email emailNotifyMessage: $emailNotifyMessage")
                val sendEmailResult = client.get(ServiceNotifyResource::class).sendEmailNotify(emailNotifyMessage)
                logger.info("the sendEmailResult is:$sendEmailResult")
            }
        }
        return Result(true)
    }
}
