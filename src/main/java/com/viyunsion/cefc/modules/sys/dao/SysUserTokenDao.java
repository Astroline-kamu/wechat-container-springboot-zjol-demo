/*
 * Copyright (c) 2022 Astroline All rights reserved.
 *
 * @date: 8/11/22, 3:51 AM
 * @author: Astroline <Astroline_kamu@outlook.com>
 *
 * https://niyredra.com
 *
 * 在下鸭爪，全宇宙最凶狠的龙！
 * 嗷～
 */

package com.viyunsion.cefc.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.viyunsion.cefc.modules.sys.model.SysUserToken;
import org.apache.ibatis.annotations.Mapper;

/**
 *
 * 用户Token
 * @author Niyredra Astroline_kamu@outlook.com
 */
@Mapper
public interface SysUserTokenDao extends BaseMapper<SysUserToken> {

    SysUserToken queryByToken(String token);

}
