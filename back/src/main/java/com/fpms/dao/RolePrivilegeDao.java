package com.fpms.dao;

import com.fpms.entity.RolePrivilege;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author : YongBiao Liao
 * @date : 2019/6/14 15:00
 * @description: 角色与权限关联表数据操作类
 * @modified :
 */
@Component
public interface RolePrivilegeDao {

    /**
     *  通过主键删除记录
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 14:53
     * @param       rolePrivilegeId
     * @return     : int
     */
    int deleteByPrimaryKey(Integer rolePrivilegeId);

    /**
     *  插入一条记录的所有字段
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 14:53
     * @param       record
     * @return     : int
     */
    int insert(RolePrivilege record);

    /**
     *  插入记录中不为null的字段
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 14:53
     * @param       record
     * @return     : int
     */
    int insertSelective(RolePrivilege record);

    /**
     *  通过主键查找记录
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 14:54
     * @param       rolePrivilegeId
     * @return     : com.fpms.entity.RolePrivilege
     */
    RolePrivilege selectByPrimaryKey(Integer rolePrivilegeId);

    /**
     *  通过主键更新不为null的字段
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 14:54
     * @param       record
     * @return     : int
     */
    int updateByPrimaryKeySelective(RolePrivilege record);

    /**
     *  通过主键更新所有字段
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 14:54
     * @param       record
     * @return     : int
     */
    int updateByPrimaryKey(RolePrivilege record);

    /**
     *  通过roleId查找记录
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 14:54
     * @param       roleId
     * @return     : java.util.List<com.fpms.entity.RolePrivilege>
     */
    List<RolePrivilege> selectByRoleId(Integer roleId);

    /**
     *  通过roleId删除记录
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 14:55
     * @param       roleId
     * @return     : void
     */
    void deleteByRoleId(Integer roleId);

    /**
     *  通过privelegeId删除记录
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 14:55
     * @param       privilegeId
     * @return     : void
     */
    void deleteByPrivilegeId(Integer privilegeId);
}