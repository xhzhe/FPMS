package com.fpms.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author : YongBiao Liao
 * @date : 2019/6/14 15:00
 * @description: 产品预审批库实体类
 * @modified :
 */
public class ProductLibraryPre {
    private Integer productPreId;

    private Integer supplierId;

    private Integer staffId;

    private String productName;

    private String productDesc;

    private BigDecimal productPrice;

    private Byte productStatus;

    private Integer categoryId;

    private BigDecimal purchaseStartPoint;

    private Integer term;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date expiryDate;

    private Byte riskRating;

    private BigDecimal returnRate;

    private BigDecimal performanceBenchmark;

    private Byte incomeType;

    private BigDecimal unitNetValue;

    private BigDecimal cumulativeNetValue;

    private String notice;

    private Integer purchaseLimit;

    private BigDecimal commission;

    private Date crateTime;

    public Integer getProductPreId() {
        return productPreId;
    }

    public void setProductPreId(Integer productPreId) {
        this.productPreId = productPreId;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc == null ? null : productDesc.trim();
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public Byte getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Byte productStatus) {
        this.productStatus = productStatus;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public BigDecimal getPurchaseStartPoint() {
        return purchaseStartPoint;
    }

    public void setPurchaseStartPoint(BigDecimal purchaseStartPoint) {
        this.purchaseStartPoint = purchaseStartPoint;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Byte getRiskRating() {
        return riskRating;
    }

    public void setRiskRating(Byte riskRating) {
        this.riskRating = riskRating;
    }

    public BigDecimal getReturnRate() {
        return returnRate;
    }

    public void setReturnRate(BigDecimal returnRate) {
        this.returnRate = returnRate;
    }

    public BigDecimal getPerformanceBenchmark() {
        return performanceBenchmark;
    }

    public void setPerformanceBenchmark(BigDecimal performanceBenchmark) {
        this.performanceBenchmark = performanceBenchmark;
    }

    public Byte getIncomeType() {
        return incomeType;
    }

    public void setIncomeType(Byte incomeType) {
        this.incomeType = incomeType;
    }

    public BigDecimal getUnitNetValue() {
        return unitNetValue;
    }

    public void setUnitNetValue(BigDecimal unitNetValue) {
        this.unitNetValue = unitNetValue;
    }

    public BigDecimal getCumulativeNetValue() {
        return cumulativeNetValue;
    }

    public void setCumulativeNetValue(BigDecimal cumulativeNetValue) {
        this.cumulativeNetValue = cumulativeNetValue;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice == null ? null : notice.trim();
    }

    public Integer getPurchaseLimit() {
        return purchaseLimit;
    }

    public void setPurchaseLimit(Integer purchaseLimit) {
        this.purchaseLimit = purchaseLimit;
    }

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    public Date getCrateTime() {
        return crateTime;
    }

    public void setCrateTime(Date crateTime) {
        this.crateTime = crateTime;
    }
}