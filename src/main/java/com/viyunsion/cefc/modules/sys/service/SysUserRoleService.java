/*
 * Copyright (c) 2022 Astroline All rights reserved.
 *
 * @date: 6/27/22, 2:21 PM
 * @author: Astroline <Astroline_kamu@outlook.com>
 *
 * https://niyredra.com
 *
 * 在下鸭爪，全宇宙最凶狠的龙！
 * 嗷～
 */

package com.viyunsion.cefc.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.viyunsion.cefc.modules.sys.model.SysUserRole;

import java.util.List;

/**
 * 用户角色表
 *
 * @author Niyredra Astroline_kamu@outlook.com
 */
public interface SysUserRoleService extends IService<SysUserRole> {

    void saveOrUpdate(Long userId, List<Long> roleIdList);

    int removeBatchByRoleIds(Long[] roleIds);

    /**
     * 根据用户ID获取角色ID列表
     *
     * @param userId 用户ID
     * @return RoleIdList by userId
     */
    List<Long> listRoleId(Long userId);

}
