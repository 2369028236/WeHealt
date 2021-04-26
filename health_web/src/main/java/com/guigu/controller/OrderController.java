package com.guigu.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guigu.constant.MessageConstant;
import com.guigu.entity.Result;
import com.guigu.pojo.*;
import com.guigu.service.MemberService;
import com.guigu.service.OrderService;
import com.guigu.service.OrderSettingService;
import com.guigu.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    @Reference
    private UserService userService;
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
        Member member2=memberService.getMemberByIdCard(member.getIdCard());
        if (member2!=null){
            date2=member2.getRegTime();
            if (dNow.getTime()-date2.getTime()<0){
                return new Result(false, MessageConstant.HAS_ORDERED);
            }
        }
      OrderSetting orderSetting= orderSettingService.getOrderSettingByDate(member.getRegTime());
       if (orderSetting!=null){
           if (orderSetting.getNumber()==orderSetting.getReservations()){
               return new Result(false, MessageConstant.ORDER_FULL);
           }
           date2 = member.getRegTime();
           if(dNow.getTime()-date2.getTime()>=0){
                return new Result(false, MessageConstant.SELECTED_DATE_CANNOT_ORDER);
           }else{
               memberService.addMember(member);
               Member member1=memberService.getMemberByIdCard(member.getIdCard());
               Order order=new Order();
               order.setMember_id(member1.getId());
               order.setOrderDate(date2);
               order.setOrderStatus("未到诊");
               orderService.addOrder(order);
               orderSetting.setReservations(Integer.valueOf(orderSetting.getReservations())+1);
               orderSettingService.editReservationsByOrderDate(orderSetting);
              Order list= orderService.getOrderMemberID(order.getMember_id());
             HttpSession session= request.getSession();
             User user= (User) session.getAttribute("user2");
             userService.addUserOrder(user.getId(),list.getId());
             return new Result(true, MessageConstant.ORDER_SUCCESS,list);
           }
       }else {
           return new Result(false, MessageConstant.SELECTED_DATE_CANNOT_ORDER);
       }
    }
    @PostMapping("/getOrder")
    public Result getOrder(HttpServletRequest request) throws IOException {
        Map map = JSON.parseObject(request.getInputStream(),Map.class);
        Integer id=Integer.valueOf(String.valueOf(map.get("id"))) ;
        List<Order> list=orderService.getOrder(id);
        return new Result(true,"",list);
    }

    @PostMapping("/deleteOrder")
    public Result deleteOrder(HttpServletRequest request) throws IOException {
        Map map = JSON.parseObject(request.getInputStream(),Map.class);
        Integer order_id=Integer.valueOf(String.valueOf(map.get("order_id"))) ;
        Integer member_id=Integer.valueOf(String.valueOf(map.get("member_id"))) ;
       if (userService.deleteByOrderId(order_id)>=0){
           if (orderService.deleteByPrimaryKey(order_id)>=0){
               memberService.deleteByPrimaryKey(member_id);
           }
       }
        return new Result(true,"已为你取消预约");
    }

    @PostMapping("/getAll")
    public Result getAll(HttpServletRequest request) throws IOException {
        Map map = JSON.parseObject(request.getInputStream(),Map.class);
        String queryStr=String.valueOf(map.get("queryStr"));
        Integer pageIndex=Integer.valueOf(String.valueOf(map.get("pageIndex")));
        Integer pageSize=Integer.valueOf(String.valueOf(map.get("pageSize"))) ;
       Map list= orderService.getAll(queryStr,pageIndex,pageSize);
       return  new Result(true,"",list);
    }
    @RequestMapping("/updateById")
    public Result updateById(@RequestParam("id") Integer id){
        Map<String,Object> map = new HashMap<>();
        map.put("flag",orderService.updateById(id));
        return new Result(true,"",map);
    }
}
