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

package com.niyredra.notjustnote.common.validator;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.niyredra.notjustnote.common.exception.ArException;

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
