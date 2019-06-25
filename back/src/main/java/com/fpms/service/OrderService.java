package com.fpms.service;

import com.fpms.entity.Order;

import java.util.List;

/**
 * @author : YongBiao Liao
 * @date : 2019/6/14 14:57
 * @description:
 * @modified :
 */
public interface OrderService {
    /**
     *  返回用户的所有订单
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/20 16:08
     * @param       userId
     * @return     : java.util.List<com.fpms.entity.Order>
     */
    List<Order> selectOrdersByUserId(Integer userId);

    /**
     *  新增订单
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/20 16:12
     * @param       order
     * @return     : void
     */
    void addOrder(Order order);

    /**
     *  删除订单
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/20 16:15
     * @param       orderId
     * @return     : void
     */
    void delOrderById(Integer orderId);

    /**
     *  结算订单
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/21 10:46
     * @param       order
     * @return     : void
     */
    void updateOrder(Order order);

    /**
     *  通过orderId获取订单
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/25 10:18
     * @param       orderId
     * @return     : com.fpms.entity.Order
     */
    Order selectOrderByOrderId(Integer orderId);
}
