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

package com.niyredra.notjustnote.modules.app.note.model;

import lombok.Data;

import java.sql.Date;

@Data
public class Note {

    Integer noteId;

    String title;
    String author;
    String content;

    String description;

    Date createTime;
    Date updateTime;
}
