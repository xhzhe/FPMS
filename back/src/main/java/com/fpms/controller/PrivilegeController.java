package com.fpms.controller;

import com.fpms.entity.Privilege;
import com.fpms.entity.RolePrivilege;
import com.fpms.entity.pojo.ResultBean;
import com.fpms.service.PrivilegeService;
import com.fpms.service.RolePrivilegeService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : TianHong Liao
 * @date : 2019/6/18 16:58
 * @description:权限逻辑控制
 * @modified :
 */
@RestController
@CrossOrigin
public class PrivilegeController {
    @Autowired
    private PrivilegeService privilegeService;

    @Autowired
    private RolePrivilegeService rolePrivilegeService;

    /**
     *新建权限
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/18 22:09
     * @param       privilege
     * @return     : com.fpms.entity.pojo.ResultBean<java.lang.Boolean>
     */
    @PostMapping("/privilege")
    public ResultBean<Boolean> addPrivilege(@RequestBody Privilege privilege){
        try{
            privilegeService.addPrivilege(privilege);
        }
        catch (Exception e){
            return new ResultBean<>(e);
        }
        return new ResultBean<>(true);
    }

    /**
     * 获取所有权限
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/18 22:10
     * @param
     * @return     : com.fpms.entity.pojo.ResultBean<java.util.List<com.fpms.entity.Privilege>>
     */
    @GetMapping("/privileges")
    public ResultBean<List<Privilege> >selectAllPrivileges(){
        return new ResultBean<>(privilegeService.selectAllPrivileges());
    }

    /**
     *  通过privilegeId删除权限
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 14:56
     * @param       privilegeId
     * @return     : com.fpms.entity.pojo.ResultBean<java.lang.Boolean>
     */
    @DeleteMapping("/privilege/{privilegeId}")
    public ResultBean<Boolean> delPrivilegeById(@PathVariable Integer privilegeId)
    {
        try{
            //删除权限表里的权限
            privilegeService.delPrivilegeById(privilegeId);
            //删除角色权限关联表里的权限
            rolePrivilegeService.delRolePrivilegeByPrivilegeId(privilegeId);
        }
        catch (Exception e){
            return new ResultBean<>(e);
        }
        return new ResultBean<>(true);
    }


    /**
     *  通过privilegeId更新权限
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 14:57
     * @param       privilegeId
     * @param       privilege
     * @return     : com.fpms.entity.pojo.ResultBean<java.lang.Boolean>
     */
    @PutMapping("/privilege/{privilegeId}")
    public ResultBean<Boolean> updatePrivilege(@PathVariable Integer privilegeId,@RequestBody Privilege privilege){
        try{
            privilege.setPrivilegeId((Integer)privilegeId);
            privilegeService.updatePrivilege(privilege);
        }
        catch (Exception e){
            return new ResultBean<>(e);
        }
        return new ResultBean<>(true);
    }
}
