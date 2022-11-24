/*
 * Copyright (c) 2022 Astroline All rights reserved.
 *
 * @date: 11/18/22, 12:00 AM
 * @author: Astroline <Astroline_kamu@outlook.com>
 *
 * https://niyredra.com
 *
 * 在下鸭爪，全宇宙最凶狠的龙！
 * 嗷～
 */

package com.viyunsion.cefc.modules.app.sales_order.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;


/**
 * Order订单
 *
 * @author Niyredra Astroline_kamu@outlook.com
 */

@Data
@TableName
public class Order {

    private String oilId;
    private String PortId;
    @TableId
    private String orderId;

    private String type;
    private String area;
    private String port;
    private String oilName;
    private String operator;
    private BigDecimal diff;
    private BigDecimal mops;
    private BigDecimal price;
    private BigDecimal quantity;
    private String operatorName;
    private String customerName;
    private String customerManager;

    @TableField("ETA")
    private Date eta;
    @TableField("ETD")
    private Date etd;
    private Date payTime;
    private Date created;

}
