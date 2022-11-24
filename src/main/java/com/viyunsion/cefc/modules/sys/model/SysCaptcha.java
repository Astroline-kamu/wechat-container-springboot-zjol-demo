/*
 * Copyright (c) 2022 Astroline All rights reserved.
 *
 * @date: 6/17/22, 9:08 AM
 * @author: Astroline <Astroline_kamu@outlook.com>
 *
 * https://niyredra.com
 *
 * 在下鸭爪，全宇宙最凶狠的龙！
 * 嗷～
 */

package com.viyunsion.cefc.modules.sys.model;

import lombok.Data;

import java.util.Date;

@Data
public class SysCaptcha {
    private String uuid;
    private String code;
    private Date expireTime;
}
