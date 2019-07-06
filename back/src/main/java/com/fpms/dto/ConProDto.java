package com.fpms.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;
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

    private String productConName;

    public String getProductConName() {
        return productConName;
    }

    public void setProductConName(String productConName) {
        this.productConName = productConName;
    }

    public Integer getProductConNum() {
        return productConNum;
    }

    public void setProductConNum(Integer productConNum) {
        this.productConNum = productConNum;
    }

    public String getProductConDesc() {
        return productConDesc;
    }

    public void setProductConDesc(String productConDesc) {
        this.productConDesc = productConDesc;
    }

    public BigDecimal getProductConPrice() {
        return productConPrice;
    }

    public void setProductConPrice(BigDecimal productConPrice) {
        this.productConPrice = productConPrice;
    }

    private Integer productConNum;

    private String productConDesc;

    private BigDecimal productConPrice;

    public List<Integer> getProductStdId() {
        return productStdId;
    }

    public void setProductStdId(List<Integer> productStdId) {
        this.productStdId = productStdId;
    }

    private List<Integer> productStdId;

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
