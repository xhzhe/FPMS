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
    /**
     *  新增目录
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/20 16:06
     * @param       productCategory
     * @return     : void
     */
    void addProductCategory(ProductCategory productCategory);

    /**
     *  更新目录
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/20 16:06
     * @param       productCategory
     * @return     : void
     */
    void updateProductCategory(ProductCategory productCategory);

    /**
     *  删除目录
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/20 16:07
     * @param       productCategory
     * @return     : void
     */
    void delProductCategory(Integer productCategory);

    /**
     *  返回所有目录
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/20 16:07
     * @param
     * @return     : java.util.List<com.fpms.entity.ProductCategory>
     */
    List<ProductCategory> selectAllProductCategory();
}
