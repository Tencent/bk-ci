/*
 * Tencent is pleased to support the open source community by making BlueKing available.
 * Copyright (C) 2017-2018 THL A29 Limited, a Tencent company. All rights reserved.
 * Licensed under the MIT License (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * http://opensource.org/licenses/MIT
 * Unless required by applicable law or agreed to in writing, software distributed under
 * the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tencent.bk.codecc.apiquery.defect.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Set;

/**
 * 开源扫描规则集配置
 *
 * @date 2020/5/11
 * @version V1.0
 */
@Data
public class OpenSourceCheckerSet {
    @JsonProperty("checker_set_id")
    private String checkerSetId;

    @JsonProperty("tool_list")
    private Set<String> toolList;

    @JsonProperty("checker_set_type")
    private String checkerSetType;

    @JsonProperty("version")
    private Integer version;
}
