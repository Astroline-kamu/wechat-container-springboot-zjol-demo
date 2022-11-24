/*
 * Copyright (c) 2022 Astroline All rights reserved.
 *
 * @date: 8/11/22, 2:07 AM
 * @author: Astroline <Astroline_kamu@outlook.com>
 *
 * https://niyredra.com
 *
 * 在下鸭爪，全宇宙最凶狠的龙！
 * 嗷～
 */

package com.viyunsion.cefc.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.viyunsion.cefc.modules.sys.dao.SysUserRoleDao;
import com.viyunsion.cefc.modules.sys.model.SysUserRole;
import com.viyunsion.cefc.modules.sys.service.SysUserRoleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Niyredra Astroline_kamu@outlook.com
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleDao, SysUserRole> implements SysUserRoleService {
    @Override
    public void saveOrUpdate(Long userId, List<Long> roleIdList) {
        this.removeByMap(
                new HashMap<>() {{
                    put("user_id", userId);
                }}
        );
        if (roleIdList == null || roleIdList.size() == 0) return ;

        List<SysUserRole> userRoleList = new ArrayList<>();
        for (Long roleId
                : roleIdList) {
            SysUserRole userRole = new SysUserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(roleId);
            userRoleList.add(userRole);
        }
        this.saveBatch(userRoleList);
    }

    @Override
    public int removeBatchByRoleIds(Long[] roleIds) {
        return baseMapper.removeBatchByRoleIds(roleIds);
    }

    @Override
    public List<Long> listRoleId(Long userId) {
        return baseMapper.listRoleId(userId);
    }
}
