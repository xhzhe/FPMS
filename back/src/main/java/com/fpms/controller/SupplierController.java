package com.fpms.controller;

import com.fpms.annotation.OperationLog;
import com.fpms.entity.Supplier;
import com.fpms.entity.pojo.ResultBean;
import com.fpms.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : xuhuizhe
 * @date : 2019/6/26 14:50
 * @description:
 * @modified :
 */
@RestController
@CrossOrigin
public class SupplierController {
    private SupplierService supplierService;

    @Autowired
    public SupplierController(SupplierService supplierService){
        this.supplierService=supplierService;
    }

    /**
     *  添加供应商
     * @author     : HuiZhe Xu
     * @date       : Created in 2019/7/2 15:21
     * @param       supplier :
     * @return     : com.fpms.entity.pojo.ResultBean<java.lang.Boolean>
     */
    @OperationLog("添加供应商")
    @PostMapping("/supplier")
    public ResultBean<Boolean> addSupplier(@RequestBody Supplier supplier) {
        ResultBean<Boolean> res = new ResultBean<>();
        try {
            if(supplier.getSupplierId()!=null){
                throw new Exception("禁止传入id");
            }
            supplierService.addSupplier(supplier);
            res.setData(true);
        } catch (Exception e) {
            return new ResultBean<>(e);
        }
        return res;
    }

    /**
     * 获取供应商byID
     *
     * @param supplierId
     * @return : com.fpms.entity.pojo.ResultBean<com.fpms.entity.Supplier>
     * @author : HuiZhe Xu
     * @date : Created in 2019/6/26 15:12
     */
    @OperationLog("获取供应商")
    @GetMapping("/supplier/{supplierId}")
    public ResultBean<Supplier> getSupplier(@PathVariable Integer supplierId) {
        try {
            Supplier supplier = supplierService.getSupplier(supplierId);
            ResultBean<Supplier> res = new ResultBean<>();
            res.setData(supplier);
            return res;
        } catch (Exception e) {
            return new ResultBean<>(e);
        }
    }

    /**
     * 获取所有供应商
     *
     * @param
     * @return : com.fpms.entity.pojo.ResultBean<java.util.List<com.fpms.entity.Supplier>>
     * @author : HuiZhe Xu
     * @date : Created in 2019/6/26 15:16
     */
    @OperationLog("获取所有供应商")
    @GetMapping("/supplier")
    public ResultBean<List<Supplier>> getAllSupplier() {
        try {
            List<Supplier> suppliers = supplierService.getSuppliers();
            ResultBean<List<Supplier>> res = new ResultBean<>();
            res.setData(suppliers);
            return res;
        } catch (Exception e) {
            return new ResultBean<>(e);
        }
    }

    /**
     *  删除供应商
     * @author     : HuiZhe Xu
     * @date       : Created in 2019/7/2 15:31
     * @param       supplierId
     * @return     : com.fpms.entity.pojo.ResultBean<java.lang.Boolean>
     */
    @OperationLog("删除供应商")
    @DeleteMapping("/supplier/{supplierId}")
    public ResultBean<Boolean> delSupplier(@PathVariable Integer supplierId){
        try{
            if(supplierId==null){
                throw new Exception("没有传入id");
            }
            if(supplierId<0){
                throw new Exception("不合法id");
            }
            supplierService.deleteSupplierById(supplierId);
            ResultBean<Boolean> res = new ResultBean<>();
            res.setData(true);
            return res;
        }catch (Exception e){
            return new ResultBean<>(e);
        }
    }

    /**
     *  修改供应商
     * @author     : HuiZhe Xu
     * @date       : Created in 2019/7/2 15:35
     * @param       supplier
     * @return     : com.fpms.entity.pojo.ResultBean<java.lang.Boolean>
     */
    @OperationLog("修改供应商")
    @PutMapping("/supplier")
    public ResultBean<Boolean> modifySupplier(@RequestBody Supplier supplier){
        try{
            if(supplier.getSupplierId()==null){
                throw new Exception("没有该供应商的id");
            }
            supplierService.modifySupplier(supplier);
            ResultBean<Boolean> res = new ResultBean<>();
            res.setData(true);
            return res;
        }catch (Exception e){
            return new ResultBean<>(e);
        }
    }
}
