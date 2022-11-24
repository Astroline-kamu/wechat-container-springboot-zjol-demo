/*
 * Copyright (c) 2022 Astroline All rights reserved.
 *
 * @date: 6/27/22, 1:24 PM
 * @author: Astroline <Astroline_kamu@outlook.com>
 *
 * https://niyredra.com
 *
 * 在下鸭爪，全宇宙最凶狠的龙！
 * 嗷～
 */

package com.viyunsion.cefc.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.viyunsion.cefc.common.constant.MenuType;
import com.viyunsion.cefc.common.constant.UserConstant;
import com.viyunsion.cefc.common.utils.CollectionUtils;
import com.viyunsion.cefc.modules.sys.dao.SysMenuDao;
import com.viyunsion.cefc.modules.sys.model.SysMenu;
import com.viyunsion.cefc.modules.sys.service.SysMenuService;
import com.viyunsion.cefc.modules.sys.service.SysRoleMenuService;
import com.viyunsion.cefc.modules.sys.service.SysUserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 菜单类
 *
 * @author Niyredra Astroline_kamu@outlook.com
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenu> implements SysMenuService {

    private final SysUserService userService;

    private final SysRoleMenuService roleMenuService;

    public SysMenuServiceImpl(SysUserService userService, SysRoleMenuService roleMenuService) {
        this.userService = userService;
        this.roleMenuService = roleMenuService;
    }


    @Override
    public List<SysMenu> listByParentId(Long parentId, List<Long> menuIdList) {
        List<SysMenu> menuList = listByParentId(parentId);
        if (menuIdList == null) return menuList;
        return CollectionUtils.intersection(menuList, menuIdList,
                (menu, ids) -> ids.equals(menu.getMenuId()));
    }

    @Override
    public List<SysMenu> listByParentId(Long parentId) {
        return baseMapper.listByParentId(parentId);
    }

    @Override
    public List<SysMenu> listNotButton() {
        return baseMapper.listNotButton();
    }

    @Override
    public List<SysMenu> listUserMenu(Long userId) {
        // 超级管理员
        if (userId == UserConstant.SUPER_ADMIN_ID)
            return getAllMenu(null);

        // 用户菜单列表
        return getAllMenu(userService.listAllMenuId(userId));
    }


    @Override
    public void delete(Long menuId) {
        this.removeById(menuId);
        // 删除菜单与角色关联项  // todo 理论上在sql中将它设为外键并设置CASCADE ... 你会相信SQL的事物么
        roleMenuService.removeByMap(
                new HashMap<>() {{
                    put("menu_id", menuId);
                }}
        );
    }

    private List<SysMenu> getAllMenu(List<Long> menuIdList) {
        // todo 确定根菜单只能是0
        List<SysMenu> menuList = listByParentId(0L, menuIdList);
        // 递归获取子菜单
        getMenuTree(menuList, menuIdList);

        return menuList;
    }

    private List<SysMenu> getMenuTree(List<SysMenu> menuList, List<Long> menuIdList) {
        List<SysMenu> subMenuList = new ArrayList<>();
        for (SysMenu menu : menuList) {
            if (menu.getType() == MenuType.CATALOG.getValue()) {
                menu.setList(
                        getMenuTree(listByParentId(menu.getMenuId(), menuIdList), menuIdList)
                );
                subMenuList.add(menu);
            }
        }
        return subMenuList;
    }


}
