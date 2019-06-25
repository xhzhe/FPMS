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
    /**
     *  从ID获取配置详细信息
     * @author     : HuiZhe Xu
     * @date       : Created in 2019/6/25 11:00
     * @param       configID
     * @return     : com.fpms.dto.ConfigDetail
     */
    ConfigDetail getConfigByID(Integer configID);
    /**
     *  获取所有配置和产品
     * @author     : HuiZhe Xu
     * @date       : Created in 2019/6/25 11:00
     * @param
     * @return     : com.fpms.dto.ProductsAndConfigs
     */
    ProductsAndConfigs getAllMall();
    /**
     *  添加员工
     * @author     : HuiZhe Xu
     * @date       : Created in 2019/6/25 11:00
     * @param       name
     * @param       pwd
     * @param       depart
     * @param       roleList
     * @return     : boolean
     */
    boolean addStaff(String name, String pwd, String depart, ArrayList roleList);
    /**
     *  获得单个员工的详细信息
     * @author     : HuiZhe Xu
     * @date       : Created in 2019/6/25 11:01
     * @param       StaffId
     * @return     : com.fpms.entity.Staff
     */
    Staff getSingleStaffDetail(Integer StaffId);
    /**
     *  获取产品简介
     * @author     : HuiZhe Xu
     * @date       : Created in 2019/6/25 11:01
     * @param       ProductID
     * @return     : com.fpms.dto.ProductDetail
     */
    ProductDetail getProductInfo(Integer ProductID);
    /**
     *  获取所有员工
     * @author     : HuiZhe Xu
     * @date       : Created in 2019/6/25 11:01
     * @param
     * @return     : java.util.ArrayList<com.fpms.entity.Staff>
     */
    ArrayList<Staff> getStaffs();

}
