package com.tencent.devops.store.service.image

import com.tencent.devops.artifactory.api.service.ServiceImageManageResource
import com.tencent.devops.common.client.Client
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @Description
 * @Date 2019/11/21
 * @Version 1.0
 */
@Service
class SupportService @Autowired constructor(
    private val client: Client
) {
    private val logger = LoggerFactory.getLogger(SupportService::class.java)

    fun getIconDataByLogoUrl(
        logoUrl: String
    ): String? {
        try {
            val iconData = client.get(ServiceImageManageResource::class).compressImage(logoUrl!!, 18, 18).data
            logger.info("the iconData is :$iconData")
            return iconData
        } catch (e: Exception) {
            logger.error("compressImage error is :$e", e)
        }
        return null
    }
}