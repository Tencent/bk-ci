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

package com.tencent.devops.common.cos.util;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ParamsUtil {
    private final static String DEFAULT_ENCODE = "UTF-8";

    /**
     * 获取 params 用来签名的键值对字符串
     * "参数的 key 和 value 都必须经过 URL Encode，如果有多个参数对可使用 & 连接，必须转为小写字符"
     * @param params 键值对
     * @return 字符串
     */
    public static String getQueryParamSignString(final Map<String, String> params) {
        if (params != null && !params.isEmpty()) {
            List<String> segments = new LinkedList<>();
            params.forEach((k, v) -> segments.add(String.format("%s=%s", urlEncode(k.toLowerCase()).toLowerCase(), StringUtils.isEmpty(v) ? "" : urlEncode(v.toLowerCase()).toLowerCase())));
            return StringUtils.join(segments, "&");
        } else {
            return "";
        }
    }

    public static String getQueryParamKeyListString(final Map<String, String> params) {
        if (params != null && !params.isEmpty()) {
            List<String> segments = new LinkedList<>();
            params.forEach((k, v) -> segments.add(k.toLowerCase()));
            return StringUtils.join(segments, ";");
        } else {
            return "";
        }
    }

    /**
     * 获取用来提交URL部分的键值对查询字符串
     * @param params 键值对
     * @return 查询字符串
     */
    public static String getQueryParamString(final Map<String, String> params) {
        if (params != null && !params.isEmpty()) {
            //对于 Append Object 操作，需形式为 "append&position=0"
            List<String> segments = new LinkedList<>();
            params.forEach((k, v) -> segments.add(
                    StringUtils.isEmpty(v) ? k : String.format("%s=%s", k, StringUtils.isEmpty(v) ? "" : urlEncode(v)))
            );
            //返回 key 和 value 都须为小写
            return StringUtils.join(segments, "&").toLowerCase();
        } else {
            return "";
        }
    }

    /**
     * 获取 header 用来签名的键值对字符串, 不同于 getQueryParamSignString() 过程
     * "头部的 key 必须全部小写，value 必须经过 URL Encode"
     * @param headers 键值对
     * @return 字符串
     */
    public static String getHeaderSignString(final Map<String, String> headers) {
        if (headers != null && !headers.isEmpty()) {
            List<String> segments = new LinkedList<>();
            headers.forEach((k, v) -> segments.add(String.format("%s=%s", k.toLowerCase(), StringUtils.isEmpty(v) ? "" : urlEncode(v))));
            return StringUtils.join(segments, "&");
        } else {
            return "";
        }
    }

    public static String urlEncode(final String str) {
        try {
            //原 JDK 中的 java.net.URLEncoder 与 COS 平台的不一致，需要特殊处理
            return UrlEncoderLowercase.encode(str, DEFAULT_ENCODE).replace("+", "%20");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }
}
