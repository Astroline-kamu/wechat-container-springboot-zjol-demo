/*
 * Copyright (c) 2022 Astroline All rights reserved.
 *
 * @date: 6/17/22, 12:59 PM
 * @author: Astroline <Astroline_kamu@outlook.com>
 *
 * https://niyredra.com
 *
 * 在下鸭爪，全宇宙最凶狠的龙！
 * 嗷～
 */

package com.viyunsion.cefc.modules.sys.service.impl;

import com.viyunsion.cefc.modules.sys.model.SysUser;
import com.viyunsion.cefc.modules.sys.model.SysUserToken;
import com.viyunsion.cefc.modules.sys.service.ShiroService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ShiroServiceImpl implements ShiroService {

    @Override
    public Set<String> getUserPermissions(long userId) {
        return null;
    }

    @Override
    public SysUserToken queryByToken(String token) {
        return null;
    }

    @Override
    public SysUser queryUser(Long userId) {
        return null;
    }
}
