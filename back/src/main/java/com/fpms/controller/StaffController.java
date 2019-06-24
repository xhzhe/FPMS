package com.fpms.controller;

import com.fpms.dto.ConfigDetail;
import com.fpms.dto.ProductDetail;
import com.fpms.dto.ProductsAndConfigs;
import com.fpms.entity.ResultBean;

import com.fpms.entity.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.fpms.service.StaffService;

import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author : HuiZhe Xu
 * @date : 2019/6/14 15:02
 * @description: staff class
 * @modified :
 */
@RestController
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    StaffService staffService;
    private Pattern NUMBER_PATTERN = Pattern.compile("[0-9]*");

    /**
     * 获取详细设置
     * @author     ：HuiZhe Xu
     * @date       ：Created in 2019/6/14 16:29
     * @parameter      ：productConId
     * @return     : ResultBean<ConfigDetail>
     */
    @GetMapping("/configurations/{productConId}")
    public ResultBean<ConfigDetail> getConfigDetail(@PathVariable Integer productConId) {
        ResultBean<ConfigDetail> res = new ResultBean<>();
        try {
            if (productConId == null) {
                res.setData(null);
                res.setState(ResultBean.FAIL);
                res.setMsg(ResultBean.FAIL_MSG);
                return res;
            }
            ConfigDetail configDetail = staffService.getConfigByID(productConId);
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
    /**
     * 获取所有商品和设置
     * @author     ：HuiZhe Xu
     * @date       ：Created in 2019/6/14 16:29
     * @return     : ResultBean<ProductsAndConfigs>
     */
    @GetMapping("/mall")
    public ResultBean<ProductsAndConfigs> getMall() {
        ResultBean<ProductsAndConfigs> res = new ResultBean<>();
        try {
            res.setData(staffService.getAllMall());
            res.setState(ResultBean.SUCCESS);
            res.setMsg(ResultBean.SUCC_MSG);
        }catch (Exception e){
            e.printStackTrace();
            res.setData(null);
            res.setState(ResultBean.FAIL);
            res.setMsg(ResultBean.FAIL_MSG);
        }
        return res;
    }

    //    @PostMapping
//    public ResultBean<> addStaff(){
//
//    }
    /**
     * 获取职工信息
     * @author     ：HuiZhe Xu
     * @date       ：Created in 2019/6/14 16:29
     * @parameter      : staffId
     * @return     : ResultBean<Staff>
     */
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
    /**
     * 获取产品信息
     * @author     ：HuiZhe Xu
     * @date       ：Created in 2019/6/14 16:29
     * @parameter      : productStdId
     * @return     : ResultBean<ProductDetail>
     */
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
    /**
     * 获取员工
     * @author     ：HuiZhe Xu
     * @date       ：Created in 2019/6/14 16:29
     * @return     : ResultBean<ArrayList<Staff>>
     */
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

    /**
     * 添加员工
     * @author     ：HuiZhe Xu
     * @date       ：Created in 2019/6/14 16:29
     * @parameter  : para
     * @return     : ResultBean<Boolean>
     */
    @PostMapping
    public ResultBean<Boolean> addStaff(@RequestBody Map para) {
        ResultBean<Boolean> res = new ResultBean<>();
        try {
            String staffName = para.get("staffName").toString();
            String staffPwd = para.get("staffPwd").toString();
            String staffDepartment = para.get("staffDepartment").toString();
            if(staffName.length()==0||staffPwd.length()==0||staffDepartment.length()==0){
                throw new Exception("缺少参数");
            }
            ArrayList<Object> roleList=(ArrayList<Object>)para.get("roleList");

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

//API
//    @PutMapping("/{staffId}/privilege")
//    public ResultBean<Boolean> modifyStaffPrivilege(@PathVariable Integer staffId, @RequestBody Map parm){
//        ResultBean<Boolean> res = new ResultBean<>();
//        try{
//            ArrayList<Object> privilegeList=(ArrayList)parm.get("privilegeList");
//            if(!staffService.ModifyPrivilege(staffId,privilegeList)){
//                throw new Exception("修改失败");
//            }
//            res.setData(true);
//            res.setState(ResultBean.SUCCESS);
//            res.setMsg(ResultBean.SUCC_MSG);
//        }catch (Exception e){
//            e.printStackTrace();
//            res.setData(false);
//            res.setState(ResultBean.FAIL);
//            res.setMsg(e.getMessage()==null?ResultBean.FAIL_MSG:e.getMessage());
//        }
//        return res;
//    }
}
