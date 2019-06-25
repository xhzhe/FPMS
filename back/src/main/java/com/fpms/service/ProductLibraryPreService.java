package com.fpms.service;

import com.fpms.entity.ProductLibraryPre;

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
}
