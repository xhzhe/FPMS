package com.fpms.dao;

import com.fpms.entity.Role;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author : YongBiao Liao
 * @date : 2019/6/14 15:00
 * @description: 角色表数据操作类
 * @modified :
 */
@Component
public interface RoleDao {

    /**
     *  通过主键roleId删除记录
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 14:46
     * @param       roleId
     * @return     : int
     */
    int deleteByPrimaryKey(Integer roleId);

    /**
     *  插入一条新的记录
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 14:46
     * @param       record
     * @return     : int
     */
    int insert(Role record);

    /**
     *  插入一条记录并且只插入其中不为null的字段
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 14:47
     * @param       record
     * @return     : int
     */
    int insertSelective(Role record);

    /**
     *  通过主键roleId查找记录
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 14:47
     * @param       roleId
     * @return     : com.fpms.entity.Role
     */
    Role selectByPrimaryKey(Integer roleId);

    /**
     *  通过主键roleId更新一条记录中不为null的字段
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 14:49
     * @param       record
     * @return     : int
     */
    int updateByPrimaryKeySelective(Role record);

    /**
     *  通过主键roleId更新一条记录的全部字段
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 14:49
     * @param       record
     * @return     : int
     */
    int updateByPrimaryKey(Role record);

    /**
     *  返回表中的所有记录
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 14:50
     * @param
     * @return     : java.util.List<com.fpms.entity.Role>
     */
    List<Role> findAllRoles();

    /**
     *  通过roleName返回记录
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/26 14:18
     * @param       roleName
     * @return     : com.fpms.entity.Role
     */
    Role selectByRoleName(String roleName);
}