/*
 * Copyright (c) 2022 Astroline All rights reserved.
 *
 * @date: 6/21/22, 10:52 AM
 * @author: Astroline <Astroline_kamu@outlook.com>
 *
 * https://niyredra.com
 *
 * 在下鸭爪，全宇宙最凶狠的龙！
 * 嗷～
 */

package com.niyredra.notjustnote.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.niyredra.notjustnote.modules.sys.model.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 角色权限
 *
 * @author Niyredra Astroline_kamu@outlook.com
 */
@Mapper
public interface SysRoleDao extends BaseMapper<SysRole> {

    /**
     * 查询用户创建的角色ID列表
     *
     * @param createUserId 创建者id
     * @return Id列表
     */
    List<Long> queryRoleIdList(Long createUserId);

}
