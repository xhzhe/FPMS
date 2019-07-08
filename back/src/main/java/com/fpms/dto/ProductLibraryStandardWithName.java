package com.fpms.dto;

import com.fpms.entity.ProductLibraryStandard;

/**
 * @author : YongBiao Liao
 * @date : 2019/7/8 20:37
 * @description:
 * @modified :
 */
public class ProductLibraryStandardWithName{

    private ProductLibraryStandard productLibraryStandard;

    private String productName;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public ProductLibraryStandard getProductLibraryStandard() {
        return productLibraryStandard;
    }

    public void setProductLibraryStandard(ProductLibraryStandard productLibraryStandard) {
        this.productLibraryStandard = productLibraryStandard;
    }
}
