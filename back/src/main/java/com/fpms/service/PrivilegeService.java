package com.fpms.service;

import com.fpms.entity.Privilege;

import java.util.List;

/**
 * @author : TianHong Liao
 * @date : 2019/6/18 17:00
 * @description:
 * @modified :
 */

public interface PrivilegeService {
    void addPrivilege(Privilege privilege);

    List<Privilege> selectAllPrivileges();

    void delPrivilegeById(Integer privilege_id);

    void updatePrivilege(Privilege privilege);
}
