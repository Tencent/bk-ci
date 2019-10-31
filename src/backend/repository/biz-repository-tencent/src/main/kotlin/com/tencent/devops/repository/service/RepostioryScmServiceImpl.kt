package com.tencent.devops.repository.service

import com.tencent.devops.common.api.enums.ScmType
import com.tencent.devops.common.api.pojo.Result
import com.tencent.devops.common.client.Client
import com.tencent.devops.process.api.service.ServiceBuildResource
import com.tencent.devops.process.pojo.BuildBasicInfo
import com.tencent.devops.repository.pojo.Project
import com.tencent.devops.repository.pojo.enums.*
import com.tencent.devops.repository.pojo.git.GitProjectInfo
import com.tencent.devops.repository.pojo.git.UpdateGitProjectInfo
import com.tencent.devops.repository.pojo.oauth.GitToken
import com.tencent.devops.scm.api.ServiceGitResource
import com.tencent.devops.scm.api.ServiceScmResource
import com.tencent.devops.scm.api.ServiceSvnResource
import com.tencent.devops.scm.pojo.GitRepositoryResp
import com.tencent.devops.scm.pojo.TokenCheckResult
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RepostioryScmServiceImpl @Autowired constructor(
    private val client: Client
):RepostioryScmService{
    override fun getProject(accessToken: String, userId: String):List<Project> {
        return client.get(ServiceGitResource::class).getProject(accessToken, userId).data ?: listOf()
    }

    override fun getAuthUrl(authParamJsonStr: String): String {
        return client.getScm(ServiceGitResource::class).getAuthUrl(authParamJsonStr).data ?: ""
    }

    override fun getToken(userId: String, code: String): GitToken {
        return client.get(ServiceGitResource::class).getToken(userId, code).data
                ?: throw RuntimeException("get token fail")
    }

    override fun getRedirectUrl(redirectUrlType: String): String {
        return client.get(ServiceGitResource::class).getRedirectUrl(redirectUrlType).data ?: ""
    }

    override fun refreshToken(userId: String, accessToken: GitToken): GitToken {
        return client.get(ServiceGitResource::class).refreshToken(userId, accessToken).data!!
    }

    override fun getSvnFileContent(url: String, userId: String, svnType: String, filePath: String, reversion: Long, credential1: String, credential2: String?): String {
        return client.getScm(ServiceSvnResource::class).getFileContent(url, userId, svnType, filePath, reversion,
                credential1, credential2).data ?: ""
    }

    override fun getGitFileContent(repoName: String, filePath: String, authType: RepoAuthType?, token: String, ref: String): String {
        return client.getScm(ServiceGitResource::class).getGitFileContent(repoName!!, filePath.removePrefix("/"), authType, token, ref).data ?: ""
    }

    override fun getGitlabFileContent(repoUrl: String, repoName: String, filePath: String, ref: String, accessToken: String): String {
        return client.getScm(ServiceGitResource::class).getGitlabFileContent(
                repoName = repoName,
                filePath = filePath,
                ref = ref,
                accessToken = accessToken
        ).data ?: ""
    }

    override fun unlock(projectName: String, url: String, type: ScmType, region: CodeSvnRegion?, userName: String): Boolean {
        return client.getScm(ServiceScmResource::class)
                .unlock(projectName, url, type, region, userName).data ?: false
    }

    override fun lock(projectName: String, url: String, type: ScmType, region: CodeSvnRegion?, userName: String): Boolean {
        return client.getScm(ServiceScmResource::class).lock(
                projectName,
                url,
                type,
                region,
                userName
                ).data ?: false
    }

    override fun moveProjectToGroup(token: String, groupCode: String, repositoryName: String, tokenType: TokenTypeEnum): Result<GitProjectInfo?> {
        return client.getScm(ServiceGitResource::class)
                .moveProjectToGroup(token, groupCode, repositoryName, tokenType)
    }

    override fun updateGitCodeRepository(token: String, projectName: String, updateGitProjectInfo: UpdateGitProjectInfo, tokenType: TokenTypeEnum): Result<Boolean> {
        return client.getScm(ServiceGitResource::class)
                .updateGitCodeRepository(token, projectName, updateGitProjectInfo, tokenType)
    }

    override fun createGitCodeRepository(userId: String, token: String, repositoryName: String, sampleProjectPath: String?, namespaceId: Int?, visibilityLevel: VisibilityLevelEnum?, tokenType: TokenTypeEnum): Result<GitRepositoryResp?> {
        return client.getScm(ServiceGitResource::class)
                .createGitCodeRepository(
                        userId,
                        token,
                        repositoryName,
                        sampleProjectPath,
                        namespaceId,
                        visibilityLevel,
                        tokenType
                )
    }

    override fun addGitProjectMember(userIdList: List<String>, repositorySpaceName: String, gitAccessLevel: GitAccessLevelEnum, token: String, tokenType: TokenTypeEnum): Result<Boolean> {
        return client.getScm(ServiceGitResource::class)
                .addGitProjectMember(userIdList, repositorySpaceName, gitAccessLevel, token, tokenType)
    }

    override fun deleteGitProjectMember(userIdList: List<String>, repositorySpaceName: String, token: String, tokenType: TokenTypeEnum): Result<Boolean> {
        return client.getScm(ServiceGitResource::class)
                .deleteGitProjectMember(userIdList, repositorySpaceName, token, tokenType)
    }

    override fun checkPrivateKeyAndToken(projectName: String, url: String, type: ScmType, privateKey: String?, passPhrase: String?, token: String?, region: CodeSvnRegion?, userName: String): Result<TokenCheckResult> {
        return client.getScm(ServiceScmResource::class).checkPrivateKeyAndToken(projectName, url, type, privateKey, passPhrase, token, region, userName)
    }

    override fun checkUsernameAndPassword(projectName: String, url: String, type: ScmType, username: String, password: String, token: String, region: CodeSvnRegion?, repoUsername: String): Result<TokenCheckResult> {
        return client.getScm(ServiceScmResource::class).checkUsernameAndPassword(projectName, url, type, username, password, token, region, repoUsername)
    }
}