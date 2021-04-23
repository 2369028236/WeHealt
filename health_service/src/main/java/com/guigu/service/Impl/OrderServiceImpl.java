package com.guigu.service.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.guigu.mapper.OrderMapper;
import com.guigu.pojo.Order;
import com.guigu.pojo.OrderExample;
import com.guigu.service.OrderService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
@Transactional(rollbackFor = Exception.class)
@Service(interfaceClass = OrderService.class)
public class OrderServiceImpl implements OrderService {
    @Resource
   private OrderMapper orderMapper;
    @Override
    public void addOrder(Order order) {
        orderMapper.insertSelective(order);
    }

    @Override
    public List<Order> getOrder(Integer id) {
        OrderExample orderExample=new OrderExample();
        OrderExample.Criteria criteria=orderExample.createCriteria();
        criteria.andIdEqualTo(id);
        List<Order> list= orderMapper.selectByExample(orderExample);
        System.out.println(list);
        if (list.isEmpty()){
            return null;
        }
        return list;
    }

    @Override
    public Order getOrderSetmealID(Integer id) {
        OrderExample orderExample=new OrderExample();
        OrderExample.Criteria criteria=orderExample.createCriteria();
        criteria.andSetmeal_idEqualTo(id);
       List<Order> list= orderMapper.selectByExample(orderExample);
        if (list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public Order getOrderMemberID(Integer id) {
        OrderExample orderExample=new OrderExample();
        OrderExample.Criteria criteria=orderExample.createCriteria();
        criteria.andMember_idEqualTo(id);
        List<Order> list= orderMapper.selectByExample(orderExample);
        if (list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return orderMapper.deleteByPrimaryKey(id);
    }
}
