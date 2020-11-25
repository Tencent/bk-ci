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

package com.tencent.devops.gitci.service

import com.tencent.devops.artifactory.api.service.ServiceArtifactoryDownLoadResource
import com.tencent.devops.artifactory.api.service.ServiceArtifactoryResource
import com.tencent.devops.artifactory.pojo.FileInfo
import com.tencent.devops.artifactory.pojo.FileInfoPage
import com.tencent.devops.artifactory.pojo.Property
import com.tencent.devops.artifactory.pojo.Url
import com.tencent.devops.artifactory.pojo.enums.ArtifactoryType
import com.tencent.devops.common.api.exception.CustomException
import com.tencent.devops.common.client.Client
import com.tencent.devops.common.pipeline.enums.ChannelCode
import com.tencent.devops.gitci.dao.GitCISettingDao
import com.tencent.devops.gitci.dao.GitRequestEventBuildDao
import com.tencent.devops.gitci.dao.GitRequestEventDao
import com.tencent.devops.gitci.pojo.GitCIModelDetail
import com.tencent.devops.process.api.service.ServiceBuildResource
import com.tencent.devops.process.api.user.UserReportResource
import com.tencent.devops.process.pojo.Report
import com.tencent.devops.scm.api.ServiceGitCiResource
import org.jooq.DSLContext
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.ws.rs.core.Response

@Service
class CurrentBuildService @Autowired constructor(
    private val client: Client,
    private val dslContext: DSLContext,
    private val gitCISettingDao: GitCISettingDao,
    private val gitRequestEventBuildDao: GitRequestEventBuildDao,
    private val gitRequestEventDao: GitRequestEventDao
) {
    companion object {
        private val logger = LoggerFactory.getLogger(CurrentBuildService::class.java)
    }

    private val channelCode = ChannelCode.GIT

    fun getLatestBuildDetail(userId: String, gitProjectId: Long): GitCIModelDetail? {
        val conf = gitCISettingDao.getSetting(dslContext, gitProjectId) ?: throw CustomException(
            Response.Status.FORBIDDEN,
            "项目未开启工蜂CI，无法查询"
        )
        val eventBuildRecord = gitRequestEventBuildDao.getLatestBuild(dslContext, gitProjectId) ?: return null
        val eventRecord = gitRequestEventDao.get(dslContext, eventBuildRecord.eventId)
        val modelDetail = client.get(ServiceBuildResource::class).getBuildDetail(
            userId,
            conf.projectCode!!,
            eventBuildRecord.pipelineId,
            eventBuildRecord.buildId,
            channelCode
        ).data!!

        return GitCIModelDetail(eventRecord!!, modelDetail)
    }

    fun getBuildDetail(userId: String, gitProjectId: Long, buildId: String): GitCIModelDetail? {
        val conf = gitCISettingDao.getSetting(dslContext, gitProjectId) ?: throw CustomException(
            Response.Status.FORBIDDEN,
            "项目未开启工蜂CI，无法查询"
        )
        val eventBuildRecord = gitRequestEventBuildDao.getByBuildId(dslContext, buildId) ?: return null
        val eventRecord = gitRequestEventDao.get(dslContext, eventBuildRecord.eventId)
        val modelDetail = client.get(ServiceBuildResource::class).getBuildDetail(
            userId,
            conf.projectCode!!,
            eventBuildRecord.pipelineId,
            buildId,
            channelCode
        ).data!!

        return GitCIModelDetail(eventRecord!!, modelDetail)
    }

    fun search(
        userId: String,
        gitProjectId: Long,
        pipelineId: String,
        buildId: String,
        page: Int?,
        pageSize: Int?
    ): FileInfoPage<FileInfo> {
        val conf = gitCISettingDao.getSetting(dslContext, gitProjectId) ?: throw CustomException(
            Response.Status.FORBIDDEN,
            "项目未开启工蜂CI，无法查询"
        )

        val propMap = HashMap<String, String>()
        propMap["pipelineId"] = pipelineId
        propMap["buildId"] = buildId
        // val searchProps = SearchProps(emptyList(), propMap)

        val prop = listOf(Property("pipelineId", pipelineId), Property("buildId", buildId))

        return client.get(ServiceArtifactoryResource::class).search(
            conf.projectCode!!,
            page,
            pageSize,
            prop
        ).data!!
    }

    fun downloadUrl(
        userId: String,
        gitUserId: String,
        gitProjectId: Long,
        artifactoryType: ArtifactoryType,
        path: String
    ): Url {
        val conf = gitCISettingDao.getSetting(dslContext, gitProjectId) ?: throw CustomException(
            Response.Status.FORBIDDEN,
            "项目未开启工蜂CI，无法查询"
        )

        // 校验工蜂项目权限
        val checkAuth = client.getScm(ServiceGitCiResource::class).checkUserGitAuth(gitUserId, gitProjectId.toString())
        if (!checkAuth.data!!) {
            throw CustomException(Response.Status.FORBIDDEN, "用户没有工蜂项目权限，无法获取下载链接")
        }

        try {
            return client.get(ServiceArtifactoryDownLoadResource::class).downloadUrl(
                conf.projectCode!!,
                artifactoryType,
                userId,
                path,
                10,
                true
            ).data!!
        } catch (e: Exception) {
            logger.error("Artifactory download url failed. ${e.message}")
            throw CustomException(Response.Status.BAD_REQUEST, "Artifactory download url failed. ${e.message}")
        }
    }

    fun getReports(userId: String, gitProjectId: Long, pipelineId: String, buildId: String): List<Report> {
        val conf = gitCISettingDao.getSetting(dslContext, gitProjectId) ?: throw CustomException(
            Response.Status.FORBIDDEN,
            "项目未开启工蜂CI，无法查询"
        )

        return client.get(UserReportResource::class).get(userId, conf.projectCode!!, pipelineId, buildId).data!!
    }
}
