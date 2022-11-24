/*
 * Copyright (c) 2022 Astroline All rights reserved.
 *
 * @date: 8/11/22, 3:59 AM
 * @author: Astroline <Astroline_kamu@outlook.com>
 *
 * https://niyredra.com
 *
 * 在下鸭爪，全宇宙最凶狠的龙！
 * 嗷～
 */

package com.viyunsion.cefc.modules.sys.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 *
 * 用户Token相关配置
 * @author Niyredra Astroline_kamu@outlook.com
 */
@Data
@Configuration
public class UserTokenConfig {

    // UserToken过期时间 单位为秒
    @Value("${user.token.expire: 43200}")
    private int expire;

}
