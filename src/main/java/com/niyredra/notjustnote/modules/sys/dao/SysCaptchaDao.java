/*
 * Copyright (c) 2022 Astroline All rights reserved.
 *
 * @date: 6/23/22, 4:44 PM
 * @author: Astroline <Astroline_kamu@outlook.com>
 *
 * https://niyredra.com
 *
 * 在下鸭爪，全宇宙最凶狠的龙！
 * 嗷～
 */

package com.niyredra.notjustnote.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.niyredra.notjustnote.modules.sys.model.SysCaptcha;
import org.apache.ibatis.annotations.Mapper;

/**
 * 验证码 定期清理过期验证码 保留清除日志（日志类，封装成类似logger的程序）
 * 验证码的非重复性让它不需要放进redis中缓存
 *
 * 但是在验证码查询的时候还是要写一个缓存的，用于非频繁刷新验证码的校验
 *
 * @author Niyredra Astroline_kamu@outlook.com
 */
@Mapper
public interface SysCaptchaDao extends BaseMapper<SysCaptcha> {
}
