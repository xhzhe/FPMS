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
     * 添加员工
     *
     * @param supplier
     * @author : HuiZhe Xu
     * @date : Created in 2019/6/26 14:57
     */
    void addSupplier(Supplier supplier) throws Exception;

    /**
     * 获取供应商
     *
     * @param id
     * @return : com.fpms.entity.Supplier
     * @author : HuiZhe Xu
     * @date : Created in 2019/6/26 15:04
     */
    Supplier getSupplier(Integer id) throws Exception;

    /**
     * 获取所有供应商
     *
     * @param
     * @return : java.util.List<com.fpms.entity.Supplier>
     * @author : HuiZhe Xu
     * @date : Created in 2019/6/26 15:14
     */
    List<Supplier> getSuppliers() throws Exception;

    /**
     * 删除供应商
     *
     * @param id :
     * @return : void
     * @author : HuiZhe Xu
     * @date : Created in 2019/7/2 15:27
     */
    void deleteSupplierById(Integer id) throws Exception;

    /**
     * 修改供应商
     *
     * @param supplier
     * @return : void
     * @author : HuiZhe Xu
     * @date : Created in 2019/7/2 15:33
     */
    void modifySupplier(Supplier supplier) throws Exception;
}
