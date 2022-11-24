/*
 * Copyright (c) 2022 Astroline All rights reserved.
 *
 * @date: 6/27/22, 12:59 PM
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
import com.viyunsion.cefc.modules.sys.model.SysLog;

import java.util.Map;

/**
 * 系统日志
 *
 * @author Niyredra Astroline_kamu@outlook.com
 */
public interface SysLogService extends IService<SysLog> {


    PageHelper queryPageLikeUsername(Map<String, Object> params);

}
