package com.fpms.controller;


import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fpms.annotation.OperationLog;
import com.fpms.dto.RolePrivilegeDto;
import com.fpms.dto.StaffRoleDto;
import com.fpms.entity.*;
import com.fpms.entity.pojo.ResultBean;
import com.fpms.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author : TianHong Liao
 * @date : 2019/6/18 17:04
 * @description:角色逻辑控制
 * @modified :
 */
@RestController
@CrossOrigin
public class RoleController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private RolePrivilegeService rolePrivilegeService;

    @Autowired
    private PrivilegeService privilegeService;

    @Autowired
    private StaffRoleService staffRoleService;

    @Autowired
    private StaffService staffService;

    /**
     *  新建角色及其拥有的权限
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 10:10
     * @param       rolePrivilegeDto
     * @return     : com.fpms.entity.pojo.ResultBean<java.lang.Boolean>
     */
    @OperationLog(value = "新建角色")
    @PostMapping("/role")
    public ResultBean<Boolean> addRole(@RequestBody RolePrivilegeDto rolePrivilegeDto){
        try{
            Role role = new Role();
            role.setRoleName(rolePrivilegeDto.getRoleName());
            roleService.addRole(role);
            List<Privilege> privilegeList = rolePrivilegeDto.getPrivilegeList();
            for(int i=0;i<privilegeList.size();i++)
            {
                RolePrivilege rolePrivilege = new RolePrivilege();
                rolePrivilege.setRoleId(role.getRoleId());
                Integer privilegeId = privilegeList.get(i).getPrivilegeId();
                Privilege privilege = privilegeService.selectById(privilegeId);
                if(privilege == null){
                    ResultBean<Boolean> resultBean = new ResultBean<>();
                    resultBean.setMsg("权限Id ："+privilegeId+"不存在");
                    resultBean.setState(1);
                    resultBean.setData(false);
                    return resultBean;
                }
                rolePrivilege.setPrivilegeId(privilege.getPrivilegeId());
                rolePrivilegeService.addRolePrivilege(rolePrivilege);
            }
        }
        catch (Exception e){
            return new ResultBean<>(e);
        }
        return new ResultBean<>(true);
    }

    /**
     *  返回所有的角色及其拥有的权限
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 10:14
     * @param
     * @return     : com.fpms.entity.pojo.ResultBean<java.util.List<com.fpms.dto.RolePrivilegeDto>>
     */
    @GetMapping("/roles")
    public ResultBean<List<RolePrivilegeDto> > selectAllRoles(){
        List<RolePrivilegeDto> rolePrivilegeDtoList = new ArrayList<>();
        try{
            List<Role> roleList = roleService.selectAllRoles();
            for(int i=0;i<roleList.size();i++)
            {
                Role role = roleList.get(i);
                RolePrivilegeDto rolePrivilegeDto = new RolePrivilegeDto();
                rolePrivilegeDto.setRoleId(role.getRoleId());
                rolePrivilegeDto.setRoleName(role.getRoleName());
                rolePrivilegeDto.setCreateTime(role.getCreateTime());

                List<RolePrivilege> rolePrivilegeList = rolePrivilegeService.selectRolePrivilegeByRoleId(role.getRoleId());
                List<Privilege> privilegeList = new ArrayList<>();
                for(int j=0;j<rolePrivilegeList.size();j++)
                {
                    RolePrivilege rolePrivilege = rolePrivilegeList.get(j);
                    Privilege privilege = privilegeService.selectById(rolePrivilege.getPrivilegeId());
                    privilegeList.add(privilege);
                }
                rolePrivilegeDto.setPrivilegeList(privilegeList);
                rolePrivilegeDtoList.add(rolePrivilegeDto);
            }
        }
        catch (Exception e){
            return new ResultBean<>(e);
        }
        return new ResultBean<>(rolePrivilegeDtoList);
    }

    /**
     *  通过roleId删除角色并在角色权限关联表中删除 同时删除职工角色关联表
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 10:15
     * @param       roleId
     * @return     : com.fpms.entity.pojo.ResultBean<java.lang.Boolean>
     */
    @OperationLog(value = "删除角色")
    @DeleteMapping("/role/{roleId}")
    public ResultBean<Boolean> delRoleById(@PathVariable Integer roleId){
        try{
            //在角色表中删除
            roleService.delRoleById(roleId);
            //在角色权限关联表中删除
            rolePrivilegeService.delRolePrivilegeByRoleId(roleId);
            //在职工角色关联表中删除
            staffRoleService.delStaffRoleByRoleId(roleId);
        }
        catch (Exception e){
            return new ResultBean<>(e);
        }
        return new ResultBean<>(true);
    }

    /**
     *  更新角色及其拥有的权限
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 10:16
     * @param       rolePrivilegeDto
     * @param       roleId
     * @return     : com.fpms.entity.pojo.ResultBean<java.lang.Boolean>
     */
    @OperationLog(value = "更新角色")
    @PutMapping("/role/{roleId}")
    public ResultBean<Boolean> updateRole(@RequestBody RolePrivilegeDto rolePrivilegeDto,@PathVariable Integer roleId){
        try{
            //更新角色表
            Role role = new Role();
            role.setRoleId(roleId);
            role.setRoleName(rolePrivilegeDto.getRoleName());
            roleService.updateRole(role);
            //更新角色权限关联表
            //先删除全部
            rolePrivilegeService.delRolePrivilegeByRoleId(roleId);
            //再添加
            List<Privilege> privilegeList = rolePrivilegeDto.getPrivilegeList();
            for(int i=0;i<privilegeList.size();i++)
            {
                RolePrivilege rolePrivilege = new RolePrivilege();
                rolePrivilege.setRoleId(roleId);
                Integer privilegeId = privilegeList.get(i).getPrivilegeId();
                Privilege privilege = privilegeService.selectById(privilegeId);
                if(privilege == null){
                    ResultBean<Boolean> resultBean = new ResultBean<>();
                    resultBean.setMsg("权限Id ：" + privilegeId + "不存在");
                    resultBean.setState(1);
                    resultBean.setData(false);
                    return resultBean;
                }
                rolePrivilege.setPrivilegeId(privilege.getPrivilegeId());
                rolePrivilegeService.addRolePrivilege(rolePrivilege);
            }

        }
        catch (Exception e){
            return new ResultBean<>(e);
        }
        return new ResultBean<>(true);
    }

    /**
     *  添加职工和角色的关联
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 14:03
     * @param       staffId
     * @param       roleList
     * @return     : com.fpms.entity.pojo.ResultBean<java.lang.Boolean>
     */
    @OperationLog(value = "添加职工的角色")
    @PostMapping("/staff/{staffId}/role")
    public ResultBean<Boolean> addStaffRole(@PathVariable Integer staffId,@RequestBody Role[] roleList){
        try{
            for(int i=0;i<roleList.length;i++){
                StaffRole staffRole = new StaffRole();
                staffRole.setStaffId(staffId);
                staffRole.setRoleId(roleList[i].getRoleId());
                staffRoleService.addStaffRole(staffRole);
            }
        }
        catch (Exception e){
            return new ResultBean<>(e);
        }
        return new ResultBean<>(true);
    }

    /**
     *  修改职工和角色的关联信息
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 14:04
     * @param       staffId
     * @param       roleList
     * @return     : com.fpms.entity.pojo.ResultBean<java.lang.Boolean>
     */
    @OperationLog(value = "修改职工的角色")
    @PutMapping("/staff/{staffId}/role")
    public ResultBean<Boolean> updateStaffRole(@PathVariable Integer staffId,@RequestBody Role[] roleList){
        try{
            //先删除全部
            staffRoleService.delStaffRoleByStaffId(staffId);
            //再添加
            for(int i=0;i<roleList.length;i++){
                StaffRole staffRole = new StaffRole();
                staffRole.setStaffId(staffId);
                staffRole.setRoleId(roleList[i].getRoleId());
                staffRoleService.addStaffRole(staffRole);
            }
        }
        catch (Exception e){
            return new ResultBean<>(e);
        }
        return new ResultBean<>(true);
    }

    /**
     *  返回所有职工的所有角色列表
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 14:04
     * @param
     * @return     : com.fpms.entity.pojo.ResultBean<java.util.List<com.fpms.dto.StaffRoleDto>>
     */
    @GetMapping("/staff/roles")
    public ResultBean<List<StaffRoleDto>> selectAllStaffRole(){
        List<StaffRoleDto> staffRoleDtoList = new ArrayList<>();
        try{
            List<Staff> staffList = staffService.getStaffs();
            for(int i=0;i<staffList.size();i++){
                StaffRoleDto staffRoleDto = new StaffRoleDto();
                //加载staff属性
                staffRoleDto.setStaffId(staffList.get(i).getStaffId());
                staffRoleDto.setStaffName(staffList.get(i).getStaffName());
                //加载role属性
                List<StaffRole> staffRoleList = staffRoleService.selectStaffRoleByStaffId(staffList.get(i).getStaffId());
                List<Role> roleList = new ArrayList<>();
                for(int j=0;j<staffRoleList.size();j++){
                    Role role = roleService.selectRoleById(staffRoleList.get(j).getRoleId());
                    roleList.add(role);
                }
                staffRoleDto.setRoleList(roleList);
                staffRoleDtoList.add(staffRoleDto);
            }

        }
        catch (Exception e){
            return new ResultBean<>(e);
        }
        return new ResultBean<>(staffRoleDtoList);
    }

    /**
     *  返回指定职工的角色列表
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 14:05
     * @param       staffId
     * @return     : com.fpms.entity.pojo.ResultBean<com.fpms.dto.StaffRoleDto>
     */
    @GetMapping("/staff/{staffId}/role")
    public ResultBean<StaffRoleDto> selectAllStaffRole(@PathVariable Integer staffId){
        StaffRoleDto staffRoleDto = new StaffRoleDto();
        try{
            //加载staff属性
            Staff staff = staffService.getSingleStaffDetail(staffId);
            staffRoleDto.setStaffId(staffId);
            staffRoleDto.setStaffName(staff.getStaffName());
            //加载role属性
            List<StaffRole> staffRoleList = staffRoleService.selectStaffRoleByStaffId(staffId);
            List<Role> roleList = new ArrayList<>();
            for(int i=0;i<staffRoleList.size();i++){
                Role role = roleService.selectRoleById(staffRoleList.get(i).getRoleId());
                roleList.add(role);
            }
            staffRoleDto.setRoleList(roleList);
        }
        catch (Exception e){
            return new ResultBean<>(e);
        }
        return new ResultBean<>(staffRoleDto);
    }
}
