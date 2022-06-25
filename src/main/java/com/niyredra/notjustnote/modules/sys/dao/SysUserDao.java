/*
 * Copyright (c) 2022 Astroline All rights reserved.
 *
 * @date: 6/21/22, 10:57 AM
 * @author: Astroline <Astroline_kamu@outlook.com>
 *
 * https://niyredra.com
 *
 * 在下鸭爪，全宇宙最凶狠的龙！
 * 嗷～
 */

package com.niyredra.notjustnote.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.niyredra.notjustnote.modules.sys.model.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 系统用户
 *
 * @author Niyredra Astroline_kamu@outlook.com
 */
@Mapper
public interface SysUserDao extends BaseMapper<SysUser> {

    /**
     * 查询用户的所有权限
     *
     * @param userId 用户ID
     * @return PermsList
     */
    List<String> queryAllPerms(Long userId);

    /**
     * 查询用户的所有菜单ID
     *
     * @param userId 用户ID
     * @return MenuIdList
     */
    List<Long> queryAllMenuId(Long userId);

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return User
     */
    SysUser queryByUserName(String username);

}
