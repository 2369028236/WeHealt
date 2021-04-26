package com.guigu.service;

import com.guigu.pojo.Order;
import com.guigu.pojo.OrderAndMember;

import java.util.List;
import java.util.Map;

public interface OrderService {
    void addOrder(Order order);
    List<Order> getOrder(Integer id);
    Order getOrderSetmealID(Integer id);
    Order getOrderMemberID(Integer id);
    int deleteByPrimaryKey(Integer id);
    Map getAll(String queryStr, Integer pageIndex, Integer pageSize);
    /**
     * 根据id修改预约列表状态
     * @param id
     * @return
     */
    boolean updateById(Integer id);
}
