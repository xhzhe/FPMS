package com.fpms.service;

import com.fpms.entity.ProductLibraryStandard;

import java.util.List;

/**
 * @author     : HuiZhe Xu
 * @date       : 2019/6/14 14:51
 * @description:
 * @modified   :
 */
public interface ProductLibraryStandardService {
    /**
     *  下架产品
     * @author     : HuiZhe Xu
     * @date       : Created in 2019/6/25 11:05
     * @param       ID
     * @return     : java.lang.Boolean
     */
    Boolean obetainProducts(Integer ID);

    /**
     *  通过标准库iD获取标准库产品
     * @author     ：HuiZhe Xu
     * @date       ：Created in 2019/6/25 10:34
     * @param       productStdId
     * @return     : com.fpms.entity.ProductLibraryStandard
     */
    ProductLibraryStandard selectById(Integer productStdId);

    /**
     *  通过标准库
     * @author     ：HuiZhe Xu
     * @date       ：Created in 2019/6/25 10:35
     * @param       productLibraryStandard
     * @return     : void
     */
    void updateProductStandard(ProductLibraryStandard productLibraryStandard);

    /**
     *  上架产品
     * @author     : HuiZhe Xu
     * @date       : Created in 2019/6/26 10:35
     * @param       Id
     * @return     : java.lang.Boolean
     */
    Boolean uploadProduct(Integer Id);

    /**
     * 通过产品预选id查找标准库产品
     * @author     ：YongBiao Liao
     * @date       ：Created in 2019/6/28 16:44
     * @param       productPreId
     * @return     : com.fpms.entity.ProductLibraryStandard
     */
    ProductLibraryStandard selectByProductPreId(Integer productPreId);

    /**
     *  获取所有标准库产品
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/28 16:50
     * @param
     * @return     : java.util.List<com.fpms.entity.ProductLibraryStandard>
     */
    public List<ProductLibraryStandard> getAll();
}
