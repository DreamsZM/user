package com.zy.user.enums;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum RoleEnum {

    Buyer(1, "买家"),

    Seller(2, "卖家");

    private int code;

    private String message;

    RoleEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
