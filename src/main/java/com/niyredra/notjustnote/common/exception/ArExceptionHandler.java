/*
 * Copyright (c) 2022 Astroline All rights reserved.
 *
 * @date: 4/22/22, 10:45 PM
 * @author: Astroline <Astroline_kamu@outlook.com>
 *
 * https://niyredra.com
 *
 * 在下鸭爪，全宇宙最凶狠的龙！
 * 嗷～
 */

package com.niyredra.notjustnote.common.exception;


import com.niyredra.notjustnote.common.global.Result;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@Log4j2
@RestControllerAdvice
public class ArExceptionHandler {

    @ExceptionHandler(ArException.class)
    public Result arExceptionHandler(ArException e){
        return new Result(){{
            put("code", e.getCode());
            put("msg", e.getMsg());
        }};
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException e) {
        return new Result(999, "输入错误");
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public Result noHandlerFoundExceptionHandler(NoHandlerFoundException e) {
        log.error(e.getMessage(), e);
        return new Result(404, "路径不存在");
    }


    @ExceptionHandler(AuthorizationException.class)
    public Result HttpMessageNotReadableException(AuthorizationException e) {
        log.error(e.getMessage(), e);
        return new Result(403, "没有访问权限");
    }


    @ExceptionHandler(DuplicateKeyException.class)
    public Result DuplicateKeyExceptionHandler(DuplicateKeyException e) {
        log.error(e.getMessage(), e);
        return Result.error("数据库中已存在该记录");
    }

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        log.error(e.getMessage(), e);
        return Result.error();
    }


}
