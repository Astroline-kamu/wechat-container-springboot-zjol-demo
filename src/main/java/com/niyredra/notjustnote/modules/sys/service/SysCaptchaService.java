/*
 * Copyright (c) 2022 Astroline All rights reserved.
 *
 * @date: 6/21/22, 11:15 AM
 * @author: Astroline <Astroline_kamu@outlook.com>
 *
 * https://niyredra.com
 *
 * 在下鸭爪，全宇宙最凶狠的龙！
 * 嗷～
 */

package com.niyredra.notjustnote.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.niyredra.notjustnote.modules.sys.model.SysCaptcha;

import java.awt.image.BufferedImage;

/**
 * 验证码相关业务
 *
 * @author Niyredra Astroline_kamu@outlook.com
 */
public interface SysCaptchaService extends IService<SysCaptcha>{

    /**
     * 获取图片验证码
     *
     * @param uuid uuid
     * @return 验证码图片
     */
    BufferedImage getCaptcha(String uuid);

    /**
     * 验证码校验
     *
     * @param uuid uuid
     * @param code 验证码
     * @return 校验结果 true：成功｜false：失败
     */
    boolean validate(String uuid, String code);
}
