package com.fpms.controller;

import com.fpms.DTO.ConfigDetail;
import com.fpms.entity.ResultBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fpms.service.StaffService;
/**
 * @author : YongBiao Liao
 * @date : 2019/6/14 15:02
 * @description:
 * @modified :
 */
@RestController
public class StaffController {

    @RequestMapping("/test")
    public String hello(){
        return "hello";
    }
    @Autowired
    StaffService staffService;
    @GetMapping("/staff/configurations/{productConID}")
    public ResultBean<ConfigDetail> getConfigDetail(@PathVariable Integer productConID){
        ResultBean<ConfigDetail>res=new ResultBean<>();
        try {
            if(productConID==null){
                System.out.println(productConID);
                res.setData(null);
                res.setState(ResultBean.FAIL);
                res.setMsg(ResultBean.FAIL_MSG);
                return res;
            }
            res.setData(staffService.getConfigByID(productConID));
            res.setState(ResultBean.SUCCESS);
            res.setMsg(ResultBean.SUCC_MSG);
            return res;
        }
        catch (Exception e){
            e.printStackTrace();
            res.setState(ResultBean.FAIL);
            res.setMsg(ResultBean.FAIL_MSG);
        }
        return res;
    }
}
