package com.fpms.dao;

import com.fpms.entity.Staff;
import com.fpms.entity.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author : HuiZhe Xu
 * @date : 2019/6/14 15:00
 * @description: 职工表数据操作类
 * @modified :
 */
@Component
public interface StaffDao {

    /**
     *  通过主键staffId删除记录
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 14:51
     * @param       staffId
     * @return     : int
     */
    int deleteByPrimaryKey(Integer staffId);

    /**
     *  插入一条记录中的所有字段
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 14:51
     * @param       record
     * @return     : int
     */
    int insert(Staff record);

    /**
     *  只插入记录中不为null的字段
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 14:51
     * @param       record
     * @return     : int
     */
    int insertSelective(Staff record);

    /**
     *  通过主键staffId查找记录
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 14:52
     * @param       staffId
     * @return     : com.fpms.entity.Staff
     */
    Staff selectByPrimaryKey(Integer staffId);

    /**
     *  通过主键staffId更新不为null的字段
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 14:52
     * @param       record
     * @return     : int
     */
    int updateByPrimaryKeySelective(Staff record);

    /**
     *  通过主键staffId更新记录
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/19 14:52
     * @param       record
     * @return     : int
     */
    int updateByPrimaryKey(Staff record);

    /**
     *  使用名字获取员工
     * @author     : HuiZhe Xu
     * @date       : Created in 2019/6/25 10:58
     * @param       staffName
     * @return     : com.fpms.entity.Staff
     */
    Staff selectByStaffName(String staffName);

    /**
     *  获取所有员工
     * @author     : HuiZhe Xu
     * @date       : Created in 2019/6/25 10:57
     * @param
     * @return     : java.util.List<com.fpms.entity.Staff>
     */
    List<Staff> getStaffs();

}