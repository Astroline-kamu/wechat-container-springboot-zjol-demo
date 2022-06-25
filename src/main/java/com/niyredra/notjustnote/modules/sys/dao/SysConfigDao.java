/*
 * Copyright (c) 2022 Astroline All rights reserved.
 *
 * @date: 6/21/22, 10:44 AM
 * @author: Astroline <Astroline_kamu@outlook.com>
 *
 * https://niyredra.com
 *
 * 在下鸭爪，全宇宙最凶狠的龙！
 * 嗷～
 */

package com.niyredra.notjustnote.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.niyredra.notjustnote.modules.sys.model.SysConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 系统配置信息
 *
 * @author Niyredra Astroline_kamu@outlook.com
 */
@Mapper
public interface SysConfigDao extends BaseMapper<SysConfig> {

    SysConfig queryByKey(String paramKey);

    void updateValueByKey(
            @Param("paramKey") String key,
            @Param("paramValue") String value
    );

}
