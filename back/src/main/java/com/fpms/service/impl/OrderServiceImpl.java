package com.fpms.service.impl;

import com.fpms.dao.OrderDao;
import com.fpms.entity.Order;
import com.fpms.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : YongBiao Liao
 * @date : 2019/6/14 15:15
 * @description:
 * @modified :
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

    @Override
    public List<Order> selectOrdersByUserId(Integer userId) {
        return orderDao.selectAllByUserId(userId);
    }

    @Override
    public void addOrder(Order order) {
        orderDao.insertSelective(order);
    }

    @Override
    public void delOrderById(Integer orderId) {
        orderDao.deleteByPrimaryKey(orderId);
    }

    @Override
    public void updateOrder(Order order) {
        orderDao.updateByPrimaryKeySelective(order);
    }
}
