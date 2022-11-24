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
package com.viyunsion.cefc.common.datasource.filter;

import com.alibaba.druid.filter.FilterEventAdapter;
import com.alibaba.druid.proxy.jdbc.StatementProxy;


// 将类注入Bean中启用sql过滤 具体可配置参数可以查看FilterEventAdapter源码
// 默认的过滤器可以通过sqlmap level 5的测试emmmmmmm
//@Configuration
public class SimpleFilter extends FilterEventAdapter {
    @Override
    public void statementExecuteBefore(StatementProxy statement, String sql){

        System.out.println(sql);
        statement.getParameters().forEach((k, v) -> System.out.println(k + " --- " + v.getValue()));
    }
}
