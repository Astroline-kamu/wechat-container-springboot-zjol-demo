/*
 * Copyright (c) 2022 Astroline All rights reserved.
 *
 * @date: 6/30/22, 5:20 PM
 * @author: Astroline <Astroline_kamu@outlook.com>
 *
 * https://niyredra.com
 *
 * 在下鸭爪，全宇宙最凶狠的龙！
 * 嗷～
 */

package com.viyunsion.cefc.common.constant;

/**
 * 菜单类型
 *
 * @author Niyredra Astroline_kamu@outlook.com
 */
public enum MenuType {
    CATALOG(0),
    MENU(1),
    BUTTON(2);

    private final int value;

    MenuType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
