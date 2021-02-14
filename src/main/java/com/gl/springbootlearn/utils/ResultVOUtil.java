package com.gl.springbootlearn.utils;

import com.gl.springbootlearn.VO.ResultVO;

public class ResultVOUtil {

    public static <T> ResultVO<T> success(T data) {
        ResultVO<T> resultV0 = new ResultVO<>();
        resultV0.setData(data);
        resultV0.setCode(0);
        resultV0.setMsg("成功");
        return resultV0;
    }

    public static <T> ResultVO<T> success() {
        return success(null);
    }

    public static <T> ResultVO<T> error(Integer code, String msg) {
        ResultVO<T> resultVO = new ResultVO<>();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }
}
