package com.fpms.service;

import com.fpms.entity.RolePrivilege;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author : TianHong Liao
 * @date : 2019/6/18 22:45
 * @description:
 * @modified :
 */
public interface RolePrivilegeService {

    /**
     *  添加角色权限之间的关联
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 14:24
     * @param       rolePrivilege
     * @return     : void
     */
    public void addRolePrivilege(RolePrivilege rolePrivilege);

    /**
     *  通过roleId选择角色与权限之间的关联
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 14:24
     * @param       roleId
     * @return     : java.util.List<com.fpms.entity.RolePrivilege>
     */
    public List<RolePrivilege> selectRolePrivilegeByRoleId(Integer roleId);

    /**
     *通过roleId删除角色与权限之间的关联
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 14:22
     * @param       roleId
     * @return     : void
     */
    public void delRolePrivilegeByRoleId(Integer roleId);

    /**
     *  通风privilegeId删除角色与权限的关联
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 14:13
     * @param       privilegeId
     * @return     : void
     */
    public void delRolePrivilegeByPrivilegeId(Integer privilegeId);
}
