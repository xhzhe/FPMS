package com.fpms.service.impl;

import com.fpms.dao.StaffRoleDao;
import com.fpms.entity.StaffRole;
import com.fpms.service.StaffRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : TianHong Liao
 * @date : 2019/6/19 10:26
 * @description:
 * @modified :
 */
@Service
public class StaffRoleServiceImpl implements StaffRoleService {
    @Autowired
    private StaffRoleDao staffRoleDao;

    @Override
    public void addStaffRole(StaffRole staffRole) {
        staffRoleDao.insertSelective(staffRole);
    }

    @Override
    public void delStaffRoleByStaffId(Integer staffId) {
        staffRoleDao.deleteByStaffId(staffId);
    }

    @Override
    public void delStaffRoleByRoleId(Integer roleId) {
        staffRoleDao.deleteByRoleId(roleId);
    }

    /**
     *  返回员工角色
     * @author     : HuiZhe Xu
     * @date       : Created in 2019/7/1 14:55
     * @param       staffId
     * @return     : java.util.List<com.fpms.entity.StaffRole>
     */
    @Override
    public List<StaffRole> selectStaffRoleByStaffId(Integer staffId) throws Exception {
        List<StaffRole>staffRoles = staffRoleDao.selectByStaffId(staffId);
        if(staffRoles==null||staffRoles.size()<=0){
            throw new Exception("该员工暂无角色");
        }
        return staffRoles;
    }
}
