package com.fpms.dao;

import com.fpms.entity.StaffRole;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author : YongBiao Liao
 * @date : 2019/6/14 15:00
 * @description: 职工与角色关联表数据操作
 * @modified :
 */
@Component
public interface StaffRoleDao {
    /**
     *  通过staffRoleId删除一条记录
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 14:42
     * @param       staffRoleId
     * @return     : int
     */
    int deleteByPrimaryKey(Integer staffRoleId);

    /**
     *  插入一条staffRole
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 14:42
     * @param       record
     * @return     : int
     */
    int insert(StaffRole record);

    /**
     *  只插入不为null的字段
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 14:43
     * @param       record
     * @return     : int
     */
    int insertSelective(StaffRole record);

    /**
     *  通过主键staffRoleId查找一条记录
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 14:43
     * @param       staffRoleId
     * @return     : com.fpms.entity.StaffRole
     */
    StaffRole selectByPrimaryKey(Integer staffRoleId);

    /**
     *  通过主键更新不为null的字段
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 14:44
     * @param       record
     * @return     : int
     */
    int updateByPrimaryKeySelective(StaffRole record);

    /**
     *  通过主键更新全部字段
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 14:44
     * @param       record
     * @return     : int
     */
    int updateByPrimaryKey(StaffRole record);

    /**
     *  通过staffId删除一条记录
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 14:45
     * @param       staffId
     * @return     : void
     */
    void deleteByStaffId(Integer staffId);

    /**
     *  通过roleId删除一条记录
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 14:45
     * @param       roleId
     * @return     : void
     */
    void deleteByRoleId(Integer roleId);

    /**
     *  通过staffId查找记录
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 14:45
     * @param       staffId
     * @return     : java.util.List<com.fpms.entity.StaffRole>
     */
    List<StaffRole> selectByStaffId(Integer staffId);
}