package com.fpms.dto;

import com.fpms.entity.Order;
import com.fpms.entity.ProductLibraryConfiguration;
import com.fpms.entity.ProductLibraryStandard;
import com.fpms.entity.User;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author : TianHong Liao
 * @date : 2019/6/25 11:43
 * @description:订单Dto
 * @modified :
 */
public class OrderDto {
    private Order order;
    private String userName;
    private ProductLibraryConfiguration productLibraryConfiguration;
    private ProductLibraryStandard productLibraryStandard;
    private String productName;
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ProductLibraryConfiguration getProductLibraryConfiguration() {
        return productLibraryConfiguration;
    }

    public void setProductLibraryConfiguration(ProductLibraryConfiguration productLibraryConfiguration) {
        this.productLibraryConfiguration = productLibraryConfiguration;
    }

    public ProductLibraryStandard getProductLibraryStandard() {
        return productLibraryStandard;
    }

    public void setProductLibraryStandard(ProductLibraryStandard productLibraryStandard) {
        this.productLibraryStandard = productLibraryStandard;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
