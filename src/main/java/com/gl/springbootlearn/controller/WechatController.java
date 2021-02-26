package com.gl.springbootlearn.controller;

import com.gl.springbootlearn.config.ProjectUrlConfig;
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

import java.io.UnsupportedEncodingException;
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

    @Autowired
    private WxMpService wxOpenService;

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    @GetMapping("/authorize")
    public String authorize(@RequestParam("returnUrl") String returnUrl) {
        String url = projectUrlConfig.getWechatMpAuthorize() + "/sell/wechat/userInfo";
        String redirectUrl = null;
        try {
            redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAUTH2_SCOPE_BASE, URLEncoder.encode(returnUrl, "UTF-8"));
            return String.format("redirect:%s", redirectUrl);
        } catch (UnsupportedEncodingException e) {
            log.error("[微信网页授权]" + e);
            throw new SellException(ResultEnum.WECHAT_MP_ERROR.getCode(), e.getMessage());
        }
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

    @GetMapping("/qrAuthorize")
    public String qrAuthorize(@RequestParam("returnUrl") String returnUrl) {
        String url = projectUrlConfig.getWechatOpenAuthorize() + "/sell/wechat/qrUserInfo";
        String redirectUrl = null;
        try {
            redirectUrl = wxOpenService.buildQrConnectUrl(url, WxConsts.QRCONNECT_SCOPE_SNSAPI_LOGIN, URLEncoder.encode(returnUrl, "UTF-8"));
            return "redirect:" + redirectUrl;
        } catch (UnsupportedEncodingException e) {
            log.error("[微信网页授权]" + e);
            throw new SellException(ResultEnum.WECHAT_MP_ERROR.getCode(), e.getMessage());
        }
    }

    @GetMapping("/qrUserInfo")
    public String qrUserInfo(@RequestParam("code") String code,
                             @RequestParam("state") String returnUrl) {
        try {
            WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxOpenService.oauth2getAccessToken(code);
            log.info("wxMpOAuth2AccessToken" + wxMpOAuth2AccessToken);
            String openId = wxMpOAuth2AccessToken.getOpenId();
            return String.format("redirect:%s?openid=%s", returnUrl, openId);
        } catch (WxErrorException e) {
            log.error("[微信网页授权]" + e);
            throw new SellException(ResultEnum.WECHAT_MP_ERROR.getCode(), e.getError().getErrorMsg());
        }
    }
}
