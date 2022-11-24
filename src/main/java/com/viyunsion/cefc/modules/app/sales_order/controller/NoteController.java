/*
 * Copyright (c) 2022 Astroline All rights reserved.
 *
 * @date: 4/2/22, 3:57 PM
 * @author: Astroline <Astroline_kamu@outlook.com>
 *
 * https://niyredra.com
 *
 * 在下鸭爪，全宇宙最凶狠的龙！
 * 嗷～
 */

package com.viyunsion.cefc.modules.app.sales_order.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.viyunsion.cefc.common.global.Result;
import com.viyunsion.cefc.modules.app.sales_order.model.Note;
import com.viyunsion.cefc.modules.app.sales_order.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/test")
public class NoteController {

    @Autowired
    NoteService noteService;

    @GetMapping("/listNote")
    public Result listNote(String title) {

        // sql 注入测试
        return Result.ok().put("data",
                noteService.list(
                        new QueryWrapper<Note>()
                                .eq("title", title)
                )
        );
    }

    @GetMapping("/listData")
    public Result listData(@RequestParam("pageSize") Integer pageSize, @RequestParam("curPage") Integer curPage){


        // 获取数据透视图的时候，获取到字段列表 考虑到SQL注入问题

        System.out.println(pageSize);
        System.out.println(curPage);

        List<HashMap<String, String>> dataList = new ArrayList<>();

        String[] el = new String[]{"Astroline", "18", "Eve", "11", "Niyredra", "7072", "Yorgiisdra", "16279999802"};

        for (int i = 0; i < pageSize;i++) {
            dataList.add(new HashMap<>(){{
                put("name", el[new Random().nextInt(el.length)]);
                put("age", el[new Random().nextInt(el.length)]);
                put("introduce", "这是一只野生的" + el[new Random().nextInt(el.length)]);
            }});
        }

        return Result.ok().put("data", dataList);

    }

    @GetMapping("/listByField")
    public Result listByField(String field){

        List<String> fieldList = new ArrayList<>(List.of("note_id", "title"));

        System.out.println(fieldList.toString());

        Map<String, Object> map = new HashMap<>(){{
            put("author", "Astroline");
            put("create_time", null);
        }};

        return Objects.requireNonNull(Result.ok().put("field", fieldList)).put("list", noteService.listByMap(map));
    }

}
