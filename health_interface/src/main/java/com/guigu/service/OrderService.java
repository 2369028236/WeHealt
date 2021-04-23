package com.guigu.service;

import com.guigu.pojo.Order;

import java.util.List;

public interface OrderService {
    void addOrder(Order order);
    List<Order> getOrder(Integer id);
    Order getOrderSetmealID(Integer id);
    Order getOrderMemberID(Integer id);
    int deleteByPrimaryKey(Integer id);
}
