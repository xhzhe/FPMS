package com.fpms.service;

import com.fpms.entity.StaffRole;

import java.util.List;

/**
 * @author : TianHong Liao
 * @date : 2019/6/19 10:26
 * @description:
 * @modified :
 */
public interface StaffRoleService {

    /**
     *  添加职工与角色的关联
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 14:30
     * @param       staffRole
     * @return     : void
     */
    void addStaffRole(StaffRole staffRole);

    /**
     *  通过staffId删除职工与角色的关联
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 14:30
     * @param       staffId
     * @return     : void
     */
    void delStaffRoleByStaffId(Integer staffId);

    /**
     *  通过roleId删除职工与角色的关联
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 14:30
     * @param       roleId
     * @return     : void
     */
    void delStaffRoleByRoleId(Integer roleId);

    /**
     *  通过staffId查找职工与角色的关联
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 14:30
     * @param       staffId
     * @return     : com.fpms.entity.StaffRole
     */
    List<StaffRole> selectStaffRoleByStaffId(Integer staffId) throws Exception;
}
