package com.fpms.service;

import com.fpms.entity.Privilege;

import java.util.List;

/**
 * @author : TianHong Liao
 * @date : 2019/6/18 17:00
 * @description:
 * @modified :
 */

public interface PrivilegeService {

    /**
     *  添加权限
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 14:26
     * @param       privilege
     * @return     : void
     */
    public void addPrivilege(Privilege privilege);

    /**
     *  查找所有权限
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 14:26
     * @param
     * @return     : java.util.List<com.fpms.entity.Privilege>
     */
    public List<Privilege> selectAllPrivileges();

    /**
     *  通过privilegeId删除权限
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 14:26
     * @param       privilegeId
     * @return     : void
     */
    public void delPrivilegeById(Integer privilegeId);

    /**
     *  更新权限
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 14:26
     * @param       privilege
     * @return     : void
     */
    public void updatePrivilege(Privilege privilege);

    /**
     *  通过privilegeId查找权限
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 14:27
     * @param       privilegeId
     * @return     : com.fpms.entity.Privilege
     */
    public Privilege selectById(Integer privilegeId);
}
