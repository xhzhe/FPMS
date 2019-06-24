package com.fpms.controller;

import com.fpms.entity.ProductLibraryPre;
import com.fpms.entity.ResultBean;
import com.fpms.service.ProductLibraryPreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * @author : YongBiao Liao
 * @date : 2019/6/14 14:59
 * @description:
 * @modified :
 */
@RestController
@CrossOrigin
public class ProductLibraryPreController {
    @Autowired
    private ProductLibraryPreService productLibraryPreService;
    public void setFail(@NotNull ResultBean res){
        res.setData(null);
        res.setState(ResultBean.FAIL);
        res.setMsg(ResultBean.FAIL_MSG);
    }
    public <T> void setSuccess(String Message,T data,@NotNull ResultBean res){
        res.setData(data);
        res.setState(ResultBean.SUCCESS);
        res.setMsg(Message);
    }
    @PutMapping("/product")
    public ResultBean<Boolean> modifyProduct(@RequestBody ProductLibraryPre product){
        ResultBean<Boolean> res = new ResultBean<>();
        try{
            if(product.getProductPreId()==null){
                setFail(res);
                throw new Exception("缺少ID");
            }
            boolean success=productLibraryPreService.modifyProduct(product);
            if(success){
                setSuccess(ResultBean.SUCC_MSG,success,res);
            }else{
                setFail(res);
            }
        }catch (Exception e){
            setFail(res);
        }
        return res;
    }

    @PostMapping("/product_pre")
    public ResultBean<Boolean> addProduct(@RequestBody ProductLibraryPre product){
        ResultBean<Boolean> res = new ResultBean<>();
        try{
            boolean success=productLibraryPreService.addProduct(product);
            if(success){
                setSuccess(ResultBean.SUCC_MSG,success,res);
            }else{
                setFail(res);
            }
        }catch (Exception e){
            setFail(res);
        }
        return res;
    }
}
