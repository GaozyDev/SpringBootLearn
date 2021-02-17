package com.gl.springbootlearn.service;

import com.gl.springbootlearn.dto.OrderDTO;
import com.lly835.bestpay.model.PayResponse;

public interface PayService {

    PayResponse create(OrderDTO orderDTO);
}
