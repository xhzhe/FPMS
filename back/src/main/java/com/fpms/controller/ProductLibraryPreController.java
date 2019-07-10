package com.fpms.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fpms.annotation.OperationLog;
import com.fpms.entity.ProductLibraryPre;
import com.fpms.entity.ProductLibraryStandard;
import com.fpms.entity.pojo.ResultBean;
import com.fpms.service.ProductLibraryPreService;
import com.fpms.service.ProductLibraryStandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author : HuiZhe Xu
 * @date : 2019/6/14 14:59
 * @description: 产品预选库逻辑控制器
 * @modified :
 */
@RestController
@CrossOrigin
public class ProductLibraryPreController {
//    @Autowired
    /**
     * 预选库服务
     *
     * @author : HuiZhe Xu
     * @date : Created in 2019/7/2 9:57
     */
    private ProductLibraryPreService productLibraryPreService;

    @Autowired
    public ProductLibraryPreController(ProductLibraryPreService productLibraryPreService) {
        this.productLibraryPreService = productLibraryPreService;
    }

    /**
     * 修改产品属性
     *
     * @param product
     * @return : com.fpms.entity.ResultBean<java.lang.Boolean>
     * @author : HuiZhe Xu
     * @date : Created in 2019/6/25 10:59
     */
    @OperationLog(value = "修改产品属性")
    @PutMapping("/product")
    @Transactional(rollbackFor = Exception.class)
    public ResultBean<Boolean> modifyProduct(@RequestBody ProductLibraryPre product) {
        ResultBean<Boolean> res = new ResultBean<>();
        try {
            if (product.getProductPreId() == null) {
                throw new Exception("缺少ID");
            }
            productLibraryPreService.modifyProduct(product);
            res.setData(true);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ResultBean<>(e);
        }
        return res;
    }

    /**
     * 上传商品
     *
     * @param product
     * @return : com.fpms.entity.ResultBean<java.lang.Boolean>
     * @author : HuiZhe Xu
     * @date : Created in 2019/6/25 10:59
     */
    @OperationLog(value = "新增预选库产品")
    @PostMapping("/productPre")
    public ResultBean<Boolean> addProduct(@RequestBody ProductLibraryPre product) {
        ResultBean<Boolean> res = new ResultBean<>();
        try {
            if(product.getUnitNetValue().compareTo(new BigDecimal("9999999"))>=0){
                throw new Exception("单位挣值过高");
            }
            if(product.getCumulativeNetValue().compareTo(new BigDecimal("9999999"))>=0){
                throw new Exception("累计挣值过高");
            }
            productLibraryPreService.addProduct(product);
            res.setData(true);
        } catch (Exception e) {
            return new ResultBean<>(e);
        }
        return res;
    }

    /**
     * 获取未评估的预选库产品列表
     *
     * @param
     * @return : com.fpms.entity.ResultBean<java.util.List<com.fpms.entity.ProductLibraryPre>>
     * @author ：TianHong Liao
     * @date ：Created in 2019/6/25 13:03
     */
    @GetMapping("/unReviewProductPres")
    public ResultBean<List<ProductLibraryPre>> getUnReviewProductPre() {
        List<ProductLibraryPre> unReviewProductList;
        try {
            unReviewProductList = productLibraryPreService.getUnReviewProductList();
        } catch (Exception e) {
            return new ResultBean<>(e);
        }
        return new ResultBean<>(unReviewProductList);
    }

    /**
     * 获取预选库的所有产品
     *
     * @param
     * @return : com.fpms.entity.pojo.ResultBean<java.util.List<com.fpms.entity.ProductLibraryPre>>
     * @author ：YongBiao Liao
     * @date ：Created in 2019/6/26 11:46
     */
    @GetMapping(value = "/productPres")
    public ResultBean<List<Object>> getAllProductPres() {
        List<Object> productLibraryPres;
        try {
            productLibraryPres = productLibraryPreService.getAllProductPres();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultBean<>(e);
        }
        return new ResultBean<>(productLibraryPres);
    }

    /**
     * 查找预选库产品
     *
     * @param productPreId
     * @return : Object
     * @author : HuiZhe Xu
     * @date : Created in 2019/7/2 15:57
     */
    @OperationLog("按id查找预选库产品")
    @GetMapping("/productPre/{productPreId}")
    public ResultBean<Object> getProductPre(@PathVariable Integer productPreId) {
        try {
            Object productLibraryPre = productLibraryPreService.selectByIdNew(productPreId);
            ResultBean<Object> res = new ResultBean<>();
            res.setData(productLibraryPre);
            return res;
        } catch (Exception e) {
            return new ResultBean<>(e);
        }
    }

    /**
     * 查找预选库产品
     *
     * @param productStdId
     * @return : Object
     * @author : HuiZhe Xu
     * @date : Created in 2019/7/2 15:57
     */
    @OperationLog("按Stdid查找预选库产品")
    @GetMapping("/productPre/{productStdId}")
    public ResultBean<Object> getProductPreByStdId(@PathVariable Integer productStdId) {
        try {
            ProductLibraryPre productPre= productLibraryPreService.selectByStdId(productStdId);
            Object productLibraryPre = productLibraryPreService.selectByIdNew(productPre.getProductPreId());
            ResultBean<Object> res = new ResultBean<>();
            res.setData(productLibraryPre);
            return res;
        } catch (Exception e) {
            return new ResultBean<>(e);
        }
    }
}
