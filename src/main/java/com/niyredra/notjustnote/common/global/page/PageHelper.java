/*
 * Copyright (c) 2022 Astroline All rights reserved.
 *
 * @date: 6/25/22, 9:21 PM
 * @author: Astroline <Astroline_kamu@outlook.com>
 *
 * https://niyredra.com
 *
 * 在下鸭爪，全宇宙最凶狠的龙！
 * 嗷～
 */

package com.niyredra.notjustnote.common.global.page;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页工具类
 *
 * @author Niyredra Astroline_kamu@outlook.com
 */
@Data
public class PageHelper implements Serializable {
    private static final long serialVersionUID = 1L;

    private int totalCount;

    private int pageSize;

    private int totalPage;

    private int curPage;

    private List<?> list;

    /**
     * 分页构造函数
     *
     * @param list          数据列表
     * @param totalCount    总页数量
     * @param pageSize      每页大小
     * @param curPage       当前页面
     */
    public PageHelper(List<?> list, int totalCount, int pageSize, int curPage) {
        this.list = list;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.curPage = curPage;
        this.totalPage = (int) Math.ceil((double) totalCount / pageSize);
    }

    public PageHelper(IPage<?> page){
        this.list = page.getRecords();
        this.totalCount = (int) page.getTotal();
        this.pageSize = (int) page.getSize();
        this.curPage = (int) page.getCurrent();
        this.totalPage = (int) page.getPages();
    }

}
