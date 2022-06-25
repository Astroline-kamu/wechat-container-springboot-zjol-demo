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

package com.niyredra.notjustnote.modules.app.article;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Article {

    @TableId
    Integer ArticleId;

    String title;
    String author;





























































    String content;

    /* 标签 */
    String tag;
    String tagId;

    /* 文章列表 */
    String artifact;
    String artifactId;

    /* 分组 */
    String group;
    String groupId;



    String version;


}
