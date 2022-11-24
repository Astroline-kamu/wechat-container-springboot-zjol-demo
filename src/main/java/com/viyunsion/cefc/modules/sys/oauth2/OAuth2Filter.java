/*
 * Copyright (c) 2022 Astroline All rights reserved.
 *
 * @date: 8/11/22, 8:21 PM
 * @author: Astroline <Astroline_kamu@outlook.com>
 *
 * https://niyredra.com
 *
 * 在下鸭爪，全宇宙最凶狠的龙！
 * 嗷～
 */

package com.viyunsion.cefc.modules.sys.oauth2;

import com.google.gson.Gson;
import com.viyunsion.cefc.common.global.Result;
import com.viyunsion.cefc.common.utils.HttpContextUtils;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 过滤器
 *
 * @author Niyredra Astroline_kamu@outlook.com
 */
@Log4j2
public class OAuth2Filter extends AuthenticatingFilter {

    /**
     * 登陆失败处理
     *
     * @param token
     * @param e
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setContentType("application/json;charset=utf-8");
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtils.getOrigin());

        try {
            Throwable throwable = e.getCause() == null ? e : e.getCause();
            Result result = Result.error(HttpStatus.SC_UNAUTHORIZED, throwable.getMessage());

            String json = new Gson().toJson(result);
            httpResponse.getWriter().print(json);
        } catch (IOException ioException) {
            log.info("登陆失败\n {}", request.getParameterMap().toString());
            ioException.printStackTrace();
        }
        return false;
    }

    /**
     * 用户未登陆时走此方法
     *
     * @param servletRequest  请求体
     * @param servletResponse 返回体
     * @return boolean
     * @throws Exception 就...exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        String token = getRequestToken((HttpServletRequest) servletResponse);
        if (StringUtils.isBlank(token)) {
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Allow-Origin", HttpContextUtils.getOrigin());

            String json = new Gson().toJson(
                    Result.error(
                            HttpStatus.SC_UNAUTHORIZED, "Invalid token"
                    )
            );
            response.getWriter().print(json);
            return false;
        }

        return executeLogin(servletRequest, servletResponse);
    }

    /**
     * 用户已登陆的拦截器
     *
     * @param request     请求体
     * @param response    返回包
     * @param mappedValue urls配置中拦截器参数
     * @return true 需要继续处理 | false 该实例已经处理
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        // todo option方法直接通过？
        return ((HttpServletRequest) request)
                .getMethod()
                .equals(RequestMethod.OPTIONS.name());
    }

    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        String token = getRequestToken((HttpServletRequest) servletResponse);
        if (StringUtils.isBlank(token)) return null;
        return new OAuth2Token(token);
    }


    /**
     * 两种方式可以检测token
     * 1. 请求头
     * 2. 地址参数
     *
     * @param request 请求上下文
     * @return String token
     */
    private String getRequestToken(HttpServletRequest request) {
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) token = request.getParameter("token");
        return token;
    }

}
