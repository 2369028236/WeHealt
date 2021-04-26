package com.guigu.service;

import com.guigu.exception.HealthException;
import com.guigu.pojo.OrderSetting;
import com.guigu.pojo.OrderSettingExample;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface OrderSettingService {
    /**
     * 批量导入
     * @param orderSettingList
     */
    void add(List<OrderSetting> orderSettingList) throws HealthException;

    List<Map> getOrderSettingByMonth(String date);
    // 通过日期修改可预约人数，这里要抛出自定义的异常
    void editNumberByDate(OrderSetting orderSetting) throws HealthException;

    void editReservationsByOrderDate(OrderSetting orderSetting) throws HealthException;

    List<OrderSetting> selectByExample(OrderSettingExample example) throws HealthException;

    OrderSetting getOrderSettingByDate(Date date);


    List<OrderSetting> selectByExample1(OrderSettingExample example) throws HealthException;

    List<Map<String, Object>> findSetmealCount();

}


