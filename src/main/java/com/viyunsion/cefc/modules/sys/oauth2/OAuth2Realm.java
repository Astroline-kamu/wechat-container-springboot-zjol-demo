/*
 * Copyright (c) 2022 Astroline All rights reserved.
 *
 * @date: 8/11/22, 5:22 PM
 * @author: Astroline <Astroline_kamu@outlook.com>
 *
 * https://niyredra.com
 *
 * 在下鸭爪，全宇宙最凶狠的龙！
 * 嗷～
 */

package com.viyunsion.cefc.modules.sys.oauth2;

import com.viyunsion.cefc.common.constant.UserStatus;
import com.viyunsion.cefc.modules.sys.model.SysUser;
import com.viyunsion.cefc.modules.sys.model.SysUserToken;
import com.viyunsion.cefc.modules.sys.service.ShiroService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author Niyredra Astroline_kamu@outlook.com
 */
@Component
public class OAuth2Realm extends AuthorizingRealm {

    private final ShiroService shiroService;

    public OAuth2Realm(ShiroService shiroService) {
        this.shiroService = shiroService;
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof OAuth2Token;
    }

    /**
     * 授权
     *
     * @param principalCollection principal
     * @return AuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SysUser user = (SysUser) principalCollection.getPrimaryPrincipal();
        Long userId = user.getUserId();

        // 用户权限列表
        Set<String> permissionSet = shiroService.getUserPermissions(userId);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permissionSet);

        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        String accessToken = (String) authenticationToken.getPrincipal();

        // token查user
        SysUserToken token = shiroService.queryByToken(accessToken);
        if (token == null || token.getExpireTime().getTime() < System.currentTimeMillis()) {
            throw new IncorrectCredentialsException("token失效，请重新登录");
        }

        // 用户信息
        SysUser user = shiroService.queryUser(token.getUserId());

        if (user.getStatus() == UserStatus.FREEZE.getValue())
            throw new LockedAccountException("账号已被锁定，请邮件联系管理员");

        return new SimpleAuthenticationInfo(user, accessToken, getName());

    }
}
