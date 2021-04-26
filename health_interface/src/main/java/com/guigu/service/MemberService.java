package com.guigu.service;

import com.guigu.exception.HealthException;
import com.guigu.pojo.Member;
import com.guigu.pojo.OrderSetting;
import com.guigu.pojo.OrderSettingExample;

import java.util.List;
import java.util.Map;


public interface MemberService {
    void addMember(Member member);
    Member getMemberByIdCard(String IdCard);
    Member getMemberById(Integer id);
    int deleteByPrimaryKey(Integer id);
    List<OrderSetting> selectByExample1(OrderSettingExample example) throws HealthException;

    List<Map<String, Object>> findSetmealCount() throws HealthException;
}
