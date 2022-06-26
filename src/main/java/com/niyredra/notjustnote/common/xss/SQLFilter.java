/*
 * Copyright (c) 2022 Astroline All rights reserved.
 *
 * @date: 6/26/22, 12:28 PM
 * @author: Astroline <Astroline_kamu@outlook.com>
 *
 * https://niyredra.com
 *
 * 在下鸭爪，全宇宙最凶狠的龙！
 * 嗷～
 */

package com.niyredra.notjustnote.common.xss;


import com.niyredra.notjustnote.common.exception.ArException;
import org.apache.commons.lang3.StringUtils;

import java.util.Locale;

/**
 * SQL过滤器
 *
 * @author Niyredra Astroline_kamu@outlook.com
 */
public class SQLFilter {

    /**
     * SQL注入过滤
     *
     * @param str 字符串
     * @return 过滤后的结果
     */
    public static String injectFilter(String str) {
        if (StringUtils.isBlank(str)) return null;

        str = StringUtils.replaceEach(str,
                new String[]{"'", ";", "\"", "\\"},
                new String[]{"", "", "", ""}
        ).toLowerCase(Locale.ROOT);

        // DML & DQL & DDL Keywords
        String[] illegalKeywords =
                {"master", "truncate", "insert", "select", "delete", "update", "declare", "alter", "drop"};

        if (StringUtils.indexOfAny(str, illegalKeywords) != -1)
            throw new ArException("包含非法字符");

        return str;
    }

}
