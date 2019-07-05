package com.fpms.service.impl;

import com.fpms.dto.ConfigDetail;
import com.fpms.dto.ProductDetail;
import com.fpms.dto.ProductsAndConfigs;
import com.fpms.dao.*;
import com.fpms.entity.*;
import com.fpms.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

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
    @Autowired
    private RoleDao roleDao;
    /**
     *  从ID获取配置详细信息
     * @author     : HuiZhe Xu
     * @date       : Created in 2019/6/25 11:00
     * @param       configId
     * @return     : com.fpms.dto.ConfigDetail
     */
    @Override
    public ConfigDetail getConfigById(Integer configId) throws Exception {
        ProductLibraryConfiguration productLibraryConfiguration = productLibraryConfigurationDao.selectByPrimaryKey(configId);
        if(productLibraryConfiguration==null) {
            throw new Exception("不存在对应的配置");
        }
        ConfigDetail res=new ConfigDetail();
        res.configlib=productLibraryConfiguration;
        List<ProductConfiguration> productConfigurations= productConfigurationDao.getProductConfigId(configId);

        for(ProductConfiguration productConfiguration:productConfigurations){
            ProductLibraryStandard productLibraryStandard=productLibraryStandardDao.selectByPrimaryKey(productConfiguration.getProductStdId());
            int productLibraryStandardId=productLibraryStandard.getProductStdId();
            ProductLibraryPre productLibraryPre=productLibraryPreDao.selectByPrimaryKey(productLibraryStandard.getProductPreId());
            if(productLibraryPre==null){
                throw new Exception("该配置不合法：存在标准库中含有而预选库不存在的产品");
            }
            Float rate=Float.parseFloat(productConfiguration.getPercentage().toString());
            res.addProduct(productLibraryPre.getProductName(),productLibraryPre.getProductDesc(),rate,productLibraryStandardId);
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
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/26 14:50
     * @param       staff
     * @param       roleName
     * @return     : boolean
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addStaff(Staff staff, String roleName) throws Exception {
        Staff staffTemp=staffDao.selectByStaffName(staff.getStaffName());
        if(staffTemp!=null){
            throw new Exception("员工名已存在");
        }
        //添加职工
        int count = staffDao.insertSelective(staff);
        if(count<=0){
            throw new Exception("添加职工失败，可能表现为数据缺失");
        }
        //添加职工与角色的关联
        Role role = roleDao.selectByRoleName(roleName);
        if(role==null){
            throw new Exception("不存在该角色");
        }
        StaffRole staffRole = new StaffRole();
        staffRole.setRoleId(role.getRoleId());
        staffRole.setStaffId(staff.getStaffId());
        count = staffRoleDao.insertSelective(staffRole);
        if(count<=0){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new Exception("为该用户添加角色失败");
        }
        return true;
    }
    /**
     *  获得单个员工的详细信息
     * @author     : HuiZhe Xu
     * @date       : Created in 2019/6/25 11:01
     * @param       staffId
     * @return     : com.fpms.entity.Staff
     */
    @Override
    public Staff getSingleStaffDetail(Integer staffId) throws Exception {
        Staff staff=staffDao.selectByPrimaryKey(staffId);
        if(staff==null){
            throw new Exception("不存在该员工");
        }
        return staff;
    }
    /**
     *  获取产品简介
     * @author     : HuiZhe Xu
     * @date       : Created in 2019/6/25 11:01
     * @param       productId
     * @return     : com.fpms.dto.ProductDetail
     */
    @Override
    public ProductDetail getProductInfo(Integer productId) throws Exception {
        ProductLibraryStandard productLibraryStandard=productLibraryStandardDao.selectByPrimaryKey(productId);
        if(productLibraryStandard==null){
            throw new Exception("标准库中不存在该产品");
        }
        Integer productPreId=productLibraryStandard.getProductPreId();
        ProductLibraryPre productLibraryPre=productLibraryPreDao.selectByPrimaryKey(productPreId);
        if(productLibraryPre==null){
            throw new Exception("配置库中不存在该产品");
        }
        ProductDetail productDetail=new ProductDetail();
        productDetail.setProductStdId(productId.toString());
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
    public ArrayList<Staff> getStaffs() throws Exception {
        List<Staff> res=staffDao.getStaffs();
        if(res.isEmpty()){
            throw new Exception("员工列表为空");
        }
        return new ArrayList<>(res);
    }

    /**
     *  更新职工信息
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/26 13:47
     * @param       staff
     * @return     : boolean
     */
    @Override
    public boolean updateStaff(Staff staff) {
        int count=staffDao.updateByPrimaryKeySelective(staff);
        if(count>0){
            return true;
        }
        return false;
    }

    /**
     * 删除员工
     *
     * @param id
     * @return : boolean
     * @author : HuiZhe Xu
     * @date : Created in 2019/6/26 14:31
     */
    @Override
    public boolean delStaff(Integer id) throws Exception {
        int count=staffDao.deleteByPrimaryKey(id);
        if(count>0){
            return true;
        }
        throw new Exception("删除失败");
    }
}

