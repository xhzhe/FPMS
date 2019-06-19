package com.fpms.service.impl;

import com.fpms.dao.StaffDao;
import com.fpms.entity.Staff;
import com.fpms.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : YongBiao Liao
 * @date : 2019/6/14 15:16
 * @description:
 * @modified :
 */
@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffDao staffDao;
    @Override
    public List<Staff> selectAllStaff() {
        return staffDao.findAllStaff();
    }

    @Override
    public Staff selectStaffById(Integer staffId) {
        return staffDao.selectByPrimaryKey(staffId);
    }
}

