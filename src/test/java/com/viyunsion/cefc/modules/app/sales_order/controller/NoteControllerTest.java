/*
 * Copyright (c) 2022 Astroline All rights reserved.
 *
 * @date: 11/17/22, 9:57 PM
 * @author: Astroline <Astroline_kamu@outlook.com>
 *
 * https://niyredra.com
 *
 * 在下鸭爪，全宇宙最凶狠的龙！
 * 嗷～
 */

package com.viyunsion.cefc.modules.app.sales_order.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.viyunsion.cefc.NotJustNoteApplication;
import com.viyunsion.cefc.common.adapter.gson.DateTypeAdapter;
import com.viyunsion.cefc.modules.app.sales_order.common.chunk.model.PojoChunk;
import com.viyunsion.cefc.modules.app.sales_order.model.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;


//@SpringBootTest
class NoteControllerTest {

    @Test
    void listNote() throws IOException {


        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateTypeAdapter()).create();

        StringBuffer sb = new StringBuffer();

        try (
                FileReader fileReader = new FileReader(
                Objects.requireNonNull(NotJustNoteApplication.class.getClassLoader()
                        .getResource("TestOrder.json")).getFile())
        ){
        int len;
        char[] content = new char[1024];
            while ((len = fileReader.read(content)) != -1)
                sb.append(content, 0, len);
        }

        List<Order> orderList = gson.fromJson(sb.toString(), new TypeToken<List<Order>>() {}.getType());
        PojoChunk<Order> chunk = new PojoChunk<>(orderList, Order.class);

        chunk.classifyBy(List.of("oilName", "Area", "port"));

        orderList.forEach(System.out::println);



    }

    @Test
    void listData() {

        String str = "getSomething";
        System.out.println(str.substring(3, 4).toLowerCase(Locale.ROOT) + str.substring(4));
//        Set<String>

    }

    /**
     *
     * 数据转换为一个二维数组 顺序根据一个字典进行排列
     */
    private void DataFormatter(){

        // 传入参数

        // 将数据按照透视内容转换成有顺序的列表

        // 用LinkedHashSet去重

        //
    }
}