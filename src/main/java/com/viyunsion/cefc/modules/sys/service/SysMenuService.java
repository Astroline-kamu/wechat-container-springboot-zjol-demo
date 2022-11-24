/*
 * Copyright (c) 2022 Astroline All rights reserved.
 *
 * @date: 6/27/22, 1:17 PM
 * @author: Astroline <Astroline_kamu@outlook.com>
 *
 * https://niyredra.com
 *
 * 在下鸭爪，全宇宙最凶狠的龙！
 * 嗷～
 */

package com.viyunsion.cefc.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.viyunsion.cefc.modules.sys.model.SysMenu;

import java.util.List;

/**
 * 菜单管理
 *
 * @author Niyredra Astroline_kamu@outlook.com
 */
public interface SysMenuService extends IService<SysMenu> {

    /**
     * 根据父级菜单查询子菜单
     * 数据取menuIdList中含有的部分，如果menuIdList为null，则直接返回查询结果
     *
     * @param parentId 父菜单ID
     * @param menuIdList 菜单IDList
     * @return MenuList by parentId in menuList
     */
    List<SysMenu> listByParentId(Long parentId, List<Long> menuIdList);

    /**
     * 根据父级菜单查询子菜单
     *
     * @param parentId 父菜单ID
     * @return MenuList by parentId
     */
    List<SysMenu> listByParentId(Long parentId);

    /**
     *  获取不包含按钮的菜单列表
     *
     * @return NotButton的MenuList(一个神奇的解释)
     */
    List<SysMenu> listNotButton();

    /**
     * 获取用户菜单列表
     *
     * @param userId 用户ID
     * @return MenuList by userId
     */
    List<SysMenu> listUserMenu(Long userId);

    /**
     * 删除
     *
     * @param menuId 菜单ID
     */
    void delete(Long menuId);
}
