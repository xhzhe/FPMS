package com.fpms.service.impl;

import com.fpms.dao.RolePrivilegeDao;
import com.fpms.entity.RolePrivilege;
import com.fpms.service.RolePrivilegeService;
import com.fpms.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : TianHong Liao
 * @date : 2019/6/18 22:46
 * @description:
 * @modified :
 */
@Service
public class RolePrivilegeServiceImpl implements RolePrivilegeService {
    @Autowired
    private RolePrivilegeDao rolePrivilegeDao;

    @Override
    public void addRolePrivilege(RolePrivilege rolePrivilege) {
        rolePrivilegeDao.insertSelective(rolePrivilege);
    }

    @Override
    public List<RolePrivilege> selectRolePrivilegeByRoleId(Integer roleId) {
        return rolePrivilegeDao.selectByRoleId(roleId);
    }

    @Override
    public void delRolePrivilegeByRoleId(Integer roleId) {
        rolePrivilegeDao.deleteByRoleId(roleId);
    }

    @Override
    public void delRolePrivilegeByPrivilegeId(Integer privilegeId) {
        rolePrivilegeDao.deleteByPrivilegeId(privilegeId);

    }
}
