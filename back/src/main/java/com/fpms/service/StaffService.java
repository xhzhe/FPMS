package com.fpms.service;

import com.fpms.DTO.ConfigDetail;

import com.fpms.DTO.ProductDetail;
import com.fpms.DTO.ProductsAndConfigs;

import com.fpms.entity.Staff;

import java.util.ArrayList;


/**
 * @author : YongBiao Liao
 * @date : 2019/6/14 14:58
 * @description:
 * @modified :
 */

public interface StaffService {
    ConfigDetail getConfigByID(Integer configID);

    ProductsAndConfigs getAllMall();

    boolean addStaff(String name, String pwd, String depart, ArrayList roleList);

    Staff getSingleStaffDetail(Integer StaffId);

    ProductDetail getProductInfo(Integer ProductID);

    ArrayList<Staff> getStaffs();

    Boolean ModifyPrivilege(Integer staffId,ArrayList Privileges);
}
