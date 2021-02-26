package com.gl.springbootlearn.handler;

import com.gl.springbootlearn.VO.ResultVO;
import com.gl.springbootlearn.config.ProjectUrlConfig;
import com.gl.springbootlearn.exception.SellException;
import com.gl.springbootlearn.exception.SellerAuthorizeException;
import com.gl.springbootlearn.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class SellExceptionHandler {

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handlerAuthorizeException() {
        return new ModelAndView("redirect:"
                .concat(projectUrlConfig.getWechatOpenAuthorize())
                .concat("/sell/wechat/qrAuthorize")
                .concat("?returnUrl=")
                .concat(projectUrlConfig.getSell())
                .concat("/sell/seller/login")
        );
    }

    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVO<Object> handlerSellerException(SellException e) {
        return ResultVOUtil.error(e.getCode(), e.getMessage());
    }
}
