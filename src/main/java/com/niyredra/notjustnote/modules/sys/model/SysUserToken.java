/*
 * Copyright (c) 2022 Astroline All rights reserved.
 *
 * @date: 6/17/22, 9:16 AM
 * @author: Astroline <Astroline_kamu@outlook.com>
 *
 * https://niyredra.com
 *
 * 在下鸭爪，全宇宙最凶狠的龙！
 * 嗷～
 */

package com.niyredra.notjustnote.modules.sys.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysUserToken implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.INPUT)
    private Long userId;
    private String token;
    private Date expireTime;
    private Date updateTime;
}
