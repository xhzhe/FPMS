package com.fpms.dao;

import com.fpms.entity.Order;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author : YongBiao Liao
 * @date : 2019/6/14 15:00
 * @description: 配置评审
 * @modified :
 */
@Component
public interface OrderDao {
    /**
     *  通过主键删除记录
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/21 10:48
     * @param       orderId
     * @return     : int
     */
    int deleteByPrimaryKey(Integer orderId);

    /**
     *  插入一条记录的所有字段
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/21 10:48
     * @param       record
     * @return     : int
     */
    int insert(Order record);

    /**
     *  插入一条记录的非null字段
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/21 10:49
     * @param       record
     * @return     : int
     */
    int insertSelective(Order record);

    /**
     *  通过主键查询记录
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/21 10:49
     * @param       orderId
     * @return     : com.fpms.entity.Order
     */
    Order selectByPrimaryKey(Integer orderId);

    /**
     *  通过主键更新记录的非null字段
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/21 10:49
     * @param       record
     * @return     : int
     */
    int updateByPrimaryKeySelective(Order record);

    /**
     *  通过主键更新记录的所有字段
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/21 10:49
     * @param       record
     * @return     : int
     */
    int updateByPrimaryKey(Order record);

    /**
     *  通过userId查询记录
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/21 10:50
     * @param       userId
     * @return     : java.util.List<com.fpms.entity.Order>
     */
    List<Order> selectAllByUserId(Integer userId);

    /**
     *  获取所有记录
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/27 23:15
     * @param
     * @return     : java.util.List<com.fpms.entity.Order>
     */
    List<Order> selectAll();
}