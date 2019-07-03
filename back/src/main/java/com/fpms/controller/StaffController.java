package com.fpms.controller;

import com.fpms.annotation.OperationLog;
import com.fpms.dto.ConfigDetail;
import com.fpms.dto.ProductDetail;
import com.fpms.dto.ProductsAndConfigs;
import com.fpms.dto.StaffDto;

import com.fpms.entity.Staff;
import com.fpms.entity.StaffRole;
import com.fpms.entity.pojo.ResultBean;
import com.fpms.service.RoleService;
import com.fpms.service.StaffRoleService;
import com.fpms.utils.EdsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.fpms.service.StaffService;

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


    private StaffService staffService;
    private static Pattern NUMBER_PATTERN = Pattern.compile("[0-9]*");

    private StaffRoleService staffRoleService;

    private RoleService roleService;

    private final int MAX_USER_NAME_LENGTH =255;
    private final int MAX_PASSWORD_LENGTH =20;
    @Autowired
    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @Autowired
    public void setStaffRoleService(StaffRoleService staffRoleService) {
        this.staffRoleService = staffRoleService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * 获取详细设置
     *
     * @return : ResultBean<ConfigDetail>
     * @author ：HuiZhe Xu
     * @date ：Created in 2019/6/14 16:29
     * @parameter ：productConId
     */
    @GetMapping("/staff/configurations/{productConId}")
    public ResultBean<ConfigDetail> getConfigDetail(@PathVariable Integer productConId) {
        ResultBean<ConfigDetail> res = new ResultBean<>();
        try {
            if (productConId == null) {
                throw new Exception("没有输入配置id");
            }
            ConfigDetail configDetail = staffService.getConfigById(productConId);
            res.setData(configDetail);
            return res;
        } catch (Exception e) {
            return new ResultBean<>(e);
        }
    }

    /**
     * 获取所有商品和设置
     *
     * @return : ResultBean<ProductsAndConfigs>
     * @author ：HuiZhe Xu
     * @date ：Created in 2019/6/14 16:29
     */
    @GetMapping("/staff/mall")
    public ResultBean<ProductsAndConfigs> getMall() {
        ResultBean<ProductsAndConfigs> res = new ResultBean<>();
        try {
            res.setData(staffService.getAllMall());
        } catch (Exception e) {
            return new ResultBean<>(e);
        }
        return res;
    }

    //    @PostMapping
//    public ResultBean<> addSupplier(){
//
//    }

    /**
     * 获取职工信息
     *
     * @return : ResultBean<Staff>
     * @author ：HuiZhe Xu
     * @date ：Created in 2019/6/14 16:29
     * @parameter : staffId
     */
    @GetMapping("/staff/{staffId}")
    public ResultBean<StaffDto> getSingleStaff(@PathVariable String staffId) {
        ResultBean<StaffDto> res = new ResultBean<>();
        try {
            if (staffId == null || staffId.length() <= 0) {
                throw new Exception("没有传入id");
            }
            if (!NUMBER_PATTERN.matcher(staffId).matches()) {
                throw new Exception("不合法id");
            }
            Staff staff = staffService.getSingleStaffDetail(Integer.parseInt(staffId));
            StaffDto staffDto = makeStaffDto(staff);
            res.setData(staffDto);
        } catch (Exception e) {
            return new ResultBean<>(e);
        }

        return res;
    }

    /**
     * 将staff转换为staffDto
     *
     * @param staff
     * @return : com.fpms.dto.StaffDto
     * @author : HuiZhe Xu
     * @date : Created in 2019/7/1 15:50
     */
    private StaffDto makeStaffDto(Staff staff) {
        StaffDto staffDto = new StaffDto();
        staffDto.setStaffId(staff.getStaffId());
        staffDto.setStaffName(staff.getStaffName());
        staffDto.setStaffGender(staff.getStaffGender());
        staffDto.setStaffPhone(staff.getStaffPhone());
        staffDto.setStaffEmail(staff.getStaffEmail());
        staffDto.setStaffDepartment(staff.getStaffDepartment());
        staffDto.setCreateTime(staff.getCreateTime());
        staffDto.setStaffStatus(staff.getStaffStatus());
        try {
            List<StaffRole> staffRoleList = staffRoleService.selectStaffRoleByStaffId(staff.getStaffId());
            staffDto.setRole(roleService.selectRoleById(staffRoleList.get(0).getRoleId()));
        } catch (Exception e) {
            staffDto.setRole(null);
        }
        return staffDto;
    }

    /**
     * 获取产品信息
     *
     * @return : ResultBean<ProductDetail>
     * @author ：HuiZhe Xu
     * @date ：Created in 2019/6/14 16:29
     * @parameter : productStdId
     */
    @OperationLog("获取产品信息")
    @GetMapping("/staff/productions/{productStdId}")
    public ResultBean<ProductDetail> getSingleProduct(@PathVariable String productStdId) {
        ResultBean<ProductDetail> res = new ResultBean<>();
        try {
            if (productStdId == null || productStdId.length() <= 0) {
                throw new Exception("没有传入id");
            }
            if (!NUMBER_PATTERN.matcher(productStdId).matches()) {
                throw new Exception("不合法id");
            }
            ProductDetail productDetail = staffService.getProductInfo(Integer.parseInt(productStdId));
            res.setData(productDetail);
        } catch (Exception e) {
            return new ResultBean<>(e);
        }
        return res;
    }

    /**
     * 获取员工
     *
     * @return : ResultBean<ArrayList<Staff>>
     * @author ：HuiZhe Xu
     * @date ：Created in 2019/6/14 16:29
     */
    @OperationLog("获取员工")
    @GetMapping("/staffs")
    public ResultBean<ArrayList<StaffDto>> getAllStuff() {
        ResultBean<ArrayList<StaffDto>> res = new ResultBean<>();
        try {
            ArrayList<Staff> staffList = staffService.getStaffs();
            ArrayList<StaffDto> staffDtoList = new ArrayList<>();
            for (Staff staff : staffList) {
                StaffDto staffDto = makeStaffDto(staff);
                staffDtoList.add(staffDto);
            }
            res.setData(staffDtoList);
        } catch (Exception e) {
            return new ResultBean<>(e);
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
     *
     * @return : ResultBean<Boolean>
     * @author ：HuiZhe Xu
     * @date ：Created in 2019/6/14 16:29
     * @parameter : para
     */
    @OperationLog(value = "新增员工")
    @PostMapping("/staff")
    public ResultBean<Boolean> addStaff(@RequestParam String staffName, @RequestParam String staffPwd, @RequestParam String staffDepartment,
                                        @RequestParam String staffGender, @RequestParam String roleName) {
        ResultBean<Boolean> res = new ResultBean<>();
        try {
            if (staffName.length() == 0 || staffPwd.length() == 0 || staffDepartment.length() == 0||roleName.length()==0||staffGender.length()==0) {
                throw new Exception("缺少参数");
            }
            if(staffName.length()> MAX_USER_NAME_LENGTH){
                throw new Exception("用户名过长");
            }
            String password=EdsUtil.encryptBasedDes(staffPwd);
            if(password.length()> MAX_PASSWORD_LENGTH){
                throw new Exception("密码设置过长");
            }
            Staff staff = new Staff();
            staff.setStaffName(staffName);
            staff.setStaffDepartment(staffDepartment);
            staff.setStaffPwd(password);
            staff.setStaffGender(staffGender);
            staffService.addStaff(staff, roleName);
            res.setData(true);
        } catch (Exception e) {
            return new ResultBean<>(e);
        }
        return res;
    }

    /**
     * 删除员工
     *
     * @param staffId
     * @return : com.fpms.entity.pojo.ResultBean<java.lang.Boolean>
     * @author : HuiZhe Xu
     * @date : Created in 2019/6/26 14:36
     */
    @OperationLog("删除员工")
    @DeleteMapping("/staff/{staffId}")
    public ResultBean<Boolean> deleteStaff(@PathVariable Integer staffId) {
        ResultBean<Boolean> res = new ResultBean<>();
        try {
            if (staffId == null) {
                throw new Exception("没有传入id");
            }
            if (staffId < 0) {
                throw new Exception("不合法id");
            }
            staffService.delStaff(staffId);
            res.setData(true);
        } catch (Exception e) {
            return new ResultBean<>(e);
        }
        return res;
    }

    /**
     * 修改员工信息
     *
     * @param staffId
     * @param staff
     * @return : com.fpms.entity.pojo.ResultBean<java.lang.Boolean>
     * @author ：TianHong Liao
     * @date ：Created in 2019/6/26 13:32
     */
    @OperationLog(value = "修改员工信息")
    @PutMapping("/staff/{staffId}")
    public ResultBean<Boolean> modifyStaffInfo(@PathVariable Integer staffId, Staff staff) {
        try {
            Staff staffTemp = staffService.getSingleStaffDetail(staffId);
            if (staffTemp == null) {
                throw new Exception("找不到该员工");
            }
            boolean success = staffService.updateStaff(staff);
            if (!success) {
                throw new Exception("修改失败");
            }
        } catch (Exception e) {
            return new ResultBean<>(e);
        }
        return new ResultBean<>(true);
    }

    /**
     * 修改密码
     *
     * @param staffId
     * @param oldPassword
     * @param password
     * @return : com.fpms.entity.pojo.ResultBean<java.lang.Boolean>
     * @author : HuiZhe Xu
     * @date : Created in 2019/6/27 20:31
     */
    @OperationLog("修改密码")
    @PutMapping("/staff/{staffId}/password")
    public ResultBean<Boolean> modifyPassword(@PathVariable Integer staffId, String oldPassword, String password) {
        try {
            Staff staffTemp = staffService.getSingleStaffDetail(staffId);
            if (staffTemp == null) {
                throw new Exception("找不到该员工");
            }
            if (oldPassword == null) {
                throw new Exception("没有旧密码传入");
            }
            if (oldPassword.equals(EdsUtil.decryptBasedDes(staffTemp.getStaffPwd()))) {
                staffTemp.setStaffPwd(EdsUtil.encryptBasedDes(password));
                boolean success = staffService.updateStaff(staffTemp);
                if (!success) {
                    throw new Exception("修改密码失败");
                }
            } else {
                throw new Exception("旧密码不正确");
            }
        } catch (Exception e) {
            return new ResultBean<>(e);
        }
        return new ResultBean<>(true);
    }
}
