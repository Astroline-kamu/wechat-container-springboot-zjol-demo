/*
 * Copyright (c) 2022 Astroline All rights reserved.
 *
 * @date: 6/30/22, 5:50 PM
 * @author: Astroline <Astroline_kamu@outlook.com>
 *
 * https://niyredra.com
 *
 * 在下鸭爪，全宇宙最凶狠的龙！
 * 嗷～
 */

package com.viyunsion.cefc.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.viyunsion.cefc.modules.sys.model.SysRoleMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 角色菜单对应表
 *
 * @author Niyredra Astroline_kamu@outlook.com
 */
@Mapper
public interface SysRoleMenuDao extends BaseMapper<SysRoleMenu> {

    List<Long> listMenuId(Long roleId);

    int removeBatchByRoleIds(Long[] roleIds);
}
