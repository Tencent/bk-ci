package com.tencent.devops.store.resources.image.service

import com.tencent.devops.common.api.pojo.Result
import com.tencent.devops.common.web.RestResource
import com.tencent.devops.store.api.image.service.ServiceStoreImageResource
import com.tencent.devops.store.pojo.common.enums.StoreTypeEnum
import com.tencent.devops.store.pojo.image.response.ImageRepoInfo
import com.tencent.devops.store.service.common.StoreProjectService
import com.tencent.devops.store.service.image.ImageFeatureService
import com.tencent.devops.store.service.image.ImageService
import org.springframework.beans.factory.annotation.Autowired

@RestResource
class ServiceImageResourceImpl @Autowired constructor(
    private val imageService: ImageService,
    private val imageFeatureService: ImageFeatureService,
    private val storeProjectService: StoreProjectService
) : ServiceStoreImageResource {
    override fun isInstalled(userId: String, projectCode: String, imageCode: String): Result<Boolean> {
        return Result(
            // 公共镜像视为默认安装
            imageFeatureService.isImagePublic(imageCode) ||
                storeProjectService.isInstalledByProject(
                    projectCode = projectCode,
                    storeCode = imageCode,
                    storeType = StoreTypeEnum.IMAGE.type.toByte()
                )
        )
    }

    override fun getImageRepoInfoByCodeAndVersion(
        userId: String,
        projectCode: String,
        imageCode: String,
        imageVersion: String?
    ): Result<ImageRepoInfo> {
        return Result(
            imageService.getImageRepoInfoByCodeAndVersion(
                userId = userId,
                projectCode = projectCode,
                imageCode = imageCode,
                imageVersion = imageVersion,
                interfaceName = "/image/imageCodes/{imageCode}/imageVersions/{imageVersion}"
            )
        )
    }
}