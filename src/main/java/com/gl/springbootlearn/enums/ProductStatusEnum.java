package com.gl.springbootlearn.enums;

import lombok.Getter;

@Getter
public enum ProductStatusEnum {
    UP(0, "在架"),
    DOWN(1, "下架");

    private final Integer code;

    private final String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
