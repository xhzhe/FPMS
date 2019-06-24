package com.fpms.dao;

import com.fpms.entity.ProductConfiguration;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author : YongBiao Liao
 * @date : 2019/6/14 15:00
 * @description: 配置评审
 * @modified :
 */
@Component
public interface ProductConfigurationDao {
    int deleteByPrimaryKey(Integer configurationId);

    int insert(ProductConfiguration record);

    int insertSelective(ProductConfiguration record);

    ProductConfiguration selectByPrimaryKey(Integer configurationId);

    int updateByPrimaryKeySelective(ProductConfiguration record);

    int updateByPrimaryKey(ProductConfiguration record);

    /**
     * 通过产品标准库id和产品配置id查看配置中某一产品
     * @author     ：YongBiao Liao
     * @date       ：Created in 2019/6/23 23:15
     * @param       productConfigId
     * @param       productStdId
     * @return     : com.fpms.entity.ProductConfiguration
     */
    ProductConfiguration selectByPCIAndPSI(@Param("productConfigId") Integer productConfigId, @Param("productStdId") Integer productStdId);
}