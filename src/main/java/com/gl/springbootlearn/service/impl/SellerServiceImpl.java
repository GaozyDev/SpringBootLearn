package com.gl.springbootlearn.service.impl;

import com.gl.springbootlearn.dataobject.SellerInfo;
import com.gl.springbootlearn.repository.SellerInfoRepository;
import com.gl.springbootlearn.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerInfoRepository repository;

    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return repository.findByOpenid(openid);
    }
}
