package com.fpms.service;

import com.fpms.entity.ProductUser;

/**
 * @author : TianHong Liao
 * @date : 2019/6/28 9:27
 * @description:
 * @modified :
 */
public interface ProductUserService {
    /**
     *  新增一个用户的产品
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/28 9:29
     * @param       productUser
     * @return     : void
     */
    void addProductUser(ProductUser productUser);
}
