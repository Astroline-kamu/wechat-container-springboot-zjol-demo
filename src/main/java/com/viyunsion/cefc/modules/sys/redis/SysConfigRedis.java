/*
 * Copyright (c) 2022 Astroline All rights reserved.
 *
 * @date: 6/26/22, 2:46 PM
 * @author: Astroline <Astroline_kamu@outlook.com>
 *
 * https://niyredra.com
 *
 * 在下鸭爪，全宇宙最凶狠的龙！
 * 嗷～
 */

package com.viyunsion.cefc.modules.sys.redis;

import com.viyunsion.cefc.common.global.RedisKeys;
import com.viyunsion.cefc.common.utils.RedisUtils;
import com.viyunsion.cefc.modules.sys.model.SysConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 系统信息 redis缓存
 *
 * @author Niyredra Astroline_kamu@outlook.com
 */
@Component
public class SysConfigRedis {

    private RedisUtils redisUtils;

    public void saveOrUpdate(SysConfig config) {
        if (config == null) return;
        String key = getKey(config.getParamKey());
        redisUtils.set(key, config);
    }

    public void delete(String configKey) {
        String key = getKey(configKey);
        redisUtils.delete(key);
    }

    public SysConfig get(String configKey) {
        String key = getKey(configKey);
        return redisUtils.get(key, SysConfig.class);
    }

    private String getKey(String key) {
        return RedisKeys.getSysConfigKey(key);
    }

    @Autowired
    public void setRedisUtils(RedisUtils redisUtils) {
        this.redisUtils = redisUtils;
    }
}
