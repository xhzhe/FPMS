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
     * @author : HuiZhe Xu
     * @date : Created in 2019/6/26 14:57
     */
    @Override
    public void addSupplier(Supplier supplier) throws Exception {
        int count = supplierDao.insertSelective(supplier);
        if (count > 0) {
            return;
        }
        throw new Exception("供应商插入失败");
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
    public Supplier getSupplier(Integer id) throws Exception {
        Supplier supplier = supplierDao.selectByPrimaryKey(id);
        if (supplier == null) {
            throw new Exception("没有这个供应商");
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
    public List<Supplier> getSuppliers() throws Exception {
        List<Supplier> suppliers = supplierDao.getAllSupplier();
        if (suppliers == null) {
            throw new Exception("供应商列表为空");
        }
        return suppliers;
    }

    /**
     * 删除供应商
     *
     * @param id
     * @return : void
     * @author : HuiZhe Xu
     * @date : Created in 2019/7/2 15:27
     */
    @Override
    public void deleteSupplierById(Integer id) throws Exception {
        getSupplier(id);
        int count = supplierDao.deleteByPrimaryKey(id);
        if (count <= 0) {
            throw new Exception("删除失败");
        }
    }

    /**
     * 修改供应商
     *
     * @param supplier
     * @return : void
     * @author : HuiZhe Xu
     * @date : Created in 2019/7/2 15:33
     */
    @Override
    public void modifySupplier(Supplier supplier) throws Exception {
        getSupplier(supplier.getSupplierId());
        int count = supplierDao.updateByPrimaryKeySelective(supplier);
        if (count <= 0) {
            throw new Exception("修改失败");
        }
    }
}
