package com.fpms.service.impl;

import com.fpms.dao.PrivilegeDao;
import com.fpms.entity.Privilege;
import com.fpms.service.PrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : TianHong Liao
 * @date : 2019/6/18 18:43
 * @description:
 * @modified :
 */
@Service
public class PrivilegeServiceImpl implements PrivilegeService {
    @Autowired
    private PrivilegeDao privilegeDao;

    @Override
    public void addPrivilege(Privilege privilege){
        privilegeDao.insertSelective(privilege);
    }

    @Override
    public List<Privilege> selectAllPrivileges(){
        return privilegeDao.findAllPrivileges();
    }

    @Override
    public void delPrivilegeById(Integer privilege_id){
        privilegeDao.deleteByPrimaryKey(privilege_id);
    }

    @Override
    public void updatePrivilege(Privilege privilege){
        privilegeDao.updateByPrimaryKeySelective(privilege);
    }
}
