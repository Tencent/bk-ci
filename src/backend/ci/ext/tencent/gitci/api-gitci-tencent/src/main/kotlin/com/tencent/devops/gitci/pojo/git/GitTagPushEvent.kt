/*
 * Tencent is pleased to support the open source community by making BK-CI 蓝鲸持续集成平台 available.
 *
 * Copyright (C) 2019 THL A29 Limited, a Tencent company.  All rights reserved.
 *
 * BK-CI 蓝鲸持续集成平台 is licensed under the MIT license.
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

package com.tencent.devops.gitci.pojo.git

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.tencent.devops.common.ci.OBJECT_KIND_TAG_PUSH

/**
{
    "object_kind": "tag_push",
    "operation_kind": "create",
    "ref": "refs/tags/v1.0.2",
    "before": "1480a4610ca01dd10cb5bc3359a1b1ea568c09a1",
    "after": "b96850262fabfa9a1d9d28fff9040621958379f9",
    "user_id": 11323,
    "user_name": "git_user1",
    "project_id": 11452,
    "repository": {
        "name": "z-413",
        "url": "ssh://git@tencent.com/z-413/tencent.git",
        "description": "",
        "homepage": "http://tencent.com/z-413/tencent",
        "git_http_url":"http://tencent.com/z-413/tencent.git",
        "git_ssh_url":"git@tencent.com:z-413/tencent.git",
        "visibility_level":0
    },
    "commits":[
        {
            "id":"458ce39e1a28572597979ca6c7cdc6c338f6bd43",
            "message":"update",
            "timestamp":"2019-03-28T02:14:59+0000",
            "url":"https://git.test.code.oa.com/release-test/xxx-framework/commit/458ce39e1a28572597979ca6c7cdc6c338f6bd43",
            "author":{
                "name":"xuhaohe",
                "email":"xuhaohe@tencent.com"
            },
            "added":[],
            "modified":[
                "README.md"
            ],
            "removed":[]
        }
    ],
    "total_commits_count":1,
    "create_from":"master"
}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
data class GitTagPushEvent(
    val operation_kind: String,
    val ref: String,
    val before: String,
    val after: String,
    val user_name: String,
    val checkout_sha: String?,
    val project_id: Long,
    val repository: GitCommitRepository,
    val commits: List<GitCommit>,
    val total_commits_count: Int,
    val create_from: String?
) : GitEvent() {
    companion object {
        const val classType = OBJECT_KIND_TAG_PUSH
    }
}