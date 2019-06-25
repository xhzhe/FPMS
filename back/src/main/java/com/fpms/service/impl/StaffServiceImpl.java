package com.fpms.service.impl;

import com.fpms.dto.ConfigDetail;
import com.fpms.dto.ProductDetail;
import com.fpms.dto.ProductsAndConfigs;
import com.fpms.dao.*;
import com.fpms.entity.*;
import com.fpms.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : HuiZhe Xu
 * @date : 2019/6/14 15:16
 * @description: staff service
 * @modified :
 */
@Service
public class StaffServiceImpl implements StaffService {
    @Autowired
    private ProductLibraryConfigurationDao productLibraryConfigurationDao;
    @Autowired
    private ProductConfigurationDao productConfigurationDao;
    @Autowired
    private ProductLibraryStandardDao productLibraryStandardDao;
    @Autowired
    private ProductLibraryPreDao productLibraryPreDao;
    @Autowired
    private StaffDao staffDao;
    @Autowired
    private StaffRoleDao staffRoleDao;
    /**
     *  从ID获取配置详细信息
     * @author     : HuiZhe Xu
     * @date       : Created in 2019/6/25 11:00
     * @param       configID
     * @return     : com.fpms.dto.ConfigDetail
     */
    @Override
    public ConfigDetail getConfigByID(Integer configID) {
        ProductLibraryConfiguration productLibraryConfiguration = productLibraryConfigurationDao.selectByPrimaryKey(configID);
        if(productLibraryConfiguration==null) {
            return null;
        }
        ConfigDetail res=new ConfigDetail();
        res.configlib=productLibraryConfiguration;
        List<ProductConfiguration> productConfigurations= productConfigurationDao.getProductConfigID(configID);

        for(ProductConfiguration productConfiguration:productConfigurations){
            ProductLibraryStandard productLibraryStandard=productLibraryStandardDao.selectByPrimaryKey(productConfiguration.getProductStdId());
            int productLibraryStandardID=productLibraryStandard.getProductStdId();
            ProductLibraryPre productLibraryPre=productLibraryPreDao.selectByPrimaryKey(productLibraryStandard.getProductPreId());
            if(productLibraryPre==null){
                break;
            }
            Float rate=Float.parseFloat(productConfiguration.getPercentage().toString());
            res.addProduct(productLibraryPre.getProductName(),productLibraryPre.getProductDesc(),rate,productLibraryStandardID);
        }

        return res;
    }
    /**
     *  获取所有配置和产品
     * @author     : HuiZhe Xu
     * @date       : Created in 2019/6/25 11:00
     * @param
     * @return     : com.fpms.dto.ProductsAndConfigs
     */
    @Override
    public ProductsAndConfigs getAllMall() {
        List<ProductLibraryConfiguration> productLibraryConfigurations=productLibraryConfigurationDao.selectAll();
        ProductsAndConfigs productsAndConfigs=new ProductsAndConfigs();
        if(productLibraryConfigurations!=null) {
            productsAndConfigs.configs.addAll(productLibraryConfigurations);
        }
        List<ProductLibraryPre> productLibraryPres=productLibraryPreDao.getAll();
        if(productLibraryPres!=null) {
            productsAndConfigs.products.addAll(productLibraryPres);
        }
        return productsAndConfigs;
    }
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
    @Override
    public boolean addStaff(String name, String pwd, String depart, ArrayList roleList) {
        Staff staff=new Staff();
        staff.setStaffName(name);
        staff.setStaffPwd(pwd);
        staff.setStaffDepartment(depart);
        staffDao.insertSelective(staff);

        Integer ID=staff.getStaffId();
        System.out.println(ID);
        for(Object roleIter:roleList){
            String roleId=(String)roleIter;
            StaffRole staffRole=new StaffRole();
            staffRole.setRoleId(Integer.parseInt(roleId));
            staffRole.setStaffId(ID);
            staffRoleDao.insertSelective(staffRole);
        }
        return true;
    }
    /**
     *  获得单个员工的详细信息
     * @author     : HuiZhe Xu
     * @date       : Created in 2019/6/25 11:01
     * @param       StaffId
     * @return     : com.fpms.entity.Staff
     */
    @Override
    public Staff getSingleStaffDetail(Integer StaffId) {
        Staff staff=staffDao.selectByPrimaryKey(StaffId);

        return staff;
    }
    /**
     *  获取产品简介
     * @author     : HuiZhe Xu
     * @date       : Created in 2019/6/25 11:01
     * @param       ProductID
     * @return     : com.fpms.dto.ProductDetail
     */
    @Override
    public ProductDetail getProductInfo(Integer ProductID) {
        ProductLibraryStandard productLibraryStandard=productLibraryStandardDao.selectByPrimaryKey(ProductID);
        if(productLibraryStandard==null){
            return null;
        }
        Integer productPreId=productLibraryStandard.getProductPreId();
        ProductLibraryPre productLibraryPre=productLibraryPreDao.selectByPrimaryKey(productPreId);
        if(productLibraryPre==null){
            return null;
        }
        ProductDetail productDetail=new ProductDetail();
        productDetail.setProductStdId(ProductID.toString());
        productDetail.setSuitUser(productLibraryStandard.getSuitUser().toString());
        productDetail.setInterRiskRating(productLibraryStandard.getInterRiskRating().toString());
        productDetail.setExchangeRateRiskIndex(productLibraryStandard.getExchangeRateRiskIndex().toString());
        productDetail.setInterestRateRiskIndex(productLibraryStandard.getInterestRateRiskIndex().toString());
        productDetail.setMarketRiskIndex(productLibraryStandard.getMarketRiskIndex().toString());
        productDetail.setCreditRiskIndex(productLibraryStandard.getCreditRiskIndex().toString());
        productDetail.setStock(productLibraryStandard.getStock().toString());
        productDetail.setSaleNum(productLibraryStandard.getSaleNum().toString());
        productDetail.setEvalutionAvgScore(productLibraryStandard.getEvalutionAvgScore().toString());
        productDetail.setProductDesc(productLibraryPre.getProductDesc());
        productDetail.setProductName(productLibraryPre.getProductName());
        productDetail.setProductPrice(productLibraryPre.getProductPrice().toString());
        productDetail.setPurchaseStartPoint(productLibraryPre.getPurchaseStartPoint().toString());
        productDetail.setCategoryId(productLibraryPre.getCategoryId().toString());
        productDetail.setTerm(productLibraryPre.getTerm().toString());
        productDetail.setExpiryDate(productLibraryPre.getExpiryDate().toString());
        productDetail.setRiskRating(productLibraryPre.getRiskRating().toString());
        productDetail.setReturnRate(productLibraryPre.getReturnRate().toString());
        productDetail.setIncomeType(productLibraryPre.getIncomeType().toString());
        productDetail.setUnitNetValue(productLibraryPre.getUnitNetValue().toString());
        productDetail.setUnitNetValue(productLibraryPre.getUnitNetValue().toString());
        productDetail.setCumulativeNetValue(productLibraryPre.getCumulativeNetValue().toString());
        productDetail.setNotice(productLibraryPre.getNotice());
        productDetail.setPurchaseLimit(productLibraryPre.getPurchaseLimit().toString());

        return productDetail;
    }
    /**
     *  获取所有员工
     * @author     : HuiZhe Xu
     * @date       : Created in 2019/6/25 11:01
     * @param
     * @return     : java.util.ArrayList<com.fpms.entity.Staff>
     */
    @Override
    public ArrayList<Staff> getStaffs() {
        List<Staff> res=staffDao.getStaffs();
        if(res.isEmpty()){
            return null;
        }
        ArrayList<Staff> result=new ArrayList<>(res);
        return result;
    }



}

