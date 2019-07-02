package com.fpms.controller;

import com.fpms.annotation.OperationLog;
import com.fpms.dto.ProductWithName;
import com.fpms.entity.ProductLibraryStandard;
import com.fpms.entity.pojo.ResultBean;
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
    @Autowired
    private ProductLibraryStandardService productLibraryStandardService;

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
}
