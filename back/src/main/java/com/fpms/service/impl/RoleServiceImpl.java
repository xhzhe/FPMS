package com.fpms.service.impl;

import com.fpms.dao.RoleDao;
import com.fpms.entity.Role;
import com.fpms.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : TianHong Liao
 * @date : 2019/6/18 22:20
 * @description:
 * @modified :
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Override
    public void addRole(Role role) {
        roleDao.insertSelective(role);
    }

    @Override
    public List<Role> selectAllRoles() {
        return roleDao.findAllRoles();
    }

    @Override
    public void delRoleById(Integer roleId) {
        roleDao.deleteByPrimaryKey(roleId);
    }

    @Override
    public void updateRole(Role role) {
        roleDao.updateByPrimaryKeySelective(role);
    }

    @Override
    public Role selectRoleById(Integer roleId) {
        return roleDao.selectByPrimaryKey(roleId);
    }
}
