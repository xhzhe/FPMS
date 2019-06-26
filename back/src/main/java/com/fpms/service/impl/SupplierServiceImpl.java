package com.fpms.service.impl;

import com.fpms.dao.SupplierDao;
import com.fpms.entity.Supplier;
import com.fpms.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : xuhuizhe
 * @date : 2019/6/26 14:56
 * @description:
 * @modified :
 */
@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierDao supplierDao;
    /**
     * 添加员工
     *
     * @param supplier
     * @return : boolean
     * @author : HuiZhe Xu
     * @date : Created in 2019/6/26 14:57
     */
    @Override
    public boolean addSupplier(Supplier supplier) {
        int count=supplierDao.insertSelective(supplier);
        if(count>0){
            return true;
        }
        return false;
    }

    /**
     * 获取供应商
     *
     * @param id
     * @return : com.fpms.entity.Supplier
     * @author : HuiZhe Xu
     * @date : Created in 2019/6/26 15:04
     */
    @Override
    public Supplier getSupplier(Integer id) {
        Supplier supplier=supplierDao.selectByPrimaryKey(id);
        if(supplier==null){
            return null;
        }
        return supplier;
    }

    /**
     * 获取所有供应商
     *
     * @return : java.util.List<com.fpms.entity.Supplier>
     * @author : HuiZhe Xu
     * @date : Created in 2019/6/26 15:14
     */
    @Override
    public List<Supplier> getSuppliers() {
        List<Supplier> suppliers=supplierDao.getAllSupplier();

        return suppliers;
    }
}
