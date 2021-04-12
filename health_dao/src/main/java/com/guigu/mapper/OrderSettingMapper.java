package com.guigu.mapper;

import com.guigu.pojo.OrderSetting;
import com.guigu.pojo.OrderSettingExample;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface OrderSettingMapper {
    long countByExample(OrderSettingExample example);

    int deleteByExample(OrderSettingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrderSetting record);

    int insertSelective(OrderSetting record);

    List<OrderSetting> selectByExample(OrderSettingExample example);

    OrderSetting selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OrderSetting record, @Param("example") OrderSettingExample example);

    int updateByExample(@Param("record") OrderSetting record, @Param("example") OrderSettingExample example);

    int updateByPrimaryKeySelective(OrderSetting record);

    int updateByPrimaryKey(OrderSetting record);
    /**
     * 通过日期来查询预约设置
     * @param orderDate
     * @return
     */
    OrderSetting findByOrderDate(Date orderDate);
    /**
     * 更新可预约数量
     * @param orderSetting
     */
    void updateNumber(OrderSetting orderSetting);
    /**
     * 添加预约设置
     * @param orderSetting
     */
    void add(OrderSetting orderSetting);
    List<OrderSetting> getOrderSettingByMonth(Map map);

    void editNumberByOrderDate(OrderSetting orderSetting);

    void editReservationsByOrderDate(OrderSetting orderSetting);
}