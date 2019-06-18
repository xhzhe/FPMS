package com.fpms.controller;

import com.fpms.entity.Privilege;
import com.fpms.entity.pojo.ResultBean;
import com.fpms.service.PrivilegeService;
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
public class PrivilegeController {
    @Autowired
    private PrivilegeService privilegeService;

    @PostMapping("/privilege")
    /**
     *新建权限
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/18 22:09
     * @param       privilege
     * @return     : com.fpms.entity.pojo.ResultBean<java.lang.Boolean>
     */
    public ResultBean<Boolean> addPrivilege(@RequestBody Privilege privilege){
        try{
            privilegeService.addPrivilege(privilege);
        }
        catch (Exception e){
            return new ResultBean<>(e);
        }
        return new ResultBean<>(true);
    }

    @GetMapping("/privileges")
    /**
     * 获取所有权限
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/18 22:10
     * @param
     * @return     : com.fpms.entity.pojo.ResultBean<java.util.List<com.fpms.entity.Privilege>>
     */
    public ResultBean<List<Privilege> >selectAllPrivileges(){
        return new ResultBean<>(privilegeService.selectAllPrivileges());
    }

    @DeleteMapping("/privilege/{privilege_id}")
    /**
     *通过id删除权限
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/18 22:11
     * @param       privilege_id
     * @return     : com.fpms.entity.pojo.ResultBean<java.lang.Boolean>
     */
    public ResultBean<Boolean> delPrivilegeById(@PathVariable Integer privilege_id)
    {
        try{
            privilegeService.delPrivilegeById(privilege_id);
        }
        catch (Exception e){
            return new ResultBean<>(e);
        }
        return new ResultBean<>(true);
    }

    @PutMapping("/privilege/{privilege_id}")
    /**
     *  通过id更新权限
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/18 22:12
     * @param       privilege_id
     * @param       privilege
     * @return     : com.fpms.entity.pojo.ResultBean<java.lang.Boolean>
     */
    public ResultBean<Boolean> updatePrivilege(@PathVariable Integer privilege_id,@RequestBody Privilege privilege){
        try{
            privilege.setPrivilegeId((Integer)privilege_id);
            privilegeService.updatePrivilege(privilege);
        }
        catch (Exception e){
            return new ResultBean<>(e);
        }
        return new ResultBean<>(true);
    }
}
