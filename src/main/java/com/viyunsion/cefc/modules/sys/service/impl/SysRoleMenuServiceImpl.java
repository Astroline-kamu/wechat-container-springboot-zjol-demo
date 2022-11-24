/*
 * Copyright (c) 2022 Astroline All rights reserved.
 *
 * @date: 6/29/22, 10:56 PM
 * @author: Astroline <Astroline_kamu@outlook.com>
 *
 * https://niyredra.com
 *
 * 在下鸭爪，全宇宙最凶狠的龙！
 * 嗷～
 */

package com.viyunsion.cefc.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.viyunsion.cefc.modules.sys.dao.SysRoleMenuDao;
import com.viyunsion.cefc.modules.sys.model.SysRoleMenu;
import com.viyunsion.cefc.modules.sys.service.SysRoleMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色菜单对应业务
 *
 * @author Niyredra Astroline_kamu@outlook.com
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuDao, SysRoleMenu> implements SysRoleMenuService {


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(Long roleId, List<Long> menuIdList) {
        // 删除然后更新
        removeBatchByRoleIds(new Long[]{roleId});
        if (menuIdList.size() == 0) return ;

        // 保存
        List<SysRoleMenu> sysRoleMenuList = new ArrayList<>();
        for (Long menuId: menuIdList) {
            SysRoleMenu sysRoleMenu = new SysRoleMenu();
            sysRoleMenu.setMenuId(menuId);
            sysRoleMenu.setRoleId(roleId);
            sysRoleMenuList.add(sysRoleMenu);
        }
        this.saveBatch(sysRoleMenuList);
    }

    @Override
    public List<Long> listMenuIdList(Long roleId) {
        return baseMapper.listMenuId(roleId);
    }

    @Override
    public int removeBatchByRoleIds(Long[] roleIds) {
        return baseMapper.removeBatchByRoleIds(roleIds);
    }
}
