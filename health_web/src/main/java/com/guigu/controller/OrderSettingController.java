package com.guigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;

import com.alibaba.fastjson.JSON;
import com.guigu.constant.MessageConstant;
import com.guigu.entity.Result;
import com.guigu.pojo.OrderSetting;
import com.guigu.pojo.OrderSettingExample;
import com.guigu.service.OrderSettingService;
import com.guigu.utils.POIUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.*;

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
    public String selectByExample(OrderSettingExample example){
        List<OrderSetting> orderSettingList= orderSettingService.selectByExample1(example);
        Map map=new HashMap();
        map.put("orderSettingList",orderSettingList);
        String stm= JSON.toJSONString(map);
        return stm;
    }

    @GetMapping("/getSetmealReport")
    public Result getSetmealReport(){
        // 调用服务查询 // 套餐数量
        List<Map<String,Object>> setmealCount = orderSettingService.findSetmealCount();
        // 套餐名称集合
        List<String> setmealNames = new ArrayList<String>();

        // [{name:,value}]
        // 抽取套餐名称
        if(null != setmealCount){
            for (Map<String, Object> map : setmealCount) {
                //map {name:,value}
                // 获取套餐的名称
                setmealNames.add((String) map.get("name"));
            }
        }
        // 封装返回的结果
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("setmealNames",setmealNames);
        resultMap.put("setmealCount",setmealCount);
        return new Result(true, MessageConstant.GET_SETMEAL_COUNT_REPORT_SUCCESS,resultMap);
    }

}
