package com.fpms.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author : YongBiao Liao
 * @date : 2019/6/28 9:51
 * @description:
 * @modified :
 */
public class ConProDto {

    private Integer productConId;

    private List<String> productName;

    private List<BigDecimal> percentage;

    public Integer getProductConId() {
        return productConId;
    }

    public void setProductConId(Integer productConId) {
        this.productConId = productConId;
    }

    public List<BigDecimal> getPercentage() {
        return percentage;
    }

    public void setPercentage(List<BigDecimal> percentage) {
        this.percentage = percentage;
    }

    public List<String> getProductName() {
        return productName;
    }

    public void setProductName(List<String> productName) {
        this.productName = productName;
    }
}
