/*
 * Copyright (c) 2022 Astroline All rights reserved.
 *
 * @date: 6/27/22, 1:03 PM
 * @author: Astroline <Astroline_kamu@outlook.com>
 *
 * https://niyredra.com
 *
 * 在下鸭爪，全宇宙最凶狠的龙！
 * 嗷～
 */

package com.viyunsion.cefc.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.viyunsion.cefc.common.global.page.PageHelper;
import com.viyunsion.cefc.common.global.page.Query;
import com.viyunsion.cefc.modules.sys.dao.SysLogDao;
import com.viyunsion.cefc.modules.sys.model.SysLog;
import com.viyunsion.cefc.modules.sys.service.SysLogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 系统日志
 *
 * @author Niyredra Astroline_kamu@outlook.com
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogDao, SysLog> implements SysLogService {

    @Override
    public PageHelper queryPageLikeUsername(Map<String, Object> params) {
        String key = (String) params.get("paramKey");
        return new PageHelper(
                this.page(
                        new Query<SysLog>().getPage(params),
                        new QueryWrapper<SysLog>()
                                .like(StringUtils.isNotBlank(key), "username", key)
                )
        );
    }
}
