/*
 * Copyright (c) 2022 Astroline All rights reserved.
 *
 * @date: 6/25/22, 9:52 PM
 * @author: Astroline <Astroline_kamu@outlook.com>
 *
 * https://niyredra.com
 *
 * 在下鸭爪，全宇宙最凶狠的龙！
 * 嗷～
 */

package com.viyunsion.cefc.common.global.page;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.viyunsion.cefc.common.xss.SQLFilter;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * 分页工具 在分页查询之前的过滤器
 *
 * @author Niyredra Astroline_kamu@outlook.com
 */
public class Query<T> {

    /**
     * 分页
     *
     * @param params 是一个标准列表 一般情况下只有前两个字段
     *               page   - 第n页
     *               limit  - 该页数据条数
     *               si     - 被排序列（字段头）
     *               order  - 排序模式 ASC or DESC
     * @return Page 翻页结果
     */
    public IPage<T> getPage(Map<String, Object> params) {
        return getPage(params, null, false);
    }

    /**
     * 分页
     *
     * @param params 是一个标准列表 一般情况下只有前两个字段
     *               page   - 第n页
     *               limit  - 该页数据条数
     *               si     - 被排序列（字段头）
     *               order  - 排序模式 ASC or DESC
     * @param defaultOrderField 默认排序方案
     * @param iaAsc 是否为升序排序
     * @return Page 翻页结果
     */
    public IPage<T> getPage(Map<String, Object> params, String defaultOrderField, boolean iaAsc) {
        long curPage = 1;
        long limit = 10;

        if (params.get(PageConstant.PAGE) != null)
            curPage = Long.parseLong((String) params.get(PageConstant.PAGE));
        if (params.get(PageConstant.LIMIT) != null)
            limit = Long.parseLong((String) params.get(PageConstant.LIMIT));

        // 分页
        Page<T> page = new Page<>(curPage, limit);
        params.put(PageConstant.PAGE, page);

        // 排序 防止SQL注入
        String orderField = SQLFilter.injectFilter((String) params.get(PageConstant.ORDER_FIELD));
        String order = (String) params.get(PageConstant.ORDER);

        if (StringUtils.isNotEmpty(orderField)
                && StringUtils.isNotEmpty(order))
            if (PageConstant.ASC.equalsIgnoreCase(order))
                return page.addOrder(OrderItem.asc(orderField));
            else return page.addOrder(OrderItem.desc(orderField));

        if (StringUtils.isBlank(defaultOrderField)) return page;
        if (iaAsc) page.addOrder(OrderItem.asc(orderField));
        else page.addOrder(OrderItem.desc(orderField));

        return page;
    }

}
