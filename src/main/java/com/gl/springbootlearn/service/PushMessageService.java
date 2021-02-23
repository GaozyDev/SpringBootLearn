package com.gl.springbootlearn.service;

import com.gl.springbootlearn.dto.OrderDTO;

public interface PushMessageService {

    void orderStatus(OrderDTO orderDTO);
}
