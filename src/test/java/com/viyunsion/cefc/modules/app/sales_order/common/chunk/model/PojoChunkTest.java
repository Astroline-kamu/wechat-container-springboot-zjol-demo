/*
 * Copyright (c) 2022 Astroline All rights reserved.
 *
 * @date: 11/21/22, 6:58 PM
 * @author: Astroline <Astroline_kamu@outlook.com>
 *
 * https://niyredra.com
 *
 * 在下鸭爪，全宇宙最凶狠的龙！
 * 嗷～
 */

package com.viyunsion.cefc.modules.app.sales_order.common.chunk.model;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;


/**
 *
 * Airstrike
 */
class PojoChunkTest {

    @Test
    public void list2ArrayTest(){
        ArrayList<String> list = new ArrayList<>(List.of("Eve", "Astroline", "Niyredra", "Lor", "Astroline"));
        String[] array = list.toArray(String[]::new);

        array[1] = "Astrikube";

        System.out.println(Arrays.toString(array));
        System.out.println(list);
        // 结论 toArray不需要clone，自动克隆

        System.out.println("-----------------------------------------------------------");

        System.out.println(list.stream().collect(Collectors.toCollection(LinkedHashSet::new)).toString());
        System.out.println(new LinkedHashSet<>(list));


    }


    @Test
    void classify() {
        System.out.println(Arrays.toString(new int[4]));
        List<Object> objectList = new ArrayList<>();
        objectList.add("test1");
        objectList.add(objectList);

        System.out.println(objectList.get(0).hashCode() == "test1".hashCode());  // true
        System.out.println(objectList.get(1).hashCode());  // tackOverflowError
    }


    @Test
    void classify2() {
        System.out.println(Arrays.toString(new int[4]));
        List<Object> objectList = new ArrayList<>();
        objectList.add("test1");

        List<Object> test = new ArrayList<>();
        objectList.add(test);

        System.out.println(objectList.get(0).hashCode() == "test1".hashCode());  // true
        System.out.println(objectList.get(1).hashCode() == test.hashCode());  // true
        test.add(1);
        System.out.println(objectList.get(1) == test);  // true
    }

}