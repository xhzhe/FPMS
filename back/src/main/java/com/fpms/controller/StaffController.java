package com.fpms.controller;

import com.fpms.annotation.OperationLog;
import com.fpms.dto.ConfigDetail;
import com.fpms.dto.ProductDetail;
import com.fpms.dto.ProductsAndConfigs;
import com.fpms.dto.StaffDto;

import com.fpms.entity.Role;
import com.fpms.entity.Staff;
import com.fpms.entity.StaffRole;
import com.fpms.entity.pojo.ResultBean;
import com.fpms.service.RoleService;
import com.fpms.service.StaffRoleService;
import com.fpms.utils.EdsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.fpms.service.StaffService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author : HuiZhe Xu
 * @date : 2019/6/14 15:02
 * @description: 职工逻辑控制器
 * @modified :
 */
@RestController
@CrossOrigin
public class StaffController {

    @Autowired
    StaffService staffService;
    private Pattern NUMBER_PATTERN = Pattern.compile("[0-9]*");

    @Autowired
    StaffRoleService staffRoleService;

    @Autowired
    RoleService roleService;
    /**
     * 获取详细设置
     * @author     ：HuiZhe Xu
     * @date       ：Created in 2019/6/14 16:29
     * @parameter      ：productConId
     * @return     : ResultBean<ConfigDetail>
     */
    @GetMapping("/staff/configurations/{productConId}")
    public ResultBean<ConfigDetail> getConfigDetail(@PathVariable Integer productConId) {
        ResultBean<ConfigDetail> res = new ResultBean<>();
        try {
            if (productConId == null) {
                res.setData(null);
                res.setState(ResultBean.FAIL);
                res.setMsg(ResultBean.FAIL_MSG);
                return res;
            }
            ConfigDetail configDetail = staffService.getConfigById(productConId);
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
    @GetMapping("/staff/mall")
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
//    public ResultBean<> addSupplier(){
//
//    }
    /**
     * 获取职工信息
     * @author     ：HuiZhe Xu
     * @date       ：Created in 2019/6/14 16:29
     * @parameter      : staffId
     * @return     : ResultBean<Staff>
     */
    @GetMapping("/staff/{staffId}")
    public ResultBean<StaffDto> getSingleStaff(@PathVariable String staffId) {
        ResultBean<StaffDto> res = new ResultBean<>();
        if (staffId == null || staffId.length() <= 0) {
            res.setData(null);
            res.setState(ResultBean.FAIL);
            res.setMsg(ResultBean.FAIL_MSG);
            return res;
        }
        if (NUMBER_PATTERN.matcher(staffId).matches()) {
            try {
                Staff staff = staffService.getSingleStaffDetail(Integer.parseInt(staffId));
                StaffDto staffDto = new StaffDto();
                staffDto.setStaffId(staff.getStaffId());
                staffDto.setStaffName(staff.getStaffName());
                staffDto.setStaffGender(staff.getStaffGender());
                staffDto.setStaffPhone(staff.getStaffPhone());
                staffDto.setStaffEmail(staff.getStaffEmail());
                staffDto.setStaffDepartment(staff.getStaffDepartment());
                staffDto.setCreateTime(staff.getCreateTime());
                staffDto.setStaffStatus(staff.getStaffStatus());
                List<StaffRole> staffRoleList = staffRoleService.selectStaffRoleByStaffId(staff.getStaffId());
                for(int j=0;j<staffRoleList.size();j++){
                    Role role = roleService.selectRoleById(staffRoleList.get(j).getRoleId());
                    staffDto.setRole(role);
                    break;
                }
                res.setData(staffDto);
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
    @GetMapping("/staff/productions/{productStdId}")
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
    @GetMapping("/staffs")
    public ResultBean<ArrayList<StaffDto>> getAllStuff() {
        ResultBean<ArrayList<StaffDto>> res = new ResultBean<>();
        try {
            ArrayList<Staff> staffList = staffService.getStaffs();
            ArrayList<StaffDto> staffDtoList = new ArrayList<>();
            for(int i=0;i<staffList.size();i++){
                StaffDto staffDto = new StaffDto();
                Staff staff = staffList.get(i);
                staffDto.setStaffId(staff.getStaffId());
                staffDto.setStaffName(staff.getStaffName());
                staffDto.setStaffGender(staff.getStaffGender());
                staffDto.setStaffPhone(staff.getStaffPhone());
                staffDto.setStaffEmail(staff.getStaffEmail());
                staffDto.setStaffDepartment(staff.getStaffDepartment());
                staffDto.setCreateTime(staff.getCreateTime());
                staffDto.setStaffStatus(staff.getStaffStatus());
                List<StaffRole> staffRoleList = staffRoleService.selectStaffRoleByStaffId(staff.getStaffId());
                for(int j=0;j<staffRoleList.size();j++){
                    Role role = roleService.selectRoleById(staffRoleList.get(j).getRoleId());
                    staffDto.setRole(role);
                    break;
                }
                staffDtoList.add(staffDto);
            }
            res.setData(staffDtoList);
            if (staffList == null) {
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
//    public ResultBean<Boolean> addSupplier(@RequestBody  Staff staff){
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
    @OperationLog(value = "新增员工")
    @PostMapping("/staff")
    public ResultBean<Boolean> addStaff(@RequestParam String staffName,@RequestParam String staffPwd,@RequestParam String staffDepartment,
                                        @RequestParam String staffGender,@RequestParam String roleName) {
        ResultBean<Boolean> res = new ResultBean<>();

        try {
            Staff staff = new Staff();
            staff.setStaffName(staffName);
            staff.setStaffDepartment(staffDepartment);
            staff.setStaffPwd(EdsUtil.encryptBasedDes(staffPwd));
            staff.setStaffGender(staffGender);
            if(staffName.length()==0||staffPwd.length()==0||staffDepartment.length()==0){
                throw new Exception("缺少参数");
            }
            if(!staffService.addStaff(staff,roleName)){
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

    /**
     *  删除员工
     * @author     : HuiZhe Xu
     * @date       : Created in 2019/6/26 14:36
     * @param       staffId
     * @return     : com.fpms.entity.pojo.ResultBean<java.lang.Boolean>
     */
    @OperationLog("删除员工")
    @DeleteMapping("/staff/{staffId}")
    public ResultBean<Boolean> deleteStaff(@PathVariable Integer staffId){
        ResultBean<Boolean> res = new ResultBean<>();
        if(staffId==null){
            res.setState(ResultBean.FAIL);
            res.setMsg(ResultBean.FAIL_MSG);
            res.setData(false);
            return res;
        }
        if(staffId<0){
            res.setData(false);
            res.setMsg(ResultBean.FAIL_MSG);
            res.setState(ResultBean.FAIL);
            return res;
        }
        try{
            boolean success=staffService.delStaff(staffId);
            if(success){
                res.setData(true);
                res.setMsg(ResultBean.SUCC_MSG);
                res.setState(ResultBean.SUCCESS);
            }else{
                throw new Exception("删除失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            res.setData(false);
            res.setMsg(ResultBean.FAIL_MSG);
            res.setState(ResultBean.FAIL);
            return res;
        }
        return res;
    }
    /**
     *  修改员工信息
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/26 13:32
     * @param       staffId
     * @param       staff
     * @return     : com.fpms.entity.pojo.ResultBean<java.lang.Boolean>
     */
    @OperationLog(value = "修改员工信息")
    @PutMapping("/staff/{staffId}")
    public ResultBean<Boolean> modifyStaffInfo(@PathVariable Integer staffId, Staff staff){
        try{

            Staff staffTemp = staffService.getSingleStaffDetail(staffId);

            if(staffTemp==null){
                throw new Exception("找不到该员工");
            }
            boolean success=staffService.updateStaff(staff);
            if(!success){
                throw new Exception("修改失败");
            }
        }
        catch (Exception e){
            return new ResultBean<>(e);
        }
        return new ResultBean<>(true);
    }
    /**
     *  修改密码
     * @author     : HuiZhe Xu
     * @date       : Created in 2019/6/27 20:31
     * @param       staffId
     * @param       oldPassword
     * @param       password
     * @return     : com.fpms.entity.pojo.ResultBean<java.lang.Boolean>
     */
    @OperationLog("修改密码")
    @PutMapping("/staff/{staffId}/password")
    public ResultBean<Boolean> modifyPassword(@PathVariable Integer staffId,String oldPassword,String password){
        try{
            Staff staffTemp = staffService.getSingleStaffDetail(staffId);

            if(staffTemp==null){
                throw new Exception("找不到该员工");
            }

            if(oldPassword==null){
                throw new Exception("没有旧密码传入");
            }
            if(oldPassword.equals(EdsUtil.decryptBasedDes(staffTemp.getStaffPwd()))){
                staffTemp.setStaffPwd(EdsUtil.encryptBasedDes(password));
                boolean success = staffService.updateStaff(staffTemp);
                if(!success){
                    throw new Exception("修改密码失败");
                }
            }else{
                throw new Exception("旧密码不正确");
            }
        }catch (Exception e){
            return new ResultBean<>(e);
        }
        return new ResultBean<>(true);
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
