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

package com.niyredra.notjustnote.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.niyredra.notjustnote.common.global.page.PageHelper;
import com.niyredra.notjustnote.modules.sys.dao.SysConfigDao;
import com.niyredra.notjustnote.modules.sys.model.SysConfig;
import com.niyredra.notjustnote.modules.sys.service.SysConfigService;

import java.util.Map;

/**
 * @author Niyredra Astroline_kamu@outlook.com
 */
public class SysConfigServiceImpl extends ServiceImpl<SysConfigDao, SysConfig> implements SysConfigService {
    @Override
    public PageHelper queryPage(Map<String, Object> params) {
        String key = (String) params.get("paramKey");
//        return this.page(
//                new Query<SysConfig>().get
//        );

        return null;
    }

    @Override
    public void saveConfig(SysConfig config) {

    }

    @Override
    public void updateConfig(SysConfig config) {

    }

    @Override
    public void updateValueByKey(String key, String value) {

    }

    @Override
    public void deleteBatch(Long[] ids) {

    }

    @Override
    public String getValue(String key) {
        return null;
    }

    @Override
    public <T> T getConfigObject(String key, Class<T> clazz) {
        return null;
    }
}
