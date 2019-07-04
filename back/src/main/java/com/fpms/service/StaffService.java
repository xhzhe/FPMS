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
     * @param       configId
     * @return     : com.fpms.dto.ConfigDetail
     */
    ConfigDetail getConfigById(Integer configId) throws Exception;
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
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/26 14:50
     * @param       staff
     * @param       roleName
     * @return     : boolean
     */
    boolean addStaff(Staff staff, String roleName) throws Exception;
    /**
     *  获得单个员工的详细信息
     * @author     : HuiZhe Xu
     * @date       : Created in 2019/6/25 11:01
     * @param       staffId
     * @return     : com.fpms.entity.Staff
     */
    Staff getSingleStaffDetail(Integer staffId) throws Exception;
    /**
     *  获取产品简介
     * @author     : HuiZhe Xu
     * @date       : Created in 2019/6/25 11:01
     * @param       productId
     * @return     : com.fpms.dto.ProductDetail
     */
    ProductDetail getProductInfo(Integer productId) throws Exception;
    /**
     *  获取所有员工
     * @author     : HuiZhe Xu
     * @date       : Created in 2019/6/25 11:01
     * @param
     * @return     : java.util.ArrayList<com.fpms.entity.Staff>
     */
    ArrayList<Staff> getStaffs() throws Exception;

    /**
     *  更新职工信息
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/26 13:42
     * @param       staff
     * @return     : boolean
     */
    boolean updateStaff(Staff staff);
    /**
     *  删除员工
     * @author     : HuiZhe Xu
     * @date       : Created in 2019/6/26 14:31
     * @param       id
     * @return     : boolean
     */
    boolean delStaff(Integer id) throws Exception;
}
