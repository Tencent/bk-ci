package com.tencent.devops.sign.api.pojo

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel("IPA包签名信息")
data class IpaSignInfo(
    @ApiModelProperty("操作用户", required = true)
    var userId: String,
    @ApiModelProperty("是否采用通配符重签", required = true)
    var wildcard: Boolean,
    @ApiModelProperty("文件名称", required = false)
    var fileName: String? = null,
    @ApiModelProperty("文件大小", required = false)
    var fileSize: Long? = null,
    @ApiModelProperty("文件MD5", required = false)
    var md5: String? = null,
    @ApiModelProperty("证书ID", required = false)
    var certId: String? = null,
    @ApiModelProperty("归档类型(PIPELINE|CUSTOM)", required = false)
    var archiveType: String? = "PIPELINE",
    @ApiModelProperty("项目Id", required = false)
    var projectId: String? = null,
    @ApiModelProperty("流水线Id", required = false)
    var pipelineId: String? = null,
    @ApiModelProperty("构建Id", required = false)
    var buildId: String? = null,
    @ApiModelProperty("归档路径", required = false)
    var archivePath: String? = "/",
    @ApiModelProperty("主App描述文件ID", required = false)
    var mobileProvisionId: String? = null,
    @ApiModelProperty("Universal Link的设置", required = false)
    var universalLinks: List<String>? = null,
    @ApiModelProperty("应用安全组", required = false)
    var applicationGroups: List<String>? = null,
    @ApiModelProperty("是否替换bundleId", required = false)
    var repalceBundleId: Boolean? = false,
    @ApiModelProperty("拓展应用名和对应的描述文件ID", required = false)
    var appexSignInfo: List<AppexSignInfo>? = null
)