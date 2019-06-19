package com.fpms.service;

import com.fpms.entity.Role;

import java.util.List;

/**
 * @author : TianHong Liao
 * @date : 2019/6/18 17:05
 * @description:
 * @modified :
 */
public interface RoleService {

    /**
     *  添加角色
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 14:28
     * @param       role
     * @return     : void
     */
    public void addRole(Role role);

    /**
     *  查找所有角色
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 14:28
     * @param
     * @return     : java.util.List<com.fpms.entity.Role>
     */
    public List<Role> selectAllRoles();

    /**
     *  通过id删除角色
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 14:28
     * @param       roleId
     * @return     : void
     */
    public void delRoleById(Integer roleId);

    /**
     *  更新角色
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 14:28
     * @param       role
     * @return     : void
     */
    public void updateRole(Role role);

    /**
     *  通过id查找角色
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 14:29
     * @param       roleId
     * @return     : com.fpms.entity.Role
     */
    Role selectRoleById(Integer roleId);
}
