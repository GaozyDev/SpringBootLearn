package com.gl.springbootlearn.controller;

import com.gl.springbootlearn.enums.ResultEnum;
import com.gl.springbootlearn.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;

@Controller
@RequestMapping("/wechat")
@Slf4j
public class WechatController {

    /**
     * openId 获取流程
     * 1.微信内打开前端页面，页面请求后台接口 http://IP/sell/wechat/authorize 携带参数：returnUrl
     * 2.http://IP/sell/wechat/authorize 重定向到微信授权链接，携带参数：回调 url 和 returnUrl
     * 3.微信方调用回调 url，回传 code 和 returnUrl, 通过 code 获取 openId, 并重定向到 returnUrl, 携带参数 openId
     */

    @Autowired
    private WxMpService wxMpService;

    @GetMapping("/authorize")
    public String authorize(@RequestParam("returnUrl") String returnUrl) {
        String url = "http://quick.nat300.top/sell/wechat/userInfo";
        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAUTH2_SCOPE_BASE, URLEncoder.encode(returnUrl));
        return String.format("redirect:%s", redirectUrl);
    }

    @GetMapping("/userInfo")
    public String userInfo(@RequestParam("code") String code,
                           @RequestParam("state") String returnUrl) {
        try {
            WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
            String openId = wxMpOAuth2AccessToken.getOpenId();
            return String.format("redirect:%s?openid=%s", returnUrl, openId);
        } catch (WxErrorException e) {
            log.error("[微信网页授权]" + e);
            throw new SellException(ResultEnum.WECHAT_MP_ERROR.getCode(), e.getError().getErrorMsg());
        }
    }
}
