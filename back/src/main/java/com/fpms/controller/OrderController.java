package com.fpms.controller;

import com.fpms.dto.OrderDto;
import com.fpms.entity.*;
import com.fpms.entity.pojo.ResultBean;
import com.fpms.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private ProductLibraryStandardService productLibraryStandardService;

    @Autowired
    private ProductLibraryConfigurationService productLibraryConfigurationService;

    @Autowired
    private ProductLibraryPreService productLibraryPreService;

    @Autowired
    private UserService userService;

    @Autowired
    private LogMoneyService logMoneyService;
    /**
     *  获取用户的订单列表
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/21 10:47
     * @param       userId
     * @return     : com.fpms.entity.pojo.ResultBean<java.util.List<com.fpms.entity.Order>>
     */
    @GetMapping("/user/{userId}/orders")
    public ResultBean<List<OrderDto> > selectOrdersByUser(HttpServletRequest request,@PathVariable Integer userId){
        List<OrderDto> orderDtoList = new ArrayList<>();
        try{
            List<Order> orderList = orderService.selectOrdersByUserId(userId);
            for(int i=0;i<orderList.size();i++){
                Order order = orderList.get(i);
                OrderDto orderDto = new OrderDto();
                orderDto.setOrderId(order.getOrderId());
                orderDto.setCreateTime(order.getCreateTime());
                orderDto.setOrderMoney(order.getOrderMoney());
                orderDto.setOrderStatus(order.getOrderStatus());
                User user = userService.getUserById(userId);
                orderDto.setUserName(user.getUserName());
                if(order.getOrderType() == 1){
                    Integer ProductPreId = productLibraryStandardService.selectById(order.getProductStdId()).getProductPreId();
                    ProductLibraryPre productLibraryPre = productLibraryPreService.selectById(ProductPreId);
                    orderDto.setProductName(productLibraryPre.getProductName());
                }else if(order.getOrderType() == 2){
                    ProductLibraryConfiguration productLibraryConfiguration = productLibraryConfigurationService.selectById(order.getProductConId());
                    orderDto.setProductName(productLibraryConfiguration.getProductConName());
                }
                orderDtoList.add(orderDto);
            }
        }
        catch (Exception e){
            return new ResultBean<>(e);
        }
        return new ResultBean<>(orderDtoList);
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
    public  ResultBean<Boolean> updateOrder(@PathVariable Integer orderId,@RequestBody Order order){
        try{
            //更改订单状态
            order.setOrderId(orderId);
            order.setOrderStatus(Byte.valueOf("2"));
            orderService.updateOrder(order);

            Order order1 = orderService.selectOrderByOrderId(orderId);
            if(order1.getOrderType() == 1){
                //减少库存
                ProductLibraryStandard productLibraryStandard = productLibraryStandardService.selectById(order1.getProductStdId());
                productLibraryStandard.setStock(productLibraryStandard.getStock() - 1);
                productLibraryStandardService.updateProductStandard(productLibraryStandard);
                //支付
                User user = userService.getUserById(order1.getUserId());
                user.setUserMoney(user.getUserMoney().subtract(order1.getOrderMoney()));
                userService.updateUser(user);
                //资金变动记录
                LogMoney logMoney = new LogMoney();
                logMoney.setMoneyType(Byte.valueOf("1"));
                logMoney.setUserId(user.getUserId());
                logMoney.setMoney(order1.getOrderMoney());
                logMoney.setUserMoney(user.getUserMoney());
                logMoney.setRemark(order1.getProductStdId().toString());
                logMoneyService.addLogMoney(logMoney);
            }
            else if(order1.getOrderType() == 2){
                //减少库存
                ProductLibraryConfiguration productLibraryConfiguration= productLibraryConfigurationService.selectById(order1.getProductConId());
                productLibraryConfiguration.setStock(productLibraryConfiguration.getStock() - 1);
                productLibraryConfigurationService.updateProductConfiguration(productLibraryConfiguration);
                //支付
                User user = userService.getUserById(order1.getUserId());
                user.setUserMoney(user.getUserMoney().subtract(order1.getOrderMoney()));
                //资金变动记录
                LogMoney logMoney = new LogMoney();
                logMoney.setMoneyType(Byte.valueOf("1"));
                logMoney.setUserId(user.getUserId());
                logMoney.setRemark(order1.getProductConId().toString());
                logMoney.setMoney(order1.getOrderMoney());
                logMoney.setUserMoney(user.getUserMoney());
                logMoneyService.addLogMoney(logMoney);
            }
        }
        catch (Exception e){
            return new ResultBean<>(e);
        }
        return new ResultBean<>(true);
    }
}
