package com.gl.springbootlearn.form;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductForm {

    private String productId;

    private BigDecimal productPrice;

    private Integer productStock;

    private String productDescription;

    private String productIcon;

    private Integer categoryType;
}
