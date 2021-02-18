package com.gl.springbootlearn.utils;

import com.gl.springbootlearn.enums.CodeEnum;

public class EnumUtil {

    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        for (T enumConstant : enumClass.getEnumConstants()) {
            if (enumConstant.getCode().equals(code)) {
                return enumConstant;
            }
        }
        return null;
    }
}
