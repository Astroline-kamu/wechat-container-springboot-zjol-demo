/*
 * Copyright (c) 2022 Astroline All rights reserved.
 *
 * @date: 6/26/22, 12:30 PM
 * @author: Astroline <Astroline_kamu@outlook.com>
 *
 * https://niyredra.com
 *
 * 在下鸭爪，全宇宙最凶狠的龙！
 * 嗷～
 */

package com.viyunsion.cefc.common.xss;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

class SQLFilterTest {

    @Test
    void injectFilter(){
        String sql = " form \\'' \" test result";
        System.out.println(SQLFilter.injectFilter(sql));
    }

    @Test
    void isBlankTest() {

        String $val1 = null;
        String $val2 = "";

        // String为空的Blank测试

        // Error
//        System.out.println($val1.isBlank());

        // true
        System.out.println($val2.isBlank());

        // true
        System.out.println(StringUtils.isBlank($val1));
    }
}