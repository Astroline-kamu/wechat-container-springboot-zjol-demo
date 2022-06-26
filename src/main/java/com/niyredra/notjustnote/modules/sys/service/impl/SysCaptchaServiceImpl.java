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

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.niyredra.notjustnote.common.exception.ArException;
import com.niyredra.notjustnote.common.utils.DateTimeUtils;
import com.niyredra.notjustnote.modules.sys.dao.SysCaptchaDao;
import com.niyredra.notjustnote.modules.sys.model.SysCaptcha;
import com.niyredra.notjustnote.modules.sys.service.SysCaptchaService;
import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.ChineseCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 验证码相关业务 redis缓存查询uuid 防止多重查询
 *
 * @author Niyredra Astroline_kamu@outlook.com
 */
@Service
public class SysCaptchaServiceImpl extends ServiceImpl<SysCaptchaDao, SysCaptcha> implements SysCaptchaService {

    @Override
    public Captcha getArithmeticCaptcha(String uuid) {
        if (StringUtils.isBlank(uuid)) throw new ArException("uuid不能为空");

        ArithmeticCaptcha captcha = new ArithmeticCaptcha();
//        captcha.setLen(6);
        String code = captcha.text();
        setCaptcha(uuid, code);
        return captcha;
    }

    @Override
    public Captcha getChineseCaptcha(String uuid) {
        if (StringUtils.isBlank(uuid)) throw new ArException("uuid不能为空");

        ChineseCaptcha captcha = new ChineseCaptcha();
        String code = captcha.text();
        setCaptcha(uuid, code);
        return captcha;
    }

    @Override
    public Captcha getSpecCaptcha(String uuid) {
        if (StringUtils.isBlank(uuid)) throw new ArException("uuid不能为空");

        SpecCaptcha captcha = new SpecCaptcha();
        String code = captcha.text();
        setCaptcha(uuid, code);
        return captcha;
    }

    @Override
    public boolean validate(String uuid, String code) {
        SysCaptcha captcha = getOne(
                new QueryWrapper<SysCaptcha>()
                        .eq("uuid", uuid)
        );
        if (captcha == null) return false;
        removeById(uuid);
        // 会有一部分 纯刷验证码但是不填写的 这种空出来的数据每日晚上进行一次专门的清理
        return captcha.getCode().equalsIgnoreCase(code)
                && captcha.getExpireTime().getTime() >= System.currentTimeMillis();
    }

    private void setCaptcha(String uuid, String code) {
        SysCaptcha captcha = new SysCaptcha();
        captcha.setUuid(uuid);
        captcha.setCode(code);
        captcha.setExpireTime(DateTimeUtils.addMinutes(new Date(), 5));
        this.save(captcha);
    }

}
