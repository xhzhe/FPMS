package com.fpms.controller;

import com.fpms.entity.LogOperate;
import com.fpms.entity.Order;
import com.fpms.entity.pojo.ResultBean;
import com.fpms.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : YongBiao Liao
 * @date : 2019/6/14 15:00
 * @description:订单逻辑控制
 * @modified :
 */
@RestController
@CrossOrigin
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     *  获取用户的订单列表
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/21 10:47
     * @param       userId
     * @return     : com.fpms.entity.pojo.ResultBean<java.util.List<com.fpms.entity.Order>>
     */
    @GetMapping("/user/{userId}/orders")
    public ResultBean<List<Order> > selectOrdersByUser(@PathVariable Integer userId){
        List<Order> orderList = new ArrayList<>();
        try{
            orderList = orderService.selectOrdersByUserId(userId);
        }
        catch (Exception e){
            return new ResultBean<>(e);
        }
        return new ResultBean<>(orderList);
    }

    /**
     *  新增订单
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/21 10:47
     * @param       order
     * @return     : com.fpms.entity.pojo.ResultBean<java.lang.Boolean>
     */
    @PostMapping("/order")
    public ResultBean<Boolean> addOrder(@RequestBody Order order){
        try{
            orderService.addOrder(order);
        }
        catch (Exception e){
            return new ResultBean<>(e);
        }
        return new ResultBean<>(true);
    }

    /**
     *  删除订单
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/21 10:47
     * @param       orderId
     * @return     : com.fpms.entity.pojo.ResultBean<java.lang.Boolean>
     */
    @DeleteMapping("/order/{orderId}")
    public ResultBean<Boolean> delOrder(@PathVariable Integer orderId){
        try{
            orderService.delOrderById(orderId);
        }
        catch (Exception e){
            return new ResultBean<>(e);
        }
        return new ResultBean<>(true);
    }
    /**
     *  结算订单
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/21 10:45
     * @param       orderId
     * @param       order
     * @return     : com.fpms.entity.pojo.ResultBean<java.lang.Boolean>
     */
    @PutMapping("/order/{orderId}")
    public ResultBean<Boolean> updateOrder(@PathVariable Integer orderId,@RequestBody Order order){
        try{
            order.setOrderId(orderId);
            order.setOrderStatus(Byte.valueOf("2"));
            orderService.updateOrder(order);
        }
        catch (Exception e){
            return new ResultBean<>(e);
        }
        return new ResultBean<>(true);
    }
}
