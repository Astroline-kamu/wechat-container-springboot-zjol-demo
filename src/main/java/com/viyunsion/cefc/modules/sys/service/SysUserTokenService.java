/*
 * Copyright (c) 2022 Astroline All rights reserved.
 *
 * @date: 6/29/22, 8:29 PM
 * @author: Astroline <Astroline_kamu@outlook.com>
 *
 * https://niyredra.com
 *
 * 在下鸭爪，全宇宙最凶狠的龙！
 * 嗷～
 */

package com.viyunsion.cefc.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.viyunsion.cefc.common.global.Result;
import com.viyunsion.cefc.modules.sys.model.SysUserToken;

/**
 * 用户Token 依旧要写数据库 如果发生宕机重启就会导致所有人的token同时失效，重新登陆的请求会造成雪崩
 *
 * @author Niyredra Astroline_kamu@outlook.com
 */
public interface SysUserTokenService extends IService<SysUserToken> {

    /**
     * 生成token
     *
     * @param userId 用户ID
     * @return Result (token shape)
     */
    Result createToken(long userId);

    /**
     * 用户登出
     *
     * @param userId 用户ID
     */
    void logout(long userId);

}
