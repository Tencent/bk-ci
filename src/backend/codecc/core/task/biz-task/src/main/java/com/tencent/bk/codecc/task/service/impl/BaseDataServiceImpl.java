/*
 * Tencent is pleased to support the open source community by making BK-CODECC 蓝鲸代码检查平台 available.
 *
 * Copyright (C) 2019 THL A29 Limited, a Tencent company.  All rights reserved.
 *
 * BK-CODECC 蓝鲸代码检查平台 is licensed under the MIT license.
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

package com.tencent.bk.codecc.task.service.impl;

import com.google.common.collect.Lists;
import com.tencent.bk.codecc.task.dao.mongorepository.BaseDataRepository;
import com.tencent.bk.codecc.task.model.BaseDataEntity;
import com.tencent.bk.codecc.task.service.BaseDataService;
import com.tencent.bk.codecc.task.vo.BaseDataVO;
import com.tencent.devops.common.api.exception.CodeCCException;
import com.tencent.devops.common.constant.ComConstants;
import com.tencent.devops.common.constant.CommonMessageCode;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 基础数据服务代码
 *
 * @version V1.0
 * @date 2019/5/28
 */
@Slf4j
@Service
public class BaseDataServiceImpl implements BaseDataService
{

    @Autowired
    private BaseDataRepository baseDataRepository;

    @Override
    public List<BaseDataVO> findBaseDataInfoByTypeAndCode(String paramType, String paramCode)
    {
        List<BaseDataEntity> baseDataEntityList = baseDataRepository.findAllByParamTypeAndParamCode(paramType, paramCode);
        return null == baseDataEntityList ? new ArrayList<>() : baseDataEntityList.stream()
                .map(baseDataEntity ->
                {
                    BaseDataVO baseDataVO = new BaseDataVO();
                    BeanUtils.copyProperties(baseDataEntity, baseDataVO);
                    return baseDataVO;
                })
                .collect(Collectors.toList());
    }

    /**
     * 根据参数类型查询参数列表
     *
     * @param paramType
     * @return
     */
    @Override
    public List<BaseDataVO> findBaseDataInfoByType(String paramType)
    {
        List<BaseDataEntity> baseDataEntityList = baseDataRepository.findAllByParamType(paramType);
        return null == baseDataEntityList ? new ArrayList<>() : baseDataEntityList.stream()
                .map(baseDataEntity ->
                {
                    BaseDataVO baseDataVO = new BaseDataVO();
                    BeanUtils.copyProperties(baseDataEntity, baseDataVO);
                    return baseDataVO;
                })
                .collect(Collectors.toList());
    }


    @Override
    public List<BaseDataVO> findBaseDataInfoByTypeAndCodeAndValue(String paramType, String paramCode, String paramValue)
    {
        List<BaseDataEntity> baseDataEntityList = baseDataRepository.findAllByParamTypeAndParamCodeAndParamValue(paramType, paramCode, paramValue);
        return null == baseDataEntityList ? new ArrayList<>() : baseDataEntityList.stream()
                .map(baseDataEntity ->
                {
                    BaseDataVO baseDataVO = new BaseDataVO();
                    BeanUtils.copyProperties(baseDataEntity, baseDataVO);
                    return baseDataVO;
                })
                .collect(Collectors.toList());
    }


    /**
     * 更新屏蔽用户名单
     *
     * @param baseDataVO vo
     * @return boolean
     */
    @Override
    public Boolean updateExcludeUserMember(BaseDataVO baseDataVO, String userName) {
        if (baseDataVO == null) {
            log.error("updateExcludeUserMember req body is null!");
            throw new CodeCCException(CommonMessageCode.PARAMETER_IS_INVALID, new String[]{"baseDataVO"}, null);
        }

        String paramValue = baseDataVO.getParamValue();
        if (StringUtils.isNotBlank(paramValue)) {
            BaseDataEntity entity = baseDataRepository.findFirstByParamType(ComConstants.KEY_EXCLUDE_USER_LIST);
            if (entity == null) {
                entity = new BaseDataEntity();
                entity.setParamType(ComConstants.KEY_EXCLUDE_USER_LIST);
                entity.setParamCode(ComConstants.KEY_EXCLUDE_USER_LIST);
                entity.setCreatedBy(userName);
                entity.setCreatedDate(System.currentTimeMillis());
            }

            entity.setParamValue(paramValue);
            entity.setUpdatedBy(userName);
            entity.setUpdatedDate(System.currentTimeMillis());
            baseDataRepository.save(entity);
            return true;
        }
        return false;
    }


    /**
     * 获取屏蔽用户名单
     *
     * @return list
     */
    @Override
    public List<String> queryMemberListByParamType(String paramType) {
        BaseDataEntity entity = baseDataRepository.findFirstByParamType(paramType);
        if (null == entity) {
            return Lists.newArrayList();
        }
        String excludeUserStr = entity.getParamValue();
        List<String> userList;
        if (StringUtils.isBlank(excludeUserStr)) {
            userList = Lists.newArrayList();
        } else {
            userList = Lists.newArrayList(excludeUserStr.split(ComConstants.SEMICOLON));
        }
        return userList;
    }


}
