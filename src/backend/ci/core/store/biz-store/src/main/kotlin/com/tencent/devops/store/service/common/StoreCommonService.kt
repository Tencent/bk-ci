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

package com.tencent.devops.store.service.common

import com.tencent.devops.store.pojo.common.Category
import com.tencent.devops.store.pojo.common.Label
import com.tencent.devops.store.pojo.common.ReleaseProcessItem
import com.tencent.devops.store.pojo.common.StoreProcessInfo
import com.tencent.devops.store.pojo.common.enums.ReleaseTypeEnum
import com.tencent.devops.store.pojo.common.enums.StoreTypeEnum
import org.jooq.Record

/**
 * store公共
 * since: 2019-07-23
 */
interface StoreCommonService {

    /**
     * 根据ID获取组件名称
     */
    fun getStoreNameById(
        storeId: String,
        storeType: StoreTypeEnum
    ): String

    /**
     * 获取正确的升级版本号
     */
    fun getRequireVersion(
        dbVersion: String,
        releaseType: ReleaseTypeEnum
    ): String

    /**
     * 设置进度
     */
    fun setProcessInfo(
        processInfo: List<ReleaseProcessItem>,
        totalStep: Int,
        currStep: Int,
        status: String
    ): Boolean

    /**
     * 生成发布流程进度信息
     */
    fun generateStoreProcessInfo(
        userId: String,
        storeId: String,
        storeCode: String,
        storeType: StoreTypeEnum,
        modifier: String,
        processInfo: List<ReleaseProcessItem>
    ): StoreProcessInfo

    /**
     * 获取store组件详情页地址
     */
    fun getStoreDetailUrl(storeType: StoreTypeEnum, storeCode: String): String

    /**
     * 为标签集合添加标签
     */
    fun addLabelToLabelList(it: Record, labelList: MutableList<Label>)

    /**
     * 为范畴集合添加范畴
     */
    fun addCategoryToCategoryList(it: Record, categoryList: MutableList<Category>)
}
