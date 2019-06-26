package com.fpms.dto;

import com.fpms.entity.ProductLibraryConfiguration;
import com.fpms.entity.ProductLibraryPre;
import com.fpms.entity.ProductLibraryStandard;

import java.util.List;

/**
 * @author : YongBiao Liao
 * @date : 2019/6/26 16:17
 * @description:
 * @modified :
 */
public class ConfigurationDto{

    ProductLibraryConfiguration productLibraryConfiguration;
    List<ProductLibraryStandard> productLibraryStandardList;
    List<ProductLibraryPre> productLibraryPreList;

    public ProductLibraryConfiguration getProductLibraryConfiguration() {
        return productLibraryConfiguration;
    }

    public void setProductLibraryConfiguration(ProductLibraryConfiguration productLibraryConfiguration) {
        this.productLibraryConfiguration = productLibraryConfiguration;
    }

    public List<ProductLibraryStandard> getProductLibraryStandardList() {
        return productLibraryStandardList;
    }

    public void setProductLibraryStandardList(List<ProductLibraryStandard> productLibraryStandardList) {
        this.productLibraryStandardList = productLibraryStandardList;
    }

    public List<ProductLibraryPre> getProductLibraryPreList() {
        return productLibraryPreList;
    }

    public void setProductLibraryPreList(List<ProductLibraryPre> productLibraryPreList) {
        this.productLibraryPreList = productLibraryPreList;
    }
}
