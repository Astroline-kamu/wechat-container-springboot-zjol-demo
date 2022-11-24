/*
 * Copyright (c) 2022 Astroline All rights reserved.
 *
 * @date: 6/27/22, 1:33 PM
 * @author: Astroline <Astroline_kamu@outlook.com>
 *
 * https://niyredra.com
 *
 * 在下鸭爪，全宇宙最凶狠的龙！
 * 嗷～
 */

package com.viyunsion.cefc.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.viyunsion.cefc.common.global.page.PageHelper;
import com.viyunsion.cefc.modules.sys.model.SysRole;

import java.util.List;
import java.util.Map;

/**
 * 账号角色
 *
 * @author Niyredra Astroline_kamu@outlook.com
 */
public interface SysRoleService extends IService<SysRole> {

    /**
     * 分页
     *
     * @param params Map String, Object
     *               roleName       角色名称
     *               createUserId   创建者
     *
     * @return RoleList
     */
    PageHelper queryPage(Map<String, Object> params);

    void insert(SysRole role);

    void modify(SysRole role);

    void deleteBatch(Long[] roleIds);

    /**
     * 查询用户创建的角色ID（列表）
     *
     * @param createUserId 创建者ID
     * @return RoleIdList by createUserId
     */
    List<Long> queryRoleIdList(Long createUserId);
}
