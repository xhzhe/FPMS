package com.fpms.service;


import com.fpms.entity.Supplier;

import java.util.List;


/**
 * @author : HuiZhe Xu
 * @date : 2019/6/14 14:58
 * @description: 供应商类
 * @modified :
 */

public interface SupplierService {
    /**
     *  添加员工
     * @author     : HuiZhe Xu
     * @date       : Created in 2019/6/26 14:57
     * @param       supplier
     */
    void addSupplier(Supplier supplier) throws Exception;

    /**
     *  获取供应商
     * @author     : HuiZhe Xu
     * @date       : Created in 2019/6/26 15:04
     * @param       id
     * @return     : com.fpms.entity.Supplier
     */
    Supplier getSupplier(Integer id) throws Exception;

    /**
     *  获取所有供应商
     * @author     : HuiZhe Xu
     * @date       : Created in 2019/6/26 15:14
     * @param
     * @return     : java.util.List<com.fpms.entity.Supplier>
     */
    List<Supplier> getSuppliers() throws Exception;
}
