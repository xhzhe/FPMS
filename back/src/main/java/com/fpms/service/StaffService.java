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
     * 从ID获取配置详细信息
     *
     * @param configId
     * @return : com.fpms.dto.ConfigDetail
     * @author : HuiZhe Xu
     * @date : Created in 2019/6/25 11:00
     */
    ConfigDetail getConfigById(Integer configId) throws Exception;

    /**
     * 获取所有配置和产品
     *
     * @param
     * @return : com.fpms.dto.ProductsAndConfigs
     * @author : HuiZhe Xu
     * @date : Created in 2019/6/25 11:00
     */
    ProductsAndConfigs getAllMall();

    /**
     * 添加员工
     *
     * @param staff
     * @param roleName
     * @author ：TianHong Liao
     * @date ：Created in 2019/6/26 14:50
     */
    void addStaff(Staff staff, String roleName) throws Exception;

    /**
     * 获得单个员工的详细信息
     *
     * @param staffId
     * @return : com.fpms.entity.Staff
     * @author : HuiZhe Xu
     * @date : Created in 2019/6/25 11:01
     */
    Staff getSingleStaffDetail(Integer staffId) throws Exception;

    /**
     * 获取产品简介
     *
     * @param productId
     * @return : com.fpms.dto.ProductDetail
     * @author : HuiZhe Xu
     * @date : Created in 2019/6/25 11:01
     */
    ProductDetail getProductInfo(Integer productId) throws Exception;

    /**
     * 获取所有员工
     *
     * @param
     * @return : java.util.ArrayList<com.fpms.entity.Staff>
     * @author : HuiZhe Xu
     * @date : Created in 2019/6/25 11:01
     */
    ArrayList<Staff> getStaffs() throws Exception;

    /**
     * 更新职工信息
     *
     * @param staff
     * @author ：TianHong Liao
     * @date ：Created in 2019/6/26 13:42
     */
    void updateStaff(Staff staff) throws Exception;

    /**
     * 删除员工
     *
     * @param id
     * @author : HuiZhe Xu
     * @date : Created in 2019/6/26 14:31
     */
    void delStaff(Integer id) throws Exception;
}
