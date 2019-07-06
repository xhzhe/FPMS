package com.fpms.controller;

import com.fpms.dto.OrderDto;
import com.fpms.dto.Product;
import com.fpms.entity.*;
import com.fpms.entity.pojo.ResultBean;
import com.fpms.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
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

    @Autowired
    private ProductUserService productUserService;

    @Autowired
    private EvaluationService evaluationService;
    /**
     *  获取用户的订单列表
     * @author     ：TianHong Liao
     * @date       ：Created in 2019/6/21 10:47
     * @param       userId
     * @return     : com.fpms.entity.pojo.ResultBean<java.util.List<com.fpms.entity.Order>>
     */
    @GetMapping("/user/{userId}/orders")
    public ResultBean<List<OrderDto> > selectOrdersByUser(@PathVariable Integer userId){
        List<OrderDto> orderDtoList = new ArrayList<>();
        try{
            List<Order> orderList = orderService.selectOrdersByUserId(userId);
            for(int i=0;i<orderList.size();i++){
                Order order = orderList.get(i);
                OrderDto orderDto = new OrderDto();
                orderDto.setOrder(order);
                User user = userService.getUserById(userId);
                orderDto.setUserName(user.getUserName());
                if(order.getOrderType() == 1){
                    ProductLibraryStandard productLibraryStandard = productLibraryStandardService.selectById(order.getProductStdId());
                    ProductLibraryPre productLibraryPre = productLibraryPreService.selectById(productLibraryStandard.getProductPreId());
                    orderDto.setProductName(productLibraryPre.getProductName());
                    orderDto.setProductLibraryStandard(productLibraryStandard);
                }else if(order.getOrderType() == 2){
                    ProductLibraryConfiguration productLibraryConfiguration = productLibraryConfigurationService.selectById(order.getProductConId());
                    orderDto.setProductName(productLibraryConfiguration.getProductConName());
                    orderDto.setProductLibraryConfiguration(productLibraryConfiguration);
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
    public ResultBean<Boolean> addOrder(@RequestBody Order order) throws Exception {
        if(order.getUserId() == null || userService.getUserById(order.getUserId()) == null){
            return new ResultBean<>("用户Id不存在!");
        }
        if(order.getOrderType() != 1 && order.getOrderType() != 2){
            return new ResultBean<>("订单类型输入错误！");
        }
        if(order.getOrderType() == 1){
            if(order.getProductStdId() == null || productLibraryStandardService.selectById(order.getProductStdId()) == null){
                return new ResultBean<>("预选库产品不存在!");
            }
            ProductLibraryStandard productLibraryStandard = productLibraryStandardService.selectById(order.getProductStdId());
            if(productLibraryStandard.getStock() == 0){
                return new ResultBean<>("库存不足，无法下单!");
            }
            if( order.getOrderMoney().doubleValue() < productLibraryPreService.selectByStdId(productLibraryStandard.getProductStdId()).
                    getPurchaseStartPoint().doubleValue() ){
                return new ResultBean<>("购买金额未达到起购点，无法下单!");
            }
            if(order.getOrderMoney().doubleValue() > productLibraryStandard.getStock()){
                return new ResultBean<>("购买金额超过最大金额限制！");
            }
        }else if (order.getOrderType() == 2){
            if(order.getProductConId() == null || productLibraryConfigurationService.selectById(order.getProductConId()) == null){
                return new ResultBean<>("配置库产品不存在!");
            }
            ProductLibraryConfiguration productLibraryConfiguration = productLibraryConfigurationService.selectById(order.getProductConId());
            if(productLibraryConfiguration.getStock() == 0){
                return new ResultBean<>("库存不足，无法下单!");
            }
        }
        try{
            //新增订单
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
        if(orderService.selectOrderByOrderId(orderId) == null){
            return new ResultBean<>("要删除的订单不存在！");
        }
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
     * @date       ：Created in 2019/6/28 9:39
     * @param       orderId
     * @return     : com.fpms.entity.pojo.ResultBean<java.lang.Boolean>
     */
    @PutMapping("/order/{orderId}")
    public  ResultBean<Boolean> updateOrder(@PathVariable Integer orderId){
        if(orderService.selectOrderByOrderId(orderId) == null){
            return new ResultBean<>("要结算的订单不存在！");
        }
        Order order1 = orderService.selectOrderByOrderId(orderId);
        if(order1.getOrderStatus() == 2){
            return new ResultBean<>("订单已支付，请勿重复支付！");
        }
        try{
            //支付
            User user = userService.getUserById(order1.getUserId());
            BigDecimal userMoney = user.getUserMoney();
            BigDecimal orderMoney = order1.getOrderMoney();
            if(userMoney.subtract(orderMoney).doubleValue() < 0){
                return new ResultBean<>("用户余额不足！");
            }
            //更改订单状态
            Order order = new Order();
            order.setOrderId(orderId);
            order.setOrderStatus(Byte.valueOf("2"));
            orderService.updateOrder(order);
            if(order1.getOrderType() == 1){
                //减少库存
                ProductLibraryStandard productLibraryStandard = productLibraryStandardService.selectById(order1.getProductStdId());
                productLibraryStandard.setStock(productLibraryStandard.getStock() - order1.getOrderMoney().intValue());
                productLibraryStandardService.updateProductStandard(productLibraryStandard);
                user.setUserMoney(userMoney.subtract(orderMoney));
                userService.updateUser(user);
                //放入个人产品库中
                ProductUser productUser = new ProductUser();
                productUser.setProductUserType(order1.getOrderType());
                productUser.setProductStdId(order1.getProductStdId());
                productUser.setUserId(order1.getUserId());
                //productUser.setTermDate();
                productUser.setPayMoney(order1.getOrderMoney());
                productUserService.addProductUser(productUser);
                //资金变动记录
                LogMoney logMoney = new LogMoney();
                logMoney.setMoneyType(order1.getOrderType());
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
                user.setUserMoney(userMoney.subtract(orderMoney));
                userService.updateUser(user);
                //放入个人产品库中
                ProductUser productUser = new ProductUser();
                productUser.setProductUserType(order1.getOrderType());
                productUser.setProductConId(order1.getProductConId());
                productUser.setUserId(order1.getUserId());
                //productUser.setTermDate();
                productUser.setPayMoney(order1.getOrderMoney());
                productUserService.addProductUser(productUser);
                //资金变动记录
                LogMoney logMoney = new LogMoney();
                logMoney.setMoneyType(order1.getOrderType());
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


    @GetMapping("/orders")
    public ResultBean<List<OrderDto> > selectAllOrders(){
        List<OrderDto> orderDtoList = new ArrayList<>();
        try{
            List<Order> orderList = orderService.selectAll();
            for(int i=0;i<orderList.size();i++){
                Order order = orderList.get(i);
                OrderDto orderDto = new OrderDto();
                orderDto.setOrder(order);
                User user = userService.getUserById(order.getUserId());
                orderDto.setUserName(user.getUserName());
                if(order.getOrderType() == 1){
                    ProductLibraryStandard productLibraryStandard = productLibraryStandardService.selectById(order.getProductStdId());
                    ProductLibraryPre productLibraryPre = productLibraryPreService.selectById(productLibraryStandard.getProductPreId());
                    orderDto.setProductName(productLibraryPre.getProductName());
                    orderDto.setProductLibraryStandard(productLibraryStandard);
                }else if(order.getOrderType() == 2){
                    ProductLibraryConfiguration productLibraryConfiguration = productLibraryConfigurationService.selectById(order.getProductConId());
                    orderDto.setProductName(productLibraryConfiguration.getProductConName());
                    orderDto.setProductLibraryConfiguration(productLibraryConfiguration);
                }
                orderDtoList.add(orderDto);
            }
        }
        catch (Exception e){
            return new ResultBean<>(e);
        }
        return new ResultBean<>(orderDtoList);
    }
}
