package com.guigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;

import com.guigu.constant.MessageConstant;
import com.guigu.entity.Result;
import com.guigu.pojo.OrderSetting;
import com.guigu.pojo.OrderSettingExample;
import com.guigu.service.OrderSettingService;
import com.guigu.utils.POIUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ordersetting")
public class OrderSettingController {
    @Reference
    private OrderSettingService orderSettingService;
    // 上传excel文件
    @PostMapping("/upload")
    public Result upload(MultipartFile excelFile){
        try {
// 读取excel内容
            List<String[]> strings = POIUtils.readExcel(excelFile);
// 转成List<OrderSetting>
            List<OrderSetting> orderSettingList = new ArrayList<OrderSetting>();
// 日期格式解析
            SimpleDateFormat sdf = new SimpleDateFormat(POIUtils.DATE_FORMAT);
            Date orderDate = null;
            OrderSetting os = null;
            for (String[] dataArr : strings) {
                orderDate = sdf.parse(dataArr[0]);
                int number = Integer.valueOf(dataArr[1]);
                os = new OrderSetting(orderDate,number);
                orderSettingList.add(os);
            }
// 调用业务服务
            orderSettingService.add(orderSettingList);
            return new Result(true, MessageConstant.IMPORT_ORDERSETTING_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.IMPORT_ORDERSETTING_FAIL);
        }
    }
    /**
     * 根据日期查询预约设置数据(获取指定日期所在月份的预约设置数据)
     * @param date
     * @return
     */
    @RequestMapping("/getOrderSettingByMonth")
    public Result getOrderSettingByMonth(String date){//参数格式为：2019-03
        try{
            List<Map> list = orderSettingService.getOrderSettingByMonth(date);
//获取预约设置数据成功
            return new Result(true,MessageConstant.GET_ORDERSETTING_SUCCESS,list);
        }catch (Exception e){
            e.printStackTrace();
//获取预约设置数据失败
            return new Result(false,MessageConstant.GET_ORDERSETTING_FAIL);
        }
    }
    /**
     * 基于日历的预约设置
     */
    @PostMapping("/editNumberByDate")
    public Result editNumberByDate(@RequestBody OrderSetting orderSetting){
// 调用服务更新
        orderSettingService.editNumberByDate(orderSetting);
        return new Result(true, MessageConstant.ORDERSETTING_SUCCESS);
    }
    /**
     * 基于日历的预约设置
     */
    @PostMapping("/editReservationsByOrderDate")
    public Result editReservationsByOrderDate(@RequestBody OrderSetting orderSetting){
// 调用服务更新
        orderSettingService.editReservationsByOrderDate(orderSetting);
        return new Result(true, MessageConstant.ORDERSETTING_SUCCESS);
    }
    @RequestMapping("/selectByExample")
    public List<OrderSetting> selectByExample(OrderSettingExample example){
        return orderSettingService.selectByExample(example);
    }
}
