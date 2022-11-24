/*
 * Copyright (c) 2022 Astroline All rights reserved.
 *
 * @date: 6/17/22, 11:43 AM
 * @author: Astroline <Astroline_kamu@outlook.com>
 *
 * https://niyredra.com
 *
 * 在下鸭爪，全宇宙最凶狠的龙！
 * 嗷～
 */

package com.viyunsion.cefc.common.validator;


import org.apache.commons.lang3.StringUtils;
import com.viyunsion.cefc.common.exception.ArException;

public interface Assert {
    public static void isBlank(String str, String msg) {
        if (StringUtils.isBlank(str)) {
            throw new ArException(msg);
        }
    }

    public static void isNull(Object obj, String msg) {
        if (obj == null) {
            throw new ArException(msg);
        }
    }

}
