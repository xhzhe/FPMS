package com.fpms.controller;

import com.fpms.annotation.OperationLog;
import com.fpms.dto.Product;
import com.fpms.dto.ProductWithName;
import com.fpms.entity.ProductLibraryPre;
import com.fpms.entity.ProductLibraryStandard;
import com.fpms.entity.pojo.ResultBean;
import com.fpms.service.ProductLibraryPreService;
import com.fpms.service.ProductLibraryStandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : HuiZhe Xu
 * @date : 2019/6/14 15:01
 * @description:
 * @modified :
 */
@RestController
@CrossOrigin
public class ProductLibraryStandardController {
    /**
     * 标准库服务
     *
     * @author : HuiZhe Xu
     * @date : Created in 2019/7/2 10:03
     */
    private ProductLibraryStandardService productLibraryStandardService;

    private ProductLibraryPreService productLibraryPreService;
    @Autowired
    public ProductLibraryStandardController(ProductLibraryStandardService productLibraryStandardService) {
        this.productLibraryStandardService = productLibraryStandardService;
    }
    @Autowired
    public void setProductLibraryPreService(ProductLibraryPreService productLibraryPreService) {
        this.productLibraryPreService = productLibraryPreService;
    }
    /**
     * 下架产品
     *
     * @param productStdId
     * @return : com.fpms.entity.ResultBean<java.lang.Boolean>
     * @author : HuiZhe Xu
     * @date : Created in 2019/6/25 10:58
     */
    @OperationLog(value = "下架产品")
    @DeleteMapping("/productStd/{productStdId}")
    public ResultBean<Boolean> productObtained(@PathVariable Integer productStdId) {
        ResultBean<Boolean> res = new ResultBean<>();
        try {
            if (productStdId == null) {
                throw new Exception("没有传入id");
            }
            if (productStdId < 0) {
                throw new Exception("不合法id");
            }
            productLibraryStandardService.obtainedProducts(productStdId);
            res.setData(true);
        } catch (Exception e) {
            return new ResultBean<>(e);
        }
        return res;

    }

    /**
     * 上架产品
     *
     * @param productStdId
     * @return : com.fpms.entity.ResultBean<java.lang.Boolean>
     * @author : HuiZhe Xu
     * @date : Created in 2019/6/26 10:32
     */
    @OperationLog(value = "上架产品")
    @PostMapping("/productStd/{productStdId}")
    public ResultBean<Boolean> postProduct(@PathVariable Integer productStdId) {
        ResultBean<Boolean> res = new ResultBean<>();
        try {
            if (productStdId == null) {
                throw new Exception("没有传入id");
            }
            if (productStdId < 0) {
                throw new Exception("不合法id");
            }
            productLibraryStandardService.uploadProduct(productStdId);
            res.setData(true);
        } catch (Exception e) {
            return new ResultBean<>(e);
        }
        return res;
    }

    /**
     * 获取所有标准库产品
     *
     * @param
     * @return : ArrayList<ProductWithName>
     * @author ：TianHong Liao
     * @date ：Created in 2019/6/28 16:52
     */
    @GetMapping("/productStds")
    public ResultBean<List<ProductWithName>> getAllProductStd() {
        ArrayList<ProductWithName> productWithNames;
        try {
            productWithNames = productLibraryStandardService.getAll();
        } catch (Exception e) {
            return new ResultBean<>(e);
        }
        return new ResultBean<>(productWithNames);
    }

    /**
     * 获取单个产品
     *
     * @param productStdId
     * @return : com.fpms.entity.pojo.ResultBean<com.fpms.dto.ProductWithName>
     * @author : HuiZhe Xu
     * @date : Created in 2019/7/2 16:14
     */
    @OperationLog("获取单个产品")
    @GetMapping("/productStd/{productStdId}")
    public ResultBean<ProductWithName> getProductStd(@PathVariable Integer productStdId) {
        try {
            if (productStdId == null) {
                throw new Exception("没有传入id");
            }
            if (productStdId < 0) {
                throw new Exception("id不合法");
            }
            ProductWithName productWithNames = productLibraryStandardService.getProductStd(productStdId);
            return new ResultBean<>(productWithNames);
        } catch (Exception e) {
            return new ResultBean<>(e);
        }
    }

    /**
     * 修改标准库产品
     *
     * @param productLibraryStandard
     * @return : com.fpms.entity.pojo.ResultBean<java.lang.Boolean>
     * @author : HuiZhe Xu
     * @date : Created in 2019/6/29 15:06
     */
    @OperationLog("修改标准库产品")
    @PutMapping("/productStd")
    public ResultBean<Boolean> modifyProductStd(@RequestBody ProductLibraryStandard productLibraryStandard) {
        try {
            productLibraryStandardService.updateProductStandard(productLibraryStandard);
            return new ResultBean<>(true);
        } catch (Exception e) {
            return new ResultBean<>(e);
        }
    }

    /**
     *  根据预选库插入标准库产品
     * @author     : HuiZhe Xu
     * @date       : Created in 2019/7/3 16:53
     * @param       productPreId
     * @return     : com.fpms.entity.pojo.ResultBean<java.lang.Boolean>
     */
    @OperationLog("根据预选库插入标准库产品")
    @PostMapping("/productStd/productPre/{productPreId}")
    public ResultBean<Boolean> addProductStd(@PathVariable Integer productPreId) {
        try {
            if(productPreId==null){
                throw new Exception("没有传入id");
            }
            if(productPreId<0){
                throw new Exception("不合法id");
            }
            ProductLibraryPre productLibraryPre = productLibraryPreService.selectById(productPreId);
            if(productLibraryPre == null){
                return new ResultBean<>("预选库Id不存在");
            }
            ProductLibraryStandard productLibraryStandard=new ProductLibraryStandard();
            productLibraryStandard.setProductPreId(productPreId);
//            Double stock = productLibraryPre.getProductPrice().doubleValue() * productLibraryPre.getPurchaseLimit().doubleValue();
//            productLibraryStandard.setStock(stock.intValue());
            productLibraryStandardService.insertProductStd(productLibraryStandard);
            return new ResultBean<>(true);
        } catch (Exception e) {
            return new ResultBean<>(e);
        }
    }

}
