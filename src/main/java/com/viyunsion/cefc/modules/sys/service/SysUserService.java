/*
 * Copyright (c) 2022 Astroline All rights reserved.
 *
 * @date: 6/29/22, 8:19 PM
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
import com.viyunsion.cefc.modules.sys.model.SysUser;

import java.util.List;
import java.util.Map;

/**
 * 系统用户
 *
 * @author Niyredra Astroline_kamu@outlook.com
 */
public interface SysUserService extends IService<SysUser> {

    PageHelper queryPage(Map<String, Object> params);

    void saveUser(SysUser user);

    void update(SysUser user);

    void deleteBatch(Long[] userIds);

    /**
     * 查询用户的所有权限
     *
     * @param userId 用户ID
     * @return PermsList by userId
     */
    List<String> listAllPerms(Long userId);

    /**
     * 查询用户的所有菜单（返回ID）
     *
     * @param userId 用户ID
     * @return MenuIdList by userId
     */
    List<Long> listAllMenuId(Long userId);

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return User by username
     */
    SysUser getByUsername(String username);

    /**
     * 修改密码
     *
     * @param userId 用户ID
     * @param password 旧密码
     * @param newPassword 新密码
     * @return 是否成功
     */
    boolean updatePassword(Long userId, String password, String newPassword);
}
