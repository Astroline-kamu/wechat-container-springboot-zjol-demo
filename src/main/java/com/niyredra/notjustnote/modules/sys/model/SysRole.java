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

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class SysRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private long roleId;

    // 角色名称
    @NotBlank(message = "角色名称不能为空")
    private String roleName;

    // 备注
    private String remark;

    // 创建者ID
    private Long createUserId;

    @TableField(exist = false)
    private List<Long> menuIdList;

    private Date createTime;
    private Date updateTime;
}
