/*
 * Copyright (c) 2022 Astroline All rights reserved.
 *
 * @date: 8/11/22, 3:50 AM
 * @author: Astroline <Astroline_kamu@outlook.com>
 *
 * https://niyredra.com
 *
 * 在下鸭爪，全宇宙最凶狠的龙！
 * 嗷～
 */

package com.viyunsion.cefc.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.viyunsion.cefc.common.global.Result;
import com.viyunsion.cefc.modules.sys.config.UserTokenConfig;
import com.viyunsion.cefc.modules.sys.dao.SysUserTokenDao;
import com.viyunsion.cefc.modules.sys.model.SysUserToken;
import com.viyunsion.cefc.modules.sys.oauth2.TokenGenerator;
import com.viyunsion.cefc.modules.sys.service.SysUserTokenService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

/**
 *
 * 用户Token
 * @author Niyredra Astroline_kamu@outlook.com
 */
@Service
public class SysUserTokenServiceImpl extends ServiceImpl<SysUserTokenDao, SysUserToken> implements SysUserTokenService {

    final
    UserTokenConfig userTokenConfig;

    public SysUserTokenServiceImpl(UserTokenConfig userTokenConfig) {
        this.userTokenConfig = userTokenConfig;
    }

    @Override
    public Result createToken(long userId) {
        // 生成Token
        String token = TokenGenerator.generateValue();

        Date now = new Date();
        Date expireTime = new Date(now.getTime() + userTokenConfig.getExpire() * 1000L);

        SysUserToken userToken = this.getById(userId);
        if (userToken == null) {
            userToken = new SysUserToken();
            userToken.setUserId(userId);
        }
        userToken.setToken(token);
        userToken.setUpdateTime(now);
        userToken.setExpireTime(expireTime);
        this.saveOrUpdate(userToken);

        return Objects.requireNonNull(Result.ok().put("token", token)).put("expire", userTokenConfig.getExpire());
    }

    @Override
    public void logout(long userId) {
        String token = TokenGenerator.generateValue();
        SysUserToken userToken = new SysUserToken();
        userToken.setUserId(userId);
        userToken.setToken(token);
        this.updateById(userToken);
    }
}
