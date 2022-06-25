/*
 * Copyright (c) 2022 Astroline All rights reserved.
 *
 * @date: 6/17/22, 1:06 PM
 * @author: Astroline <Astroline_kamu@outlook.com>
 *
 * https://niyredra.com
 *
 * 在下鸭爪，全宇宙最凶狠的龙！
 * 嗷～
 */

package com.niyredra.notjustnote.common.utils;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Redis工具
 *
 * @author Niyredra Astroline_kamu@outlook.com
 */


@Component
public class RedisUtils {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private ValueOperations<String, String> valueOperations;

    @Autowired
    private HashOperations<String, String, Object> hashOperations;

    @Autowired
    private ListOperations<String, Object> listOperations;

    @Autowired
    private SetOperations<String, Object> setOperations;

    @Autowired
    private ZSetOperations<String, Object> zSettOperations;

    public final static long DEFAULT_EXPIRE = 60 * 60 * 24;
    public final static long NOT_EXPIRE = -1;

    private final static Gson gson = new Gson();

    public void set(String key, Object val, long expire) {
        valueOperations.set(key, toJson(val));
        if (expire != NOT_EXPIRE)
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
    }

    public void set(String key, Object val) {
        set(key, val, DEFAULT_EXPIRE);
    }

    public <T> T get(String key, Class<T> clz, long expire) {
        String value = valueOperations.get(key);
        if (expire != NOT_EXPIRE)
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        return value == null ? null : parseObject(value, clz);
    }

    public <T> T get(String key, Class<T> clz) {
        return get(key, clz, NOT_EXPIRE);
    }

    public String get(String key, long expire) {
        String value = valueOperations.get(key);
        if (expire != NOT_EXPIRE)
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        return value;
    }

    public String get(String key) {
        return get(key, NOT_EXPIRE);
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }

    /**
     *
     * pojo类 转 Json字符串
     * @param obj pojo类
     * @return Json字符串
     */
    private String toJson(Object obj) {
        if (obj instanceof Integer
                || obj instanceof Long
                || obj instanceof Float
                || obj instanceof Double
                || obj instanceof Boolean
                || obj instanceof String
        ) return String.valueOf(obj);
        return gson.toJson(obj);
    }

    /**
     *
     * JSON 转 Object
     * @param json Json字符串
     * @param clz 转换目标类
     * @param <T> 类
     * @return Class T
     */
    private <T> T parseObject(String json, Class<T> clz) {
        return JSON.parseObject(json, clz);
    }
}
