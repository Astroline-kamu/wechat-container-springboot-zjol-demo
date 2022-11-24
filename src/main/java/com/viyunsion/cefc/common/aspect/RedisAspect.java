/*
 * Copyright (c) 2022 Astroline All rights reserved.
 *
 * @date: 6/20/22, 9:59 AM
 * @author: Astroline <Astroline_kamu@outlook.com>
 *
 * https://niyredra.com
 *
 * 在下鸭爪，全宇宙最凶狠的龙！
 * 嗷～
 */

package com.viyunsion.cefc.common.aspect;

import com.viyunsion.cefc.common.exception.ArException;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Redis切片
 *
 * @author Niyredra Astroline_kamu@outlook.com
 */

@Data
@Log4j2
@Aspect
@Configuration
public class RedisAspect {

    // 是否开启redis缓存：true开启｜false关闭
    @Value("${spring.redis.open: false}")
    private boolean open;

    @Around("execution(* com.viyunsion.cefc.common.utils.RedisUtils.*(..))")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object res = null;
        log.info("Redis Configuration Test {}", open);
        if (open) {
            try {
                res = point.proceed();
                log.info("Point.proceed ::: {}", point.proceed().toString());
            }catch (Exception e ){
                log.error("redis error", e);
                throw new ArException("Redis服务异常");
            }
        }
        return res;
    }

}
