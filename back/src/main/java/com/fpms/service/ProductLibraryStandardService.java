package com.fpms.service;

import com.fpms.entity.ProductLibraryStandard;

/**
 * @author     : YongBiao Liao
 * @date       : 2019/6/14 14:51
 * @description:
 * @modified   :
 */
public interface ProductLibraryStandardService {
    Boolean obetainProducts(Integer ID);

    /**
     *  通过标准库iD获取标准库产品
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/25 10:34
     * @param       productStdId
     * @return     : com.fpms.entity.ProductLibraryStandard
     */
    ProductLibraryStandard selectById(Integer productStdId);

    /**
     *  通过标准库
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/25 10:35
     * @param       productLibraryStandard
     * @return     : void
     */
    void updateProductStandard(ProductLibraryStandard productLibraryStandard);
}
