/*
 * Copyright (c) 2022 Astroline All rights reserved.
 *
 * @date: 11/22/22, 11:51 AM
 * @author: Astroline <Astroline_kamu@outlook.com>
 *
 * https://niyredra.com
 *
 * 在下鸭爪，全宇宙最凶狠的龙！
 * 嗷～
 */

package com.viyunsion.cefc.modules.app.sales_order.common.chunk.helper;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

class PojoChunkHelperTest {

    @Test
    void sliceCks() {
        Map<String, String> map = new LinkedHashMap<>(){{
            put("Eve", "1");
            put("Niyredra", "2");
            put("Astroline", "3");
            put("Asyrerina", "4");
            put("Vorexpoter", "5");
            put("Ticporrope", "6");
        }};

        // 原始顺序
        Set<Map.Entry<String, String>> set = map.entrySet();

        System.out.println(set);
        set.forEach(System.out::println);



    }

}