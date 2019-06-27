package com.fpms.dao;

import com.fpms.entity.ProductConfiguration;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author : YongBiao Liao
 * @date : 2019/6/14 15:00
 * @description: 配置评审
 * @modified :
 */
@Component
public interface ProductConfigurationDao {
    /**
     * 通过主键删除配置产品
     * @author     ：YongBiao Liao
     * @date       ：Created in 2019/6/25 10:51
     * @param       configurationId
     * @return     : int
     */
    int deleteByPrimaryKey(Integer configurationId);

    /**
     * 插入ProductConfiguration
     * @author     ：YongBiao Liao
     * @date       ：Created in 2019/6/25 10:54
     * @param       record
     * @return     : int
     */
    int insert(ProductConfiguration record);

    /**
     *
     * @author     ：YongBiao Liao
     * @date       ：Created in 2019/6/25 10:54
     * @param       record
     * @return     : int
     */
    int insertSelective(ProductConfiguration record);

    /**
     * 通过主键查找ProductConfiguration
     * @author     ：YongBiao Liao
     * @date       ：Created in 2019/6/25 10:54
     * @param       configurationId
     * @return     : com.fpms.entity.ProductConfiguration
     */
    ProductConfiguration selectByPrimaryKey(Integer configurationId);

    /**
     * 更新
     * @author     ：YongBiao Liao
     * @date       ：Created in 2019/6/25 10:54
     * @param       record
     * @return     : int
     */
    int updateByPrimaryKeySelective(ProductConfiguration record);

    /**
     * 通过主键更新ProductConfiguration
     * @author     ：YongBiao Liao
     * @date       ：Created in 2019/6/25 10:54
     * @param       record
     * @return     : int
     */
    int updateByPrimaryKey(ProductConfiguration record);

    /**
     * 通过产品标准库id和产品配置id查看配置中某一产品
     * @author     ：YongBiao Liao
     * @date       ：Created in 2019/6/23 23:15
     * @param       productConId
     * @param       productStdId
     * @return     : com.fpms.entity.ProductConfiguration
     */
    ProductConfiguration selectByPCIAndPSI(@Param("productConId") Integer productConId, @Param("productStdId") Integer productStdId);

    /**
     * 通过配置id删除配置
     * @author     ：YongBiao Liao
     * @date       ：Created in 2019/6/24 21:09
     * @param       productConId
     * @return     : java.lang.Integer
     */
    Integer deleteByProductConId(@Param("productConId") Integer productConId);

    List<ProductConfiguration> getProductConfigID(Integer configID);

    /**
     * 通过配置id获取该配置中的所有产品
     * @author     ：YongBiao Liao
     * @date       ：Created in 2019/6/26 16:47
     * @param       productConId
     * @return     : java.util.List<com.fpms.entity.ProductConfiguration>
     */
    List<ProductConfiguration> getProductConfigurationByproductConId(@Param("productConId") Integer productConId);
}