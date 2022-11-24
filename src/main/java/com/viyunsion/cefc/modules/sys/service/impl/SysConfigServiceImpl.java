/*
 * Copyright (c) 2022 Astroline All rights reserved.
 *
 * @date: 6/25/22, 9:16 PM
 * @author: Astroline <Astroline_kamu@outlook.com>
 *
 * https://niyredra.com
 *
 * 在下鸭爪，全宇宙最凶狠的龙！
 * 嗷～
 */

package com.viyunsion.cefc.modules.sys.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.viyunsion.cefc.common.exception.ArException;
import com.viyunsion.cefc.common.global.page.PageHelper;
import com.viyunsion.cefc.common.global.page.Query;
import com.viyunsion.cefc.modules.sys.dao.SysConfigDao;
import com.viyunsion.cefc.modules.sys.model.SysConfig;
import com.viyunsion.cefc.modules.sys.redis.SysConfigRedis;
import com.viyunsion.cefc.modules.sys.service.SysConfigService;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 系统配置信息
 *
 * @author Niyredra Astroline_kamu@outlook.com
 */
@Log4j2
@Service
public class SysConfigServiceImpl extends ServiceImpl<SysConfigDao, SysConfig> implements SysConfigService {

    private SysConfigRedis sysConfigRedis;

    @Override
    public PageHelper queryPageLikeParamKey(Map<String, Object> params) {
        String key = (String) params.get("paramKey");
        return new PageHelper(
                this.page(
                        new Query<SysConfig>().getPage(params),
                        new QueryWrapper<SysConfig>()
                                .like(StringUtils.isNotBlank(key), "param_key", key)
                                .eq("status", 1)
                )
        );
    }

    @Override
    public void saveConfig(SysConfig config) {
        this.save(config);
        sysConfigRedis.saveOrUpdate(config);
    }

    @Override
    public void updateConfig(SysConfig config) {
        this.updateById(config);
        sysConfigRedis.saveOrUpdate(config);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateValueByKey(String key, String value) {
        baseMapper.updateValueByKey(key, value);
        sysConfigRedis.delete(key);
    }

    @Override
    public void deleteBatch(Long[] ids) {
        List<SysConfig> configList = listByIds(Arrays.asList(ids));
        for (SysConfig config :
                configList)
            sysConfigRedis.delete(config.getParamKey());
        this.removeByIds(Arrays.asList(ids));
    }

    @Override
    public String getValue(String key) {
        SysConfig config = sysConfigRedis.get(key);
        if (config == null){
            // ...我才发现内置的baseMapper就是我写的Mapper？？我之前为什么还要注入？？
            config = baseMapper.queryByKey(key);
            sysConfigRedis.saveOrUpdate(config);
        }
        return config == null ? null : config.getParamValue();
    }

    @Override
    public <T> T getConfigObject(String key, Class<T> clazz) {
        String value = getValue(key);
        if (StringUtils.isNotBlank(value)){
            return JSON.parseObject(value, clazz);
        }
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (InvocationTargetException
                | NoSuchMethodException
                | IllegalAccessException
                | InstantiationException e) {
            throw new ArException("参数获取失败");
        }
    }

    @Autowired
    public void setSysConfigRedis(SysConfigRedis sysConfigRedis) {
        this.sysConfigRedis = sysConfigRedis;
    }
}
