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

package com.niyredra.notjustnote.modules.app.note.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.niyredra.notjustnote.common.global.Result;
import com.niyredra.notjustnote.modules.app.note.model.Note;
import com.niyredra.notjustnote.modules.app.note.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/note")
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






}
