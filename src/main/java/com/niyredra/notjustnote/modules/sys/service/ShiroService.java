/*
 * Copyright (c) 2022 Astroline All rights reserved.
 *
 * @date: 6/17/22, 12:52 PM
 * @author: Astroline <Astroline_kamu@outlook.com>
 *
 * https://niyredra.com
 *
 * 在下鸭爪，全宇宙最凶狠的龙！
 * 嗷～
 */

package com.niyredra.notjustnote.modules.sys.service;

import com.niyredra.notjustnote.modules.sys.model.SysUser;
import com.niyredra.notjustnote.modules.sys.model.SysUserToken;

import java.util.Set;

public interface ShiroService {

    /**
     *
     * 获取用户权限
     * @param userId 用户Id
     * @return {String Set} PermissionList
     */
    Set<String> getUserPermissions(long userId);

    /**
     *
     * 用Token查询出用户信息
     * @param token 用户Token
     * @return SysUserToken
     */
    SysUserToken queryByToken(String token);

    /**
     *
     * 根据用户Id查询用户
     * @param userId 用户Id
     * @return SysUser
     */
    SysUser queryUser(Long userId);
}
