/*
 * Copyright (c) 2022 Astroline All rights reserved.
 *
 * @date: 8/11/22, 2:09 AM
 * @author: Astroline <Astroline_kamu@outlook.com>
 *
 * https://niyredra.com
 *
 * 在下鸭爪，全宇宙最凶狠的龙！
 * 嗷～
 */

package com.viyunsion.cefc.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.viyunsion.cefc.modules.sys.model.SysUserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Niyredra Astroline_kamu@outlook.com
 */
@Mapper
public interface SysUserRoleDao extends BaseMapper<SysUserRole> {

    /**
     *
     * 根据用户ID获取角色ID列表
     * @param userId 用户Id
     * @return List Long 角色相关所有全县
     */
    List<Long> listRoleId(Long userId);

    /**
     *
     * 根据角色ID数组批量删除
     * @param roleIds 权限（多个权限）
     * @return int
     */
    int removeBatchByRoleIds(Long[] roleIds);



}
