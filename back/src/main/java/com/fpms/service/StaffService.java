package com.fpms.service;

import com.fpms.entity.Staff;

import java.util.List;

/**
 * @author : YongBiao Liao
 * @date : 2019/6/14 14:58
 * @description:
 * @modified :
 */
public interface StaffService {
    List<Staff> selectAllStaff();

    Staff selectStaffById(Integer staffId);
}
