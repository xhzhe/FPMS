package com.fpms.dao;

import com.fpms.entity.Staff;
import com.fpms.entity.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

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

    int updateByPrimaryKeySelective(Staff record);

    int updateByPrimaryKey(Staff record);

    /**
     * 通过职工名查找职工
     * @author     ：YongBiao Liao
     * @date       ：Created in 2019/6/18 20:37
     * @param       staffName
     * @return     : com.fpms.entity.User
     */
    Staff selectByStaffName(String staffName);
}