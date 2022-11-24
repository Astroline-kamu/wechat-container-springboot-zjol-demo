/*
 * Copyright (c) 2022 Astroline All rights reserved.
 *
 * @date: 6/30/22, 8:32 PM
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
import com.viyunsion.cefc.modules.sys.dao.SysRoleDao;
import com.viyunsion.cefc.modules.sys.dao.SysUserDao;
import com.viyunsion.cefc.modules.sys.model.SysRole;
import com.viyunsion.cefc.modules.sys.service.SysRoleMenuService;
import com.viyunsion.cefc.modules.sys.service.SysRoleService;
import com.viyunsion.cefc.modules.sys.service.SysUserRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 角色业务
 *
 * @author Niyredra Astroline_kamu@outlook.com
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRole> implements SysRoleService {

    // todo 这是个不合理的设计
    private final SysUserDao userDao;
    private final SysRoleMenuService roleMenuService;
    private final SysUserRoleService userRoleService;

    public SysRoleServiceImpl(SysRoleMenuService roleMenuService, SysUserDao userDao, SysUserRoleService userRoleService) {
        this.roleMenuService = roleMenuService;
        this.userDao = userDao;
        this.userRoleService = userRoleService;
    }

    @Override
    public PageHelper queryPage(Map<String, Object> params) {
        String roleName = (String) params.get("roleName");
        Long createUserId = (Long) params.get("createUserId");

        return new PageHelper(
                this.page(
                        new Query<SysRole>().getPage(params),
                        new QueryWrapper<SysRole>()
                                // param1: condition something.doIt();
                                .like(StringUtils.isNotBlank(roleName), "role_name", roleName)
                                .eq(createUserId != null, "create_user_id", createUserId)
                )
        );
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insert(SysRole role) {
        checkPermission(role);
        save(role);
        // 更新角色与菜单关系
        roleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modify(SysRole role) {
        checkPermission(role);
        updateById(role);
        roleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] roleIds) {
        // 删除角色
        this.removeByIds(Arrays.asList(roleIds));

        // 删除角色与菜单关联
        roleMenuService.removeBatchByRoleIds(roleIds);

        // 删除角色与用户关联
        userRoleService.removeBatchByRoleIds(roleIds);
    }

    @Override
    public List<Long> queryRoleIdList(Long createUserId) {
        return baseMapper.queryRoleIdList(createUserId);
    }


    /**
     * 检查是否有操作权限的相关权限
     *
     * @param role SysRole
     */
    private void checkPermission(SysRole role) {
        if (role.getCreateUserId() == UserConstant.SUPER_ADMIN_ID) return;

        List<Long> menuIdList = userDao.queryAllMenuId(role.getCreateUserId());
        if (!menuIdList.containsAll(role.getMenuIdList()))
            throw new ArException("你没有新增角色权限的权限");
    }

}
