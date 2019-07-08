package com.fpms.dto;

import com.fpms.entity.ProductLibraryConfiguration;
import com.fpms.entity.ProductLibraryPre;
import com.fpms.entity.ProductLibraryStandard;

import java.util.List;

/**
 * @author : YongBiao Liao
 * @date : 2019/7/8 21:04
 * @description:
 * @modified :
 */
public class ConWithProNameDto {
    ProductLibraryConfiguration productLibraryConfiguration;

    List<ProductLibraryStandardWithName> productLibraryStandardWithNameList;

    List<ProductLibraryPre> productLibraryPreList;

    public ProductLibraryConfiguration getProductLibraryConfiguration() {
        return productLibraryConfiguration;
    }

    public void setProductLibraryConfiguration(ProductLibraryConfiguration productLibraryConfiguration) {
        this.productLibraryConfiguration = productLibraryConfiguration;
    }

    public List<ProductLibraryStandardWithName> getProductLibraryStandardWithNameList() {
        return productLibraryStandardWithNameList;
    }

    public void setProductLibraryStandardWithNameList(List<ProductLibraryStandardWithName> productLibraryStandardWithNameList) {
        this.productLibraryStandardWithNameList = productLibraryStandardWithNameList;
    }

    public List<ProductLibraryPre> getProductLibraryPreList() {
        return productLibraryPreList;
    }

    public void setProductLibraryPreList(List<ProductLibraryPre> productLibraryPreList) {
        this.productLibraryPreList = productLibraryPreList;
    }
}
