package com.fpms.service;

import com.fpms.entity.ProductLibraryStandard;

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
}
