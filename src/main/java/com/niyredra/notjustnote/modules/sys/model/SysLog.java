/*
 * Copyright (c) 2022 Astroline All rights reserved.
 *
 * @date: 6/17/22, 9:10 AM
 * @author: Astroline <Astroline_kamu@outlook.com>
 *
 * https://niyredra.com
 *
 * 在下鸭爪，全宇宙最凶狠的龙！
 * 嗷～
 */

package com.niyredra.notjustnote.modules.sys.model;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private Long id;
    // 用户名
    private String username;
    // 用户操作
    private String operation;
    // 请求方法
    private String method;
    // 请求参数
    private String params;
    // 执行时长 毫秒
    private Long time;
    // IP地址
    private String ip;
    // 创建时间
    private Date createTime;
    
}
