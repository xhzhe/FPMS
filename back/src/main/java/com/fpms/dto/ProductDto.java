package com.fpms.dto;

import com.fpms.entity.ProductLibraryPre;
import com.fpms.entity.ProductLibraryStandard;

/**
 * @author : YongBiao Liao
 * @date : 2019/7/3 23:23
 * @description: 封装上架的的产品的信息
 * @modified :
 */
public class ProductDto {
    private ProductLibraryPre productLibraryPre;

    private ProductLibraryStandard productLibraryStandard;

    public ProductLibraryPre getProductLibraryPre() {
        return productLibraryPre;
    }

    public void setProductLibraryPre(ProductLibraryPre productLibraryPre) {
        this.productLibraryPre = productLibraryPre;
    }

    public ProductLibraryStandard getProductLibraryStandard() {
        return productLibraryStandard;
    }

    public void setProductLibraryStandard(ProductLibraryStandard productLibraryStandard) {
        this.productLibraryStandard = productLibraryStandard;
    }
}
