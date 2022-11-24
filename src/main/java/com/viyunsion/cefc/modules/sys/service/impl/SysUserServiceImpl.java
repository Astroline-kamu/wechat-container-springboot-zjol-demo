/*
 * Copyright (c) 2022 Astroline All rights reserved.
 *
 * @date: 8/11/22, 2:42 AM
 * @author: Astroline <Astroline_kamu@outlook.com>
 *
 * https://niyredra.com
 *
 * 在下鸭爪，全宇宙最凶狠的龙！
 * 嗷～
 */

package com.viyunsion.cefc.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.viyunsion.cefc.common.constant.UserConstant;
import com.viyunsion.cefc.common.exception.ArException;
import com.viyunsion.cefc.common.global.page.PageHelper;
import com.viyunsion.cefc.common.global.page.Query;
import com.viyunsion.cefc.modules.sys.dao.SysUserDao;
import com.viyunsion.cefc.modules.sys.model.SysUser;
import com.viyunsion.cefc.modules.sys.service.SysRoleService;
import com.viyunsion.cefc.modules.sys.service.SysUserRoleService;
import com.viyunsion.cefc.modules.sys.service.SysUserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 用户
 *
 * @author Niyredra Astroline_kamu@outlook.com
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUser> implements SysUserService {

    private final SysUserRoleService userRoleService;

    private final SysRoleService roleService;

    public SysUserServiceImpl(SysUserRoleService userRoleService, SysRoleService roleService) {
        this.userRoleService = userRoleService;
        this.roleService = roleService;
    }


    @Override
    public PageHelper queryPage(Map<String, Object> params) {
        String username = (String) params.get("username");
        Long createUserId = (Long) params.get("createUserId");

        return new PageHelper(
                this.page(
                        new Query<SysUser>().getPage(params),
                        new QueryWrapper<SysUser>()
                                .like(StringUtils.isNotBlank(username), "username", username)
                                .eq(createUserId != null, "create_user_id", createUserId)
                )
        );
    }

    @Override
    @Transactional
    public void saveUser(SysUser user) {

        user.setCreateTime(new Date());

        // Sha256加密
        String salt = RandomStringUtils.randomAlphanumeric(20);
        user.setPassword(new Sha256Hash(user.getPassword(), salt).toHex());
        user.setSalt(salt);
        this.save(user);

        // 越权检测
        checkRole(user);

        // 保存用户角色关系
        userRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
    }

    @Override
    @Transactional
    public void update(SysUser user) {
        if (StringUtils.isBlank(user.getPassword()))
            user.setPassword(null);
        else user.setPassword(new Sha256Hash(user.getPassword(), user.getSalt()).toHex());
        this.updateById(user);
        checkRole(user);
        userRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
    }

    @Override
    public void deleteBatch(Long[] userIds) {
        this.removeByIds(List.of(userIds));
    }

    @Override
    public List<String> listAllPerms(Long userId) {
        return baseMapper.queryAllPerms(userId);
    }

    @Override
    public List<Long> listAllMenuId(Long userId) {
        return baseMapper.queryAllMenuId(userId);
    }

    @Override
    public SysUser getByUsername(String username) {
        return baseMapper.queryByUserName(username);
    }

    @Override
    public boolean updatePassword(Long userId, String password, String newPassword) {
        SysUser user = new SysUser();
        user.setPassword(newPassword);
        // todo 有改动 验证修改结果是否有被加盐
        return this.update(user,
                new QueryWrapper<SysUser>()
                        .eq("user_id", userId)
                        .eq("password", password)
        );

    }

    private void checkRole(SysUser user) {
        if (user.getRoleIdList() == null || user.getRoleIdList().size() == 0) return ;
        if (user.getCreateUserId() == UserConstant.SUPER_ADMIN_ID) return ;

        List<Long> roleIdList = roleService.queryRoleIdList(user.getCreateUserId());

        if (!roleIdList.containsAll(user.getRoleIdList()))
            throw new ArException("新增用户所选角色非本人创建");
    }


}
