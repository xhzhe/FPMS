package com.fpms.service;

import com.fpms.entity.ProductLibraryPre;

import java.util.List;

/**
 * @author : HuiZhe Xu
 * @date : 2019/6/14 14:49
 * @description:
 * @modified :
 */
public interface ProductLibraryPreService {
    /**
     * 修改产品属性
     *
     * @param productLibraryPre
     * @throws Exception when nothing found there
     * @author : HuiZhe Xu
     * @date : Created in 2019/6/25 11:06
     */
    void modifyProduct(ProductLibraryPre productLibraryPre) throws Exception;

    /**
     * 添加产品
     *
     * @param productLibraryPre
     * @author : HuiZhe Xu
     * @date : Created in 2019/6/25 11:06
     */
    void addProduct(ProductLibraryPre productLibraryPre) throws Exception;

    /**
     * 通过预选库Id获取预选库产品
     *
     * @param productPreId
     * @return : com.fpms.entity.ProductLibraryPre
     * @author ：TianHong Liao
     * @date ：Created in 2019/6/25 12:04
     */
    ProductLibraryPre selectById(Integer productPreId) throws Exception;

    /**
     * 新的获取productpre
     *
     * @param productPreId
     * @return : java.lang.Object
     * @author : HuiZhe Xu
     * @date : Created in 2019/7/9 11:46
     */
    Object selectByIdNew(Integer productPreId) throws Exception;

    /**
     * 获取未评审的产品
     *
     * @param
     * @return : java.util.List<com.fpms.entity.ProductLibraryPre>
     * @author ：TianHong Liao
     * @date ：Created in 2019/6/25 12:54
     */
    List<ProductLibraryPre> getUnReviewProductList() throws Exception;

    /**
     * 获取所有预选库的产品
     *
     * @param
     * @return : Object
     * @author ：YongBiao Liao
     * @date ：Created in 2019/6/26 11:40
     */
    List<Object> getAllProductPres() throws Exception;

    /**
     * 通过标准库Id获得预选库的产品信息
     *
     * @param productStdId
     * @return : com.fpms.entity.ProductLibraryPre
     * @author ：TianHong Liao
     * @date ：Created in 2019/6/26 15:58
     */
    ProductLibraryPre selectByStdId(Integer productStdId) throws Exception;

    /**
     * 通过产品名查找产品
     *
     * @param productName
     * @return : com.fpms.entity.ProductLibraryPre
     * @author ：YongBiao Liao
     * @date ：Created in 2019/6/28 16:36
     */
    ProductLibraryPre selectByProductName(String productName) throws Exception;
}
