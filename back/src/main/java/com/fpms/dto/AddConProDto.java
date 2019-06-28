package com.fpms.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author : YongBiao Liao
 * @date : 2019/6/28 16:31
 * @description:
 * @modified :
 */
public class AddConProDto {
    private Integer productConId;

    private String productName;

    private BigDecimal percentage;

    public Integer getProductConId() {
        return productConId;
    }

    public void setProductConId(Integer productConId) {
        this.productConId = productConId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPercentage() {
        return percentage;
    }

    public void setPercentage(BigDecimal percentage) {
        this.percentage = percentage;
    }
}
