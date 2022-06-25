/*
 * Copyright (c) 2022 Astroline All rights reserved.
 *
 * @date: 6/21/22, 10:48 AM
 * @author: Astroline <Astroline_kamu@outlook.com>
 *
 * https://niyredra.com
 *
 * 在下鸭爪，全宇宙最凶狠的龙！
 * 嗷～
 */

package com.niyredra.notjustnote.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.niyredra.notjustnote.modules.sys.model.SysMenu;

import java.util.List;

/**
 * 菜单
 *
 * @author Niyredra Astroline_kamu@outlook.com
 */
public interface SysMenuDao extends BaseMapper<SysMenu> {

    /**
     * 根据父级菜单获取子菜单
     *
     * @param parentId 父级菜单id
     * @return 菜单列表
     */
    List<SysMenu> queryListParentId(Long parentId);

    /**
     * 获取不包含按钮的菜单列表
     *
     * @return 菜单列表
     */
    List<SysMenu> queryNotButtonList();


}
