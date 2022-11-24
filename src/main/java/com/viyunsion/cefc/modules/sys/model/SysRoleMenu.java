/*
 * Copyright (c) 2022 Astroline All rights reserved.
 *
 * @date: 6/17/22, 9:17 AM
 * @author: Astroline <Astroline_kamu@outlook.com>
 *
 * https://niyredra.com
 *
 * 在下鸭爪，全宇宙最凶狠的龙！
 * 嗷～
 */

package com.viyunsion.cefc.modules.sys.model;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class SysRoleMenu implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId
    private Long id;
    // 角色ID
    private Long roleId;
    // 菜单ID
    private Long menuId;
}
