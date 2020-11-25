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

package com.tencent.devops.support.model.wechatwork

import com.tencent.devops.support.model.wechatwork.enums.FromType
import com.tencent.devops.support.model.wechatwork.enums.MsgType
import org.dom4j.Element

data class CallbackElement(
        // 公共部分抽离出来
    val toUserName: String, // 接收人，一般只的都是我们wxab249edd27d57738的id
    val serviceId: String, // 服务号id,fw06b88b0e0531c52c
    val agentType: String, // 一般都是chat
    val chatId: String, // 发送人
    val msgType: MsgType, // xml.Msg.MsgType
    val fromType: FromType, // xml.Msg.MsgType
        // 没办法抽离出来的，讲Msg整个element存放起来
    val msgElement: Element, // xml.Msg
    val fromElement: Element // xml.Msg.From

)
