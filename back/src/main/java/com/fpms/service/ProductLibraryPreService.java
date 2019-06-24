package com.fpms.service;

import com.fpms.entity.ProductLibraryPre;

/**
 * @author     : YongBiao Liao
 * @date       : 2019/6/14 14:49
 * @description:
 * @modified   :
 */
public interface ProductLibraryPreService {
    Boolean modifyProduct(ProductLibraryPre productLibraryPre);
    Boolean addProduct(ProductLibraryPre productLibraryPre);
}
