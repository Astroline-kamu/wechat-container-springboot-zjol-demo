/*
 * Copyright (c) 2022 Astroline All rights reserved.
 *
 * @date: 11/23/22, 10:07 PM
 * @author: Astroline <Astroline_kamu@outlook.com>
 *
 * https://niyredra.com
 *
 * 在下鸭爪，全宇宙最凶狠的龙！
 * 嗷～
 */

package com.viyunsion.cefc.modules.app.sales_order.common.chunk.worker;

import lombok.SneakyThrows;

import java.util.List;

/**
 * @author Niyredra Astroline_kamu@outlook.com
 */
public interface Classifier {

    @SneakyThrows
    void action(List<Object> source);

}
