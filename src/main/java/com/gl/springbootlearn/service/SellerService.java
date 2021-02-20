package com.gl.springbootlearn.service;

import com.gl.springbootlearn.dataobject.SellerInfo;

public interface SellerService {

    SellerInfo findSellerInfoByOpenid(String openid);
}
