package com.gl.springbootlearn.enums;

import lombok.Getter;

@Getter
public enum PayStatusEnum implements CodeEnum {
    WAIT(0, "等待支付"),
    SUCCESS(1, "支付成功");

    private final Integer code;

    private final String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
