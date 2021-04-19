package com.guigu.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.guigu.constant.MessageConstant;
import com.guigu.entity.Result;
import com.guigu.pojo.*;
import com.guigu.service.MemberService;
import com.guigu.service.OrderService;
import com.guigu.service.OrderSettingService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Reference
    private OrderService orderService;
    @Reference
    private OrderSettingService orderSettingService;
    @Reference
    private MemberService memberService;

    @PostMapping("/add")
    public Result addOrder(HttpServletRequest request) throws IOException, ParseException {
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
        Date date2= new Date();
        Member member=new Member();
        Map map = JSON.parseObject(request.getInputStream(),Map.class);
        member.setName((String) map.get("name"));
        member.setSex((String) map.get("sex"));
        member.setIdCard((String) map.get("idCard"));
        member.setPhoneNumber((String) map.get("phoneNumber"));
        member.setRegTime(ft.parse(String.valueOf(map.get("regTime"))));
        member.setEmail((String) map.get("email"));
        if (memberService.getMemberByIdCard(member.getIdCard())==null){
            return new Result(false, MessageConstant.HAS_ORDERED);
        }
      OrderSetting orderSetting= orderSettingService.getOrderSettingByDate(member.getRegTime());
       if (orderSetting!=null){
           if (orderSetting.getNumber()==orderSetting.getReservations()){
               return new Result(false, MessageConstant.ORDER_FULL);
           }
           date2 = member.getRegTime();
           System.out.println(date2);
           if(dNow.getTime()-date2.getTime()>=0){
                return new Result(false, MessageConstant.SELECTED_DATE_CANNOT_ORDER);
           }else{
               memberService.addMember(member);
               Member member1=memberService.getMember(member.getPhoneNumber());
               System.out.println("member1"+member1);
               Order order=new Order();
               order.setMember_id(member1.getId());
               order.setOrderDate(date2);
               order.setSetmeal_id((Integer) map.get("Setmeal_id"));
               orderService.addOrder(order);
               orderSetting.setReservations(orderSetting.getReservations()+1);
               orderSettingService.editReservationsByOrderDate(orderSetting);
               return new Result(true, MessageConstant.ORDER_SUCCESS);
           }
       }else {
           return new Result(false, MessageConstant.SELECTED_DATE_CANNOT_ORDER);
       }

    }
}
