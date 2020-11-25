package com.tencent.devops.store.api.common

import com.tencent.devops.common.api.auth.AUTH_HEADER_USER_ID
import com.tencent.devops.common.api.pojo.Result
import com.tencent.devops.store.pojo.common.MediaInfoReq
import com.tencent.devops.store.pojo.common.StoreMediaInfo
import com.tencent.devops.store.pojo.common.enums.StoreTypeEnum
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.HeaderParam
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Api(tags = ["OP_COMMON_MEDIA"], description = "研发商店_媒体信息")
@Path("/op/store/media")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
interface OpMediaResource {
    @ApiOperation("提交媒体信息")
    @Path("/storeCodes/{storeCode}/types/{storeType}/media")
    @POST
    fun createStoreMedia(
        @ApiParam("userId", required = true)
        @HeaderParam(AUTH_HEADER_USER_ID)
        userId: String,
        @ApiParam("研发商店代码", required = true)
        @PathParam("storeCode")
        storeCode: String,
        @ApiParam("类别", required = true)
        @PathParam("storeType")
        storeType: StoreTypeEnum,
        @ApiParam("评论信息", required = true)
        mediaInfoList: List<MediaInfoReq>
    ): Result<Boolean>

    @ApiOperation("获取单条媒体信息")
    @Path("/ids/{mediaId}")
    @GET
    fun getStoreMedia(
        @ApiParam("userId", required = true)
        @HeaderParam(AUTH_HEADER_USER_ID)
        userId: String,
        @ApiParam("媒体ID", required = true)
        @PathParam("mediaId")
        mediaId: String
    ): Result<StoreMediaInfo?>

    @ApiOperation("获取扩展服务所有媒体信息")
    @Path("/storesCodes/{storeCode}/types/{labelType}")
    @GET
    fun getStoreMediaByStoreCode(
        @ApiParam("userId", required = true)
        @HeaderParam(AUTH_HEADER_USER_ID)
        userId: String,
        @ApiParam("研发商店编码", required = true)
        @PathParam("storeCode")
        storeCode: String,
        @ApiParam("类别", required = true)
        @PathParam("labelType")
        labelType: StoreTypeEnum
    ): Result<List<StoreMediaInfo>?>
}