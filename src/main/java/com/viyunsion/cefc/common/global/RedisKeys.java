/*
 * Copyright (c) 2022 Astroline All rights reserved.
 *
 * @date: 6/17/22, 1:06 PM
 * @author: Astroline <Astroline_kamu@outlook.com>
 *
 * https://niyredra.com
 *
 * 在下鸭爪，全宇宙最凶狠的龙！
 * 嗷～
 */

package com.viyunsion.cefc.common.global;

public class RedisKeys {

    private static final String SYS_CONFIG_KEY = "sys:config:";

    public static String getSysConfigKey(String key) {
        return SYS_CONFIG_KEY + key;
    }
}
