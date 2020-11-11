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

package com.tencent.bk.codecc.apiquery.defect.dao.mongotemplate;

import com.google.common.collect.Lists;
import com.tencent.bk.codecc.apiquery.defect.model.LintDefectV2Model;
import com.tencent.bk.codecc.apiquery.defect.model.LintStatisticModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOptions;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * lint类工具告警持久层代码
 *
 * @version V1.0
 * @date 2019/5/10
 */
@Repository
public class LintDefectDao {
    @Autowired
    @Qualifier("defectMongoTemplate")
    private MongoTemplate mongoTemplate;


    /**
     * 根据工具ID和工具列表查询最近一次分析记录
     *
     * @param taskIdSet 任务ID集合
     * @param toolName  工具名
     * @return list
     */
    public List<LintStatisticModel> findStatByTaskIdInAndToolIs(Collection<Long> taskIdSet, String toolName) {
        // 以taskId tooName进行过滤
        MatchOperation match = Aggregation.match(Criteria.where("task_id").in(taskIdSet).and("tool_name").is(toolName));
        // 根据时间倒序排列
        SortOperation sort = Aggregation.sort(Sort.Direction.DESC, "time");
        // 以taskId进行分组，并取第一个的字段
        GroupOperation group = Aggregation.group("task_id")
                .first("task_id").as("task_id")
                .first("tool_name").as("tool_name")
                .first("time").as("time")
                .first("defect_count").as("defect_count")
                .first("file_count").as("file_count")
                .first("new_defect_count").as("new_defect_count")
                .first("history_defect_count").as("history_defect_count")
                .first("total_new_serious").as("total_new_serious")
                .first("total_new_normal").as("total_new_normal")
                .first("total_new_prompt").as("total_new_prompt")
                .first("total_serious").as("total_serious")
                .first("total_normal").as("total_normal")
                .first("total_prompt").as("total_prompt")
                .first("total_defect_count").as("total_defect_count");
        Aggregation agg = Aggregation.newAggregation(match, sort, group);

        AggregationResults<LintStatisticModel> queryResult =
                mongoTemplate.aggregate(agg, "t_lint_statistic", LintStatisticModel.class);
        return queryResult.getMappedResults();
    }


    /**
     * 以规则维度统计告警数
     *
     * @param taskIdSet 任务ID集合
     * @param toolName  工具名
     * @param status    告警状态
     * @return list
     */
    public List<LintDefectV2Model> findDefectByGroupChecker(Collection<Long> taskIdSet, String toolName,
            Integer status, Long startTime, Long endTime) {
        MatchOperation match = Aggregation
                .match(Criteria.where("task_id").in(taskIdSet).and("tool_name").is(toolName).and("status").is(status));

        // 选填筛选
        List<Criteria> criteriaList = Lists.newArrayList();
        if (startTime != 0 && endTime != 0) {
            // 按告警状态匹配对应时间字段
            String timeField = ConvertUtil.timeField4DefectStatus(status);
            criteriaList.add(Criteria.where(timeField).gte(startTime).lte(endTime));
        }

        Criteria criteria = new Criteria();
        if (CollectionUtils.isNotEmpty(criteriaList)) {
            criteria.andOperator(criteriaList.toArray(new Criteria[0]));
        }
        MatchOperation afterMatch = Aggregation.match(criteria);

        SortOperation sort = Aggregation.sort(Sort.Direction.ASC, "task_id");

        // 以规则为维度统计告警数
        GroupOperation group = Aggregation.group("task_id", "tool_name", "checker")
                .first("task_id").as("task_id")
                .first("tool_name").as("tool_name")
                .first("checker").as("checker")
                .first("severity").as("severity")
                .count().as("line_num");

        // 允许磁盘操作(支持较大数据集合的处理)
        AggregationOptions options = new AggregationOptions.Builder().allowDiskUse(true).build();
        Aggregation agg = Aggregation.newAggregation(match, afterMatch, group, sort).withOptions(options);

        return mongoTemplate.aggregate(agg, "t_lint_defect_v2", LintDefectV2Model.class).getMappedResults();
    }


    /**
     * 批量查询告警列表
     *
     * @param taskIdSet 任务ID集合
     * @param toolName  工具名
     * @param status    告警状态
     * @param timeField 时间筛选字段 create_time,fixed_time,ignore_time,exclude_time
     * @param isNot     告警状态是否取反
     * @return list
     */
    public List<LintDefectV2Model> batchQueryDefect(Collection<Long> taskIdSet, String toolName, Integer status,
            String timeField, Long startTime, Long endTime, boolean isNot) {
        // 索引筛选1
        List<Criteria> criteriaList1 = Lists.newArrayList();
        criteriaList1.add(Criteria.where("task_id").in(taskIdSet).and("tool_name").is(toolName));

        if (status != null && status != 0) {
            if (isNot) {
                criteriaList1.add(Criteria.where("status").ne(status));
            } else {
                criteriaList1.add(Criteria.where("status").is(status));
            }
        }

        Criteria criteria1 = new Criteria();
        if (CollectionUtils.isNotEmpty(criteriaList1)) {
            criteria1.andOperator(criteriaList1.toArray(new Criteria[0]));
        }
        MatchOperation match1 = Aggregation.match(criteria1);

        // 选填筛选2
        List<Criteria> criteriaList2 = Lists.newArrayList();
        if (StringUtils.isNotBlank(timeField)) {
            criteriaList2.add(Criteria.where(timeField).gte(startTime).lte(endTime));
        }

        Criteria criteria2 = new Criteria();
        if (CollectionUtils.isNotEmpty(criteriaList2)) {
            criteria2.andOperator(criteriaList1.toArray(new Criteria[0]));
        }
        MatchOperation match2 = Aggregation.match(criteria2);

        GroupOperation group = Aggregation.group("task_id", "severity")
                .first("task_id").as("task_id")
                .first("severity").as("severity")
                .count().as("line_num");

        AggregationOptions options = new AggregationOptions.Builder().allowDiskUse(true).build();
        Aggregation agg = Aggregation.newAggregation(match1, match2, group).withOptions(options);

        return mongoTemplate.aggregate(agg, "t_lint_defect_v2", LintDefectV2Model.class).getMappedResults();
    }


}
