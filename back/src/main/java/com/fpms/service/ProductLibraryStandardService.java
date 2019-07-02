package com.fpms.service;

import com.fpms.dto.ProductWithName;
import com.fpms.entity.ProductLibraryStandard;

import java.util.ArrayList;
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
     * @param       id
     * @return     : java.lang.Boolean
     */
    Boolean obtainedProducts(Integer id) throws Exception;

    /**
     *  通过标准库iD获取标准库产品
     * @author     ：HuiZhe Xu
     * @date       ：Created in 2019/6/25 10:34
     * @param       productStdId
     * @return     : com.fpms.entity.ProductLibraryStandard
     */
    ProductLibraryStandard selectById(Integer productStdId) throws Exception;

    /**
     *  通过标准库
     * @author     ：HuiZhe Xu
     * @date       ：Created in 2019/6/25 10:35
     * @param       productLibraryStandard
     * @return     : boolean
     */
    boolean updateProductStandard(ProductLibraryStandard productLibraryStandard) throws Exception;

    /**
     *  上架产品
     * @author     : HuiZhe Xu
     * @date       : Created in 2019/6/26 10:35
     * @param       id
     * @return     : java.lang.Boolean
     */
    Boolean uploadProduct(Integer id) throws Exception;

    /**
     * 通过产品预选id查找标准库产品
     * @author     ：YongBiao Liao
     * @date       ：Created in 2019/6/28 16:44
     * @param       productPreId
     * @return     : com.fpms.entity.ProductLibraryStandard
     */
    ProductLibraryStandard selectByProductPreId(Integer productPreId) throws Exception;

    /**
     *  获取所有标准库产品
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/28 16:50
     * @param
     * @return     : ArrayList<ProductWithName>
     */
    public ArrayList<ProductWithName> getAll() throws Exception;
}
