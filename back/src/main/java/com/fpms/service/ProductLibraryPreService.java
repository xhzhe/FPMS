package com.fpms.service;

import com.fpms.entity.ProductLibraryPre;

import java.util.List;

/**
 * @author     : HuiZhe Xu
 * @date       : 2019/6/14 14:49
 * @description:
 * @modified   :
 */
public interface ProductLibraryPreService {
    /**
     *  修改产品属性
     * @author     : HuiZhe Xu
     * @date       : Created in 2019/6/25 11:06
     * @param       productLibraryPre
     * @return     : java.lang.Boolean
     */
    Boolean modifyProduct(ProductLibraryPre productLibraryPre);
    /**
     *  添加产品
     * @author     : HuiZhe Xu
     * @date       : Created in 2019/6/25 11:06
     * @param       productLibraryPre
     * @return     : java.lang.Boolean
     */
    Boolean addProduct(ProductLibraryPre productLibraryPre);

    /**
     *  通过预选库Id获取预选库产品
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/25 12:04
     * @param       productPreId
     * @return     : com.fpms.entity.ProductLibraryPre
     */
    ProductLibraryPre selectById(Integer productPreId);

    /**
     *  获取未评审的产品
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/25 12:54
     * @param
     * @return     : java.util.List<com.fpms.entity.ProductLibraryPre>
     */
    List<ProductLibraryPre> getUnReviewProductList();

    /**
     * 获取所有预选库的产品
     * @author     ：YongBiao Liao
     * @date       ：Created in 2019/6/26 11:40
     * @param
     * @return     : java.util.List<com.fpms.entity.ProductLibraryPre>
     */
    List<ProductLibraryPre> getAllProductPres();

    /**
     *  通过标准库Id获得预选库的产品信息
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/26 15:58
     * @param       productStdId
     * @return     : com.fpms.entity.ProductLibraryPre
     */
    ProductLibraryPre selectByStdId(Integer productStdId);
}
