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
    @Autowired
    private SupplierService supplierService;
    @OperationLog("添加供应商")
    @PostMapping("/supplier")
    public ResultBean<Boolean> addSupplier(@RequestBody Supplier supplier){
        ResultBean<Boolean> res = new ResultBean<>();
        try{
            boolean success=supplierService.addSupplier(supplier);
            if(success){
                res.setState(ResultBean.SUCCESS);
                res.setMsg(ResultBean.SUCC_MSG);
                res.setData(success);
            }else{
                throw new Exception("添加失败");
            }
        }catch (Exception e){
            res=new ResultBean<>(e);
            res.setData(false);
        }
        return res;
    }
    /**
     *  获取供应商byID
     * @author     : HuiZhe Xu
     * @date       : Created in 2019/6/26 15:12
     * @param       supplierId
     * @return     : com.fpms.entity.pojo.ResultBean<com.fpms.entity.Supplier>
     */
    @OperationLog("获取供应商")
    @GetMapping("/supplier/{supplierId}")
    public ResultBean<Supplier> getSupplier(@PathVariable Integer supplierId){
        try{
            Supplier supplier=supplierService.getSupplier(supplierId);
            if(supplier==null){
                throw new Exception("供应商不存在");
            }else{
                ResultBean<Supplier> res = new ResultBean<>();
                res.setData(supplier);
                res.setState(ResultBean.SUCCESS);
                res.setMsg(ResultBean.SUCC_MSG);
                return res;
            }
        }catch (Exception e){
            ResultBean<Supplier> res = new ResultBean<>(e);
            res.setData(null);
            return res;
        }
    }
    /**
     *  获取所有供应商
     * @author     : HuiZhe Xu
     * @date       : Created in 2019/6/26 15:16
     * @param
     * @return     : com.fpms.entity.pojo.ResultBean<java.util.List<com.fpms.entity.Supplier>>
     */
    @OperationLog("获取所有供应商")
    @GetMapping("/supplier")
        public ResultBean<List<Supplier>> getAllSupplier(){
            try {
                List<Supplier> suppliers = supplierService.getSuppliers();
                if (suppliers == null) {
                    throw new Exception("没有供应商");
                }else{
                    ResultBean<List<Supplier>> res = new ResultBean<>();
                    res.setData(suppliers);
                    res.setState(ResultBean.SUCCESS);
                    res.setMsg(ResultBean.SUCC_MSG);
                    return res;
                }
            }catch (Exception e){
                ResultBean<List<Supplier>> res = new ResultBean<>(e);
                res.setData(null);
                return res;
            }
    }
}
