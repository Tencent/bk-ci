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

<<<<<<< HEAD:src/backend/codecc/core/defect/api-defect/src/main/java/com/tencent/bk/codecc/defect/vo/LintDefectGroupStatisticVO.java
package com.tencent.bk.codecc.defect.vo;

import lombok.Data;

/**
 * lint类文件告警详情
 *
 * @version V1.0
 * @date 2019/5/9
 */
@Data
public class LintDefectGroupStatisticVO
{
    /**
     * 状态：NEW(1), FIXED(2), IGNORE(8)
     */
    private int status;

    /**
     * 状态：严重(1), 一般(2), 提示(3)
     */
    private int severity;

    private Long lineUpdateTime;

    private int defectCount;
=======
package com.tencent.bk.codecc.task.dao.mongorepository;

import com.tencent.bk.codecc.task.model.BuildIdRelationshipEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Codecc buildId和蓝盾流水线buildId关系持久化接口
 * @version V1.0
 * @date 2020/08/20
 */
@Repository
public interface BuildIdRelationshipRepository extends MongoRepository<BuildIdRelationshipEntity, ObjectId> {


>>>>>>> 51f8a48c54... feat: 容器化版本迁移 #2953:src/backend/codecc/core/task/biz-task/src/main/java/com/tencent/bk/codecc/task/dao/mongorepository/BuildIdRelationshipRepository.java
}
