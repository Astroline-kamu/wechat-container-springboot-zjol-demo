/*
 * Copyright (c) 2022 Astroline All rights reserved.
 *
 * @date: 8/11/22, 9:20 PM
 * @author: Astroline <Astroline_kamu@outlook.com>
 *
 * https://niyredra.com
 *
 * 在下鸭爪，全宇宙最凶狠的龙！
 * 嗷～
 */

package com.viyunsion.cefc.common.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * HttpContextUtils
 *
 * @author Niyredra Astroline_kamu@outlook.com
 */
public class HttpContextUtils {

    public static HttpServletRequest getHttpServletRequest() {
        return (
                (ServletRequestAttributes)
                        Objects.requireNonNull(RequestContextHolder.getRequestAttributes())
        ).getRequest();
    }

    public static String getDomain(){
        HttpServletRequest request = getHttpServletRequest();
        StringBuffer url = request.getRequestURL();
        return url.delete(
                url.length() - request.getRequestURI().length(),
                url.length()
        ).toString();
    }

    public static String getOrigin(){
        HttpServletRequest request = getHttpServletRequest();
        return request.getHeader("Origin");
    }

}
