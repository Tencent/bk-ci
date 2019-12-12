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

package com.tencent.devops.common.auth

import com.tencent.devops.common.auth.api.MockAuthPermissionApi
import com.tencent.devops.common.auth.api.MockAuthProjectApi
import com.tencent.devops.common.auth.api.MockAuthResourceApi
import com.tencent.devops.common.auth.api.MockAuthTokenApi
import com.tencent.devops.common.auth.code.BkArtifactoryAuthServiceCode
import com.tencent.devops.common.auth.code.BkBcsAuthServiceCode
import com.tencent.devops.common.auth.code.BkCodeAuthServiceCode
import com.tencent.devops.common.auth.code.BkEnvironmentAuthServiceCode
import com.tencent.devops.common.auth.code.BkPipelineAuthServiceCode
import com.tencent.devops.common.auth.code.BkProjectAuthServiceCode
import com.tencent.devops.common.auth.code.BkQualityAuthServiceCode
import com.tencent.devops.common.auth.code.BkRepoAuthServiceCode
import com.tencent.devops.common.auth.code.BkTicketAuthServiceCode
import org.springframework.boot.autoconfigure.AutoConfigureOrder
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.core.Ordered

@Configuration
@ConditionalOnProperty(prefix = "auth", name = ["idProvider"], havingValue = "sample", matchIfMissing = true)
@ConditionalOnWebApplication
@AutoConfigureOrder(Ordered.LOWEST_PRECEDENCE)
class AuthAutoConfiguration {

    @Bean
    @Primary
    fun authTokenApi() = MockAuthTokenApi()

    @Bean
    @Primary
    fun authPermissionApi() = MockAuthPermissionApi()

    @Bean
    @Primary
    fun authResourceApi(authTokenApi: MockAuthTokenApi) = MockAuthResourceApi()

    @Bean
    @Primary
    fun authProjectApi(bkAuthPermissionApi: MockAuthPermissionApi) = MockAuthProjectApi(bkAuthPermissionApi)

    @Bean
    fun bcsAuthServiceCode() = BkBcsAuthServiceCode()

    @Bean
    fun pipelineAuthServiceCode() = BkPipelineAuthServiceCode()

    @Bean
    fun codeAuthServiceCode() = BkCodeAuthServiceCode()

    @Bean
    fun projectAuthServiceCode() = BkProjectAuthServiceCode()

    @Bean
    fun environmentAuthServiceCode() = BkEnvironmentAuthServiceCode()

    @Bean
    fun repoAuthServiceCode() = BkRepoAuthServiceCode()

    @Bean
    fun ticketAuthServiceCode() = BkTicketAuthServiceCode()

    @Bean
    fun qualityAuthServiceCode() = BkQualityAuthServiceCode()

    @Bean
    fun artifactoryAuthServiceCode() = BkArtifactoryAuthServiceCode()
}