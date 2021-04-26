package com.guigu.service.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guigu.mapper.OrderMapper;
import com.guigu.pojo.Order;
import com.guigu.pojo.OrderAndMember;
import com.guigu.pojo.OrderExample;
import com.guigu.service.OrderService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public Map getAll(String queryStr, Integer pageIndex, Integer pageSize) {
        PageHelper.startPage(pageIndex,pageSize);
       List<OrderAndMember> list=orderMapper.getAll("%"+queryStr+"%");
        PageInfo pageInfo=new PageInfo(list);
        Map map=new HashMap();
        map.put("pageInfo",pageInfo);
        return map;
    }

    @Override
    public boolean updateById(Integer id) {
        Order order = orderMapper.selectByPrimaryKey(id);
        System.out.println("order："+order);
        if("未到诊".equals(order.getOrderStatus())){
            order.setOrderStatus("已到诊");
        }else {
            order.setOrderStatus("未到诊");
        }
        OrderExample orderExample = new OrderExample();
        orderExample.createCriteria().andIdEqualTo(id);
        return orderMapper.updateByExampleSelective(order,orderExample)>0?true:false;
    }
}
