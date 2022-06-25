/*
 * Copyright (c) 2022 Astroline All rights reserved.
 *
 * @date: 6/17/22, 9:15 AM
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

import java.util.Date;
import java.util.List;

@Data
public class SysMenu {

    // 菜单ID
    @TableId
    private Long menuId;

    // 父菜单ID，一级为0
    private Long parentId;
    // 父菜单名称
    private String parentName;
    // 菜单名称
    private String name;

    // 菜单URL
    private String url;
    // 授权，用逗号分割 栗子🌰：user:list,user:create
    private String perms;

    // 类型：0 目录, 1 菜单, 2 按钮
    private Integer type;
    // 菜单图标
    private String icon;
    // 排序
    private Integer orderNum;

    private Date createTime;
    private Date updateTime;

    // z-tree属性
    @TableField(exist = false)
    private Boolean open;
    @TableField(exist = false)
    private List<?> list;
}
