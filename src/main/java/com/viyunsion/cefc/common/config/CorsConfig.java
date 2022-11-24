/*
 * Copyright (c) 2022 Astroline All rights reserved.
 *
 * @date: 8/12/22, 1:43 AM
 * @author: Astroline <Astroline_kamu@outlook.com>
 *
 * https://niyredra.com
 *
 * 在下鸭爪，全宇宙最凶狠的龙！
 * 嗷～
 */

package com.viyunsion.cefc.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Niyredra Astroline_kamu@outlook.com
 */
    @Configuration
    public class CorsConfig implements WebMvcConfigurer {

        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
                    .allowedOriginPatterns("*")
                    .allowCredentials(true)
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                    .maxAge(3600);
        }
    }
