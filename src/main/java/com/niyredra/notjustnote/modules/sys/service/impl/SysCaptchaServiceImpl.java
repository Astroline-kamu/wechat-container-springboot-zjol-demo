/*
 * Copyright (c) 2022 Astroline All rights reserved.
 *
 * @date: 6/21/22, 11:23 AM
 * @author: Astroline <Astroline_kamu@outlook.com>
 *
 * https://niyredra.com
 *
 * 在下鸭爪，全宇宙最凶狠的龙！
 * 嗷～
 */

package com.niyredra.notjustnote.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.niyredra.notjustnote.common.exception.ArException;
import com.niyredra.notjustnote.common.utils.DateUtils;
import com.niyredra.notjustnote.modules.sys.dao.SysCaptchaDao;
import com.niyredra.notjustnote.modules.sys.model.SysCaptcha;
import com.niyredra.notjustnote.modules.sys.service.SysCaptchaService;
import com.wf.captcha.GifCaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.Date;

/**
 * 验证码相关业务 redis缓存查询uuid 防止多重查询
 *
 * @author Niyredra Astroline_kamu@outlook.com
 */
@Service
public class SysCaptchaServiceImpl extends ServiceImpl<SysCaptchaDao, SysCaptcha> implements SysCaptchaService {

    private GifCaptcha gifCaptcha;

    @Override
    public BufferedImage getCaptcha(String uuid) {
        if (uuid.isBlank()) {
            throw new ArException("uuid不能为空");
        }

        String code = gifCaptcha.text();

        SysCaptcha captcha = new SysCaptcha();
        captcha.setUuid(uuid);
        captcha.setCode(code);
        captcha.setExpireTime(new Date());

        return null;
    }

    @Override
    public boolean validate(String uuid, String code) {
        return false;
    }
}
