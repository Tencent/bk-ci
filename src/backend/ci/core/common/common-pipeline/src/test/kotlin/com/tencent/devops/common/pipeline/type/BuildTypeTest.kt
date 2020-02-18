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

package com.tencent.devops.common.pipeline.type

import com.tencent.devops.common.api.pojo.OS
import com.tencent.devops.common.api.util.EnumUtil
import org.junit.Assert
import org.junit.Test

class BuildTypeTest {

    @Test
    fun addEnum() {
        val additionalValues = arrayOf("蓝盾Windows集群", listOf(OS.WINDOWS), true, true, true)
        EnumUtil.addEnum(
            enumType = BuildType::class.java, enumName = "DIY_WINDOWS",
            additionalValues = additionalValues
        )
        BuildType.values().forEach {
            println("${it.name} ${it.value} ${it.osList}")
        }

        Assert.assertNotNull(BuildType.valueOf("DIY_WINDOWS"))
        val newBuildType = BuildType.valueOf("DIY_WINDOWS")

        Assert.assertArrayEquals(
            additionalValues, arrayOf(
                newBuildType.value,
                newBuildType.osList,
                newBuildType.enableApp,
                newBuildType.clickable,
                newBuildType.visable
            )
        )
    }

    @Test
    fun changeEnum() {

        val additionalValues = arrayOf("蓝盾公共构建资源666", listOf(OS.MACOS), true, true, true)
        EnumUtil.addEnum(
            enumType = BuildType::class.java,
            enumName = BuildType.DOCKER.name,
            additionalValues = additionalValues
        )

        // 支持values 动态
        BuildType.values().forEach {
            println("${it.name} ${it.value} ${it.osList}")
            if (it.name === "DOCKER") {
                Assert.assertEquals("蓝盾公共构建资源666", it.value)
                Assert.assertEquals(1, it.osList.size)
                Assert.assertEquals(OS.MACOS, it.osList[0])
            }
        }

        // 支持 valueOf 动态实例化
        val newDOCKER = BuildType.valueOf(BuildType.DOCKER.name)
        Assert.assertEquals("蓝盾公共构建资源666", newDOCKER.value)
        Assert.assertArrayEquals(
            additionalValues,
            arrayOf(
                newDOCKER.value,
                newDOCKER.osList,
                newDOCKER.enableApp,
                newDOCKER.clickable,
                newDOCKER.visable
            )
        )

        // 不支持这种，因为前者在编译时已经被编译成常量
        Assert.assertNotEquals(BuildType.DOCKER.value, newDOCKER.value)
        Assert.assertNotEquals(BuildType.DOCKER.osList[0], newDOCKER.osList[0])
    }
}