package com.fpms.service;

import com.fpms.entity.ProductCategory;

import java.util.List;

/**
 * @author     : YongBiao Liao
 * @date       : 2019/6/14 14:47
 * @description:
 * @modified   :
 */
public interface ProductCategoryService {
    void addProductCategory(ProductCategory productCategory);

    void updateProductCategory(ProductCategory productCategory);

    void delProductCategory(Integer productCategory);

    List<ProductCategory> selectAllProductCategory();
}
