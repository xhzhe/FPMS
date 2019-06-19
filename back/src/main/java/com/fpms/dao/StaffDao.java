package com.fpms.dao;

import com.fpms.entity.Staff;
import com.fpms.entity.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author : YongBiao Liao
 * @date : 2019/6/14 15:00
 * @description: 配置评审
 * @modified :
 */
@Component
public interface StaffDao {
    int deleteByPrimaryKey(Integer staffId);

    int insert(Staff record);

    int insertSelective(Staff record);

    Staff selectByPrimaryKey(Integer staffId);

    /*/**
     *
     * @author     ：YongBiao Liao
     * @date       ：Created in 2019/6/19 14:40
     * @param       record
     * @return     : int
     */
    int updateByPrimaryKeySelective(Staff record);

    /**
     *
     * @author     ：YongBiao Liao
     * @date       ：Created in 2019/6/19 14:39
     * @param       record
     * @return     : int
     */
    int updateByPrimaryKey(Staff record);

    /**
     * 通过职工名查找职工
     * @author     ：YongBiao Liao
     * @date       ：Created in 2019/6/18 20:37
     * @param       staffName
     * @return     : com.fpms.entity.User
     */
    Staff selectByStaffName(String staffName);

    /**
     *  返回所有职工列表
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 14:53
     * @param
     * @return     : java.util.List<com.fpms.entity.Staff>
     */
    List<Staff> findAllStaff();
}