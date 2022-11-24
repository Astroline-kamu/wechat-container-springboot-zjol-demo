/*
 * Copyright (c) 2022 Astroline All rights reserved.
 *
 * @date: 4/2/22, 3:57 PM
 * @author: Astroline <Astroline_kamu@outlook.com>
 *
 * https://niyredra.com
 *
 * 在下鸭爪，全宇宙最凶狠的龙！
 * 嗷～
 */

package com.viyunsion.cefc.common.global;

import java.util.HashMap;

public class Result extends HashMap<String, Object> {

    public Result() {
        put("code", 200);
        put("msg", "success");
    }

    public Result(int code, String msg) {
        put("code", code);
        put("msg", msg);
    }

    public Result(int code, String msg, Object data) {
        put("code", code);
        put("msg", msg);
        put("data", data);
    }

    public static Result ok() {
        return ok(200, "success");
    }

    public static Result ok(Object data) {
        return ok(200, "success", data);
    }

    public static Result ok(int code, String msg) {

        return new Result(code, msg);
    }

    public static Result ok(int code, String msg, Object data) {
        return new Result(code, msg, data);
    }

    public static Result error() {
        return error(500, "error");
    }

    public static Result error(String msg) {
        return error(500, msg);
    }

    public static Result error(int code, String msg) {
        return new Result(code, msg);
    }

    public static Result error(int code, String msg, Object data) {
        return new Result(code, msg, data);
    }

    public Result put(String key, Object value) {
        super.put(key, value);
        return this;
    }

}
