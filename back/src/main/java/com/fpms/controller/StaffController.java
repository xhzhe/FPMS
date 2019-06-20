package com.fpms.controller;

import com.fpms.DTO.ConfigDetail;
import com.fpms.DTO.ProductDetail;
import com.fpms.DTO.ProductsAndConfigs;
import com.fpms.entity.ResultBean;

import com.fpms.entity.Staff;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.fpms.service.StaffService;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author : YongBiao Liao
 * @date : 2019/6/14 15:02
 * @description:
 * @modified :
 */
@RestController
@RequestMapping("/staff")
public class StaffController {

    @RequestMapping("/test")
    public String hello() {
        return "hello";
    }

    @Autowired
    StaffService staffService;
    private Pattern NUMBER_PATTERN = Pattern.compile("[0-9]*");

    @GetMapping("/configurations/{productConID}")
    public ResultBean<ConfigDetail> getConfigDetail(@PathVariable Integer productConID) {
        ResultBean<ConfigDetail> res = new ResultBean<>();
        try {
            if (productConID == null) {
                res.setData(null);
                res.setState(ResultBean.FAIL);
                res.setMsg(ResultBean.FAIL_MSG);
                return res;
            }
            ConfigDetail configDetail = staffService.getConfigByID(productConID);
            if (configDetail == null) {
                res.setData(null);
                res.setState(ResultBean.FAIL);
                res.setMsg(ResultBean.FAIL_MSG);
                return res;
            }
            res.setData(configDetail);
            res.setState(ResultBean.SUCCESS);
            res.setMsg(ResultBean.SUCC_MSG);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            res.setState(ResultBean.FAIL);
            res.setMsg(ResultBean.FAIL_MSG);
        }
        return res;
    }

    @GetMapping("/mall")
    public ResultBean<ProductsAndConfigs> getMall() {
        ResultBean<ProductsAndConfigs> res = new ResultBean<>();
        res.setData(staffService.getAllMall());
        res.setState(ResultBean.SUCCESS);
        res.setMsg(ResultBean.SUCC_MSG);

        return res;
    }

    //    @PostMapping
//    public ResultBean<> addStaff(){
//
//    }
    @GetMapping("/{staffId}")
    public ResultBean<Staff> getSingleStaff(@PathVariable String staffId) {
        ResultBean<Staff> res = new ResultBean<>();
        if (staffId == null || staffId.length() <= 0) {
            res.setData(null);
            res.setState(ResultBean.FAIL);
            res.setMsg(ResultBean.FAIL_MSG);
            return res;
        }
        if (NUMBER_PATTERN.matcher(staffId).matches()) {
            try {
                Staff staff = staffService.getSingleStaffDetail(Integer.parseInt(staffId));
                res.setData(staff);
                if (staff == null) {
                    res.setState(ResultBean.FAIL);
                    res.setMsg("没有该员工");
                } else {
                    res.setState(ResultBean.SUCCESS);
                    res.setMsg(ResultBean.SUCC_MSG);
                }
            } catch (Exception e) {
                res.setData(null);
                res.setState(ResultBean.FAIL);
                res.setMsg(ResultBean.FAIL_MSG);
            }
        }
        return res;
    }

    @GetMapping("productions/{productStdId}")
    public ResultBean<ProductDetail> getSingleProduct(@PathVariable String productStdId) {
        ResultBean<ProductDetail> res = new ResultBean<>();
        if (productStdId == null || productStdId.length() <= 0) {
            res.setData(null);
            res.setState(ResultBean.FAIL);
            res.setMsg(ResultBean.FAIL_MSG);
            return res;
        }
        if (NUMBER_PATTERN.matcher(productStdId).matches()) {
            try {
                ProductDetail productDetail = staffService.getProductInfo(Integer.parseInt(productStdId));
                res.setData(productDetail);
                if (productDetail == null) {
                    res.setState(ResultBean.FAIL);
                    res.setMsg("查询不到该产品");
                } else {
                    res.setState(ResultBean.SUCCESS);
                    res.setMsg(ResultBean.SUCC_MSG);
                }
            } catch (Exception e) {
                res.setData(null);
                res.setState(ResultBean.FAIL);
                res.setMsg(ResultBean.FAIL_MSG);
            }
        }
        return res;
    }

    @GetMapping
    public ResultBean<ArrayList<Staff>> getAllStuff() {
        ResultBean<ArrayList<Staff>> res = new ResultBean<>();
        try {
            ArrayList<Staff> staff = staffService.getStaffs();
            res.setData(staff);
            if (staff == null) {
                res.setState(ResultBean.FAIL);
                res.setMsg("暂无员工");
            } else {
                res.setState(ResultBean.SUCCESS);
                res.setMsg(ResultBean.SUCC_MSG);
            }
        } catch (Exception e) {
            res.setData(null);
            res.setState(ResultBean.FAIL);
            res.setMsg(ResultBean.FAIL_MSG);
        }

        return res;
    }

//    @PostMapping
//    public ResultBean<Boolean> addStaff(@RequestBody  Staff staff){
//        ResultBean<Boolean>res = new ResultBean<>();
//
//    }


    @PostMapping
    public ResultBean<Boolean> addStaff(@RequestBody Map parm) {
        ResultBean<Boolean> res = new ResultBean<>();
        try {
            String staffName = parm.get("staffName").toString();
            String staffPwd = parm.get("staffPwd").toString();
            String staffDepartment = parm.get("staffDepartment").toString();
            if(staffName.length()==0||staffPwd.length()==0||staffDepartment.length()==0){
                throw new Exception("缺少参数");
            }
            ArrayList<Object> roleList=(ArrayList<Object>)parm.get("roleList");

            if(!staffService.addStaff(staffName,staffPwd,staffDepartment,roleList)){
                throw new Exception("插入失败");
            }
            res.setData(true);
            res.setState(ResultBean.SUCCESS);
            res.setMsg(ResultBean.SUCC_MSG);
        } catch (Exception e) {
            e.printStackTrace();
            res.setData(false);
            res.setState(ResultBean.FAIL);
            res.setMsg(e.getMessage()==null?ResultBean.FAIL_MSG:e.getMessage());
        }
        return res;
    }

    @PutMapping("/{staffId}/privilege")
    public ResultBean<Boolean> modifyStaffPrivilege(@PathVariable Integer staffId, @RequestBody Map parm){
        ResultBean<Boolean> res = new ResultBean<>();
        try{
            ArrayList<Object> privilegeList=(ArrayList)parm.get("privilegeList");
            if(!staffService.ModifyPrivilege(staffId,privilegeList)){
                throw new Exception("修改失败");
            }
            res.setData(true);
            res.setState(ResultBean.SUCCESS);
            res.setMsg(ResultBean.SUCC_MSG);
        }catch (Exception e){
            e.printStackTrace();
            res.setData(false);
            res.setState(ResultBean.FAIL);
            res.setMsg(e.getMessage()==null?ResultBean.FAIL_MSG:e.getMessage());
        }
        return res;
    }
}
