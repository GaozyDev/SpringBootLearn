package com.gl.springbootlearn.repository;

import com.gl.springbootlearn.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {

    OrderDetail findByOrderId(String orderId);
}
