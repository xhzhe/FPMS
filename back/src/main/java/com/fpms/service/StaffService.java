package com.fpms.service;

import com.fpms.dto.ConfigDetail;

import com.fpms.dto.ProductDetail;
import com.fpms.dto.ProductsAndConfigs;

import com.fpms.entity.Staff;

import java.util.ArrayList;


/**
 * @author : HuiZhe Xu
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

}
