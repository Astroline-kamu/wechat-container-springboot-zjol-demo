/*
 * Copyright (c) 2022 Astroline All rights reserved.
 *
 * @date: 4/22/22, 12:58 AM
 * @author: Astroline <Astroline_kamu@outlook.com>
 *
 * https://niyredra.com
 *
 * 在下鸭爪，全宇宙最凶狠的龙！
 * 嗷～
 */

package com.niyredra.notjustnote.common.exception;


public class ArException extends RuntimeException{

    private String msg;
    private int code = 500;

    public ArException(String msg){
        super(msg);
        this.msg = msg;
    }

    public ArException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public ArException(String msg, Throwable cuz) {
        super(msg, cuz);
        this.msg = msg;
    }

    public ArException(String msg, int code, Throwable cuz) {
        super(msg, cuz);
        this.msg = msg;
        this.code = code;
    }




    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
