package com.zy.user.vo;

import lombok.Data;

@Data
public class ResultVO {

    private int code;

    private String message;

    private Object data;

    public ResultVO() {
    }

    public ResultVO(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
