package com.jcy.jcyycback.common.service;


public enum RespCodeEnum implements IRespCodeEnum {

    SUCCESS(200, "成功"),
    FAIL(100, "操作失败"),
    EXCEPTION(500, "异常"),
    UNADMIN(5000, "帐号未登录"),
    TIMEOUT(5001, "帐号已超时"),
    BAN_USER(102, "账号已删除"),
    PARAM_FAIL(106, "参数校验失败"),
    EXCEPTION_303(303, "该登录账号已存在"),
    EXCEPTION_306(306, "登录账号或密码错误");

    private int code;

    private String text;

    RespCodeEnum(int code, String text) {
        this.code = code;
        this.text = text;
    }

    @Override
    public String toString() {
        return String.valueOf(this.code);
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getText() {
        return this.text;
    }
}

