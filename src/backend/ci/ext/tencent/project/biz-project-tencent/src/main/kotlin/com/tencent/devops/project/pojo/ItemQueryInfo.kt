package com.tencent.devops.project.pojo

import com.tencent.devops.project.api.pojo.enums.ServiceItemStatusEnum
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel("查询扩展点请求对象")
data class ItemQueryInfo(
    @ApiModelProperty("所属服务Id", required = false)
    val serviceId: String? = null,
    @ApiModelProperty("扩展点名称", required = false)
    val itemName: String? = null,
    @ApiModelProperty("扩展点状态", required = false)
    val itemStatusList: List<ServiceItemStatusEnum>?,
    @ApiModelProperty("页数", required = false)
    val page: Int? = 1,
    @ApiModelProperty("每页条数", required = false)
    val pageSize: Int? = 10
)