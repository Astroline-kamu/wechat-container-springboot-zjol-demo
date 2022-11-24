/*
 * Copyright (c) 2022 Astroline All rights reserved.
 *
 * @date: 6/25/22, 5:35 PM
 * @author: Astroline <Astroline_kamu@outlook.com>
 *
 * https://niyredra.com
 *
 * 在下鸭爪，全宇宙最凶狠的龙！
 * 嗷～
 */

package com.viyunsion.cefc.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.viyunsion.cefc.common.global.page.PageHelper;
import com.viyunsion.cefc.modules.sys.model.SysConfig;

import java.util.Map;

/**
 * 系统配置信息
 *
 * @author Niyredra Astroline_kamu@outlook.com
 */
public interface SysConfigService extends IService<SysConfig> {

    PageHelper queryPageLikeParamKey(Map<String, Object> params);

    /**
     * 保存配置信息
     *
     * @param config SysConfig
     */
    void saveConfig(SysConfig config);

    /**
     * 更新配置信息
     *
     * @param config SysConfig
     */
    void updateConfig(SysConfig config);

    /**
     * 根据Key更新Value
     *
     * @param key ConfigKey
     * @param value ConfigValue
     */
    void updateValueByKey(String key, String value);

    /**
     * 删除配置信息
     *
     * @param ids id列表
     */
    void deleteBatch(Long[] ids);

    /**
     * 根据Key获取配置的Value
     *
     * @param key ConfigKey
     * @return ConfigValue
     */
    String getValue(String key);

    /**
     * 根据Key获取Value的Object对象
     *
     * @param key ConfigKey
     * @param clazz Object对象类型
     * @param <T> Object对象类型
     * @return Class&lt;T&gt;
     */
    <T> T getConfigObject(String key, Class<T> clazz);
}
