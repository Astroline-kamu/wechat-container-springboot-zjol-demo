/*
 * Copyright (c) 2022 Astroline All rights reserved.
 *
 * @date: 6/17/22, 9:16 AM
 * @author: Astroline <Astroline_kamu@outlook.com>
 *
 * https://niyredra.com
 *
 * 在下鸭爪，全宇宙最凶狠的龙！
 * 嗷～
 */

package com.niyredra.notjustnote.modules.sys.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.niyredra.notjustnote.common.validator.group.AddGroup;
import com.niyredra.notjustnote.common.validator.group.UpdateGroup;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class SysUser implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId
    private Long userID;

    @NotBlank(message = "用户名不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String username;
    // 密码
    private String password;
    // 加盐
    private String salt;

    // 邮箱
    @NotBlank(message = "邮箱不能为空", groups = {AddGroup.class, UpdateGroup.class})
    @Email(message = "邮箱格式不正确", groups = {AddGroup.class, UpdateGroup.class})
    private String email;
    // 手机号
    private String mobile;
    // 账号状态：0 冻结，1 正常
    private Integer status;
    // 角色ID列表
    @TableField(exist = false)
    private List<Long> roleIdList;
    // 创建者ID
    private Long createUserId;

    private Date createTime;
    private Date updateTime;

}
