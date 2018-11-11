package com.zy.user.enums;

import lombok.Getter;

@Getter
public enum  ResultEnum {
    LOGIN_FAIL(0, "登录失败"),

    ROLE_ERROR(1, "角色不正确"),
    ;

    private int code;

    private String message;

    ResultEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
