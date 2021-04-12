package com.guigu.service.Impl;

import com.guigu.entity.Result;
import com.guigu.exception.HealthException;
import com.guigu.mapper.OrderSettingMapper;
import com.guigu.pojo.OrderSetting;
import com.guigu.service.OrderSettingService;
import com.sun.media.jfxmediaimpl.HostUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderSettingServiceImpl implements OrderSettingService {
    @Autowired
    private OrderSettingMapper orderSettingMapper;

    @Override
    @Transactional
    public void add(List<OrderSetting> orderSettingList) throws HealthException {
// 遍历
        for (OrderSetting orderSetting : orderSettingList) {
// 判断是否存在, 通过日期来查询, 注意：日期里是否有时分秒，数据库里的日期是没有时分秒的
            OrderSetting osInDB = orderSettingMapper.findByOrderDate(orderSetting.getOrderDate());
            if(null != osInDB){
// 数据库存在这个预约设置, 已预约数量不能大于可预约数量
                if(osInDB.getReservations() > orderSetting.getNumber()){
                    throw new HealthException(orderSetting.getOrderDate() + " 中已预约数量不能大于可预约数量");
                }
                orderSettingMapper.updateNumber(orderSetting);
            }else{
// 不存在
                orderSettingMapper.add(orderSetting);
            }
        }
    }
    //根据日期查询预约设置数据
    public List<Map> getOrderSettingByMonth(String date) {//2019-03
// 1.组织查询Map，dateBegin表示月份开始时间，dateEnd月份结束时间
        String dateBegin = date + "-1";//2019-03-1
        String dateEnd = date + "-31";//2019-03-31
        Map map = new HashMap();
        map.put("dateBegin",dateBegin);
        map.put("dateEnd",dateEnd);
// 2.查询当前月份的预约设置
        List<OrderSetting> list = orderSettingMapper.getOrderSettingByMonth(map);
        List<Map> data = new ArrayList<>();
// 3.将List<OrderSetting>，组织成List<Map>
        for (OrderSetting orderSetting : list) {
            Map orderSettingMap = new HashMap();
            orderSettingMap.put("date",orderSetting.getOrderDate().getDate());//获得日期（几号）
            orderSettingMap.put("number",orderSetting.getNumber());//可预约人数
            orderSettingMap.put("reservations",orderSetting.getReservations());//已预约人数
            data.add(orderSettingMap);
        }
        return data;
}
    /**
     * 通过日期设置预约信息
     * @param orderSetting
     */
    @Override
    public void editNumberByDate(OrderSetting orderSetting) {
//通过日期判断预约设置是否存在？
        System.out.println(orderSetting);
        OrderSetting os = orderSettingMapper.findByOrderDate(orderSetting.getOrderDate());
//- 存在：

        if(null != os) {
// 判断已经预约的人数是否大于要更新的最大可预约人数， reverations > 传进来的number数量，则不能更新，要报错
            if(orderSetting.getNumber() < os.getReservations()){
// 已经预约的人数高于最大预约人数，不允许
                throw new HealthException("最大预约人数不能小已预约人数！");
            }
// reverations <= 传进来的number数量，则要更新最大可预约数量
            orderSettingMapper.editNumberByOrderDate(orderSetting);
        }else {
//- 不存在：
// - 添加预约设置信息
            orderSettingMapper.add(orderSetting);
        }
    }

    @Override
    public void editReservationsByOrderDate(OrderSetting orderSetting) throws HealthException {
        //通过日期判断预约设置是否存在？
        OrderSetting os = orderSettingMapper.findByOrderDate(orderSetting.getOrderDate());
//- 存在：
        if(null != os) {
// 判断已经预约的人数是否大于要更新的最大可预约人数， reverations > 传进来的number数量，则不能更新，要报错
            if(orderSetting.getReservations() > os.getNumber()){
// 已经预约的人数高于最大预约人数，不允许
                throw new HealthException("预约人数不能超过最大预约人数！");
            }
// reverations <= 传进来的number数量，则要更新最大可预约数量
            orderSettingMapper.editReservationsByOrderDate(orderSetting);
        }else {
            throw new HealthException("预约人数不能超过最大预约人数！");
        }
    }
}
