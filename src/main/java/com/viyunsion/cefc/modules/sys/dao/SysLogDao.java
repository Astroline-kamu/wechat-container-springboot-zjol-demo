/*
 * Copyright (c) 2022 Astroline All rights reserved.
 *
 * @date: 6/21/22, 10:47 AM
 * @author: Astroline <Astroline_kamu@outlook.com>
 *
 * https://niyredra.com
 *
 * 在下鸭爪，全宇宙最凶狠的龙！
 * 嗷～
 */

package com.viyunsion.cefc.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.viyunsion.cefc.modules.sys.model.SysLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * 日志模块
 *
 * @author Niyredra Astroline_kamu@outlook.com
 */
@Mapper
public interface SysLogDao extends BaseMapper<SysLog> {
}
