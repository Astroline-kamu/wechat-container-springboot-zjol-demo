/*
 * Copyright (c) 2022 Astroline All rights reserved.
 *
 * @date: 8/11/22, 5:28 PM
 * @author: Astroline <Astroline_kamu@outlook.com>
 *
 * https://niyredra.com
 *
 * 在下鸭爪，全宇宙最凶狠的龙！
 * 嗷～
 */

package com.viyunsion.cefc.modules.sys.oauth2;

import org.apache.shiro.authc.AuthenticationToken;

/**
 *
 *
 * @author Niyredra Astroline_kamu@outlook.com
 */
public class OAuth2Token implements AuthenticationToken {

    private final String token;

    public OAuth2Token(String token) {this.token = token;}

    // Username
    @Override
    public Object getPrincipal() {
        return token;
    }

    // Password
    @Override
    public Object getCredentials() {
        return token;
    }
}
