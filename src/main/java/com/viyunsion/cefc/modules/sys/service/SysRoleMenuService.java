/*
 * Copyright (c) 2022 Astroline All rights reserved.
 *
 * @date: 6/27/22, 1:28 PM
 * @author: Astroline <Astroline_kamu@outlook.com>
 *
 * https://niyredra.com
 *
 * 在下鸭爪，全宇宙最凶狠的龙！
 * 嗷～
 */

package com.viyunsion.cefc.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.viyunsion.cefc.modules.sys.model.SysRoleMenu;

import java.util.List;

/**
 * 角色菜单关系表
 *
 * @author Niyredra Astroline_kamu@outlook.com
 */
public interface SysRoleMenuService extends IService<SysRoleMenu> {

    /**
     * 保存或更新 删除所有相关内容，然后重新写入
     *
     * @param roleId 角色ID
     * @param menuIdList MenuIdList
     */
    void saveOrUpdate(Long roleId, List<Long> menuIdList);

    /**
     * 根据角色ID获取菜单ID列表
     *
     * @param roleId 角色ID
     * @return MenuIdList by roleId
     */
    List<Long> listMenuIdList(Long roleId);

    /**
     * 根据RoleID列表批量删除
     *
     * @param roleIds 角色ID列表
     * @return isSuccess ... or times? ...todo make it sure
     */
    int removeBatchByRoleIds(Long[] roleIds);

}
