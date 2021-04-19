package com.guigu.service.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.guigu.mapper.OrderMapper;
import com.guigu.pojo.Order;
import com.guigu.service.OrderService;

import javax.annotation.Resource;
@Service(interfaceClass = OrderService.class)
public class OrderServiceImpl implements OrderService {
    @Resource
   private OrderMapper orderMapper;
    @Override
    public void addOrder(Order order) {
        orderMapper.insertSelective(order);
    }
}
