package com.gl.springbootlearn.exception;

import com.gl.springbootlearn.enums.ResultEnum;

public class SellException extends RuntimeException {

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        Integer code = resultEnum.getCode();
    }
}
