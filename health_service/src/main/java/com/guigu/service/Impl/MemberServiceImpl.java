package com.guigu.service.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.guigu.exception.HealthException;
import com.guigu.mapper.MemberMapper;
import com.guigu.mapper.OrderSettingMapper;
import com.guigu.pojo.Member;
import com.guigu.pojo.MemberExample;
import com.guigu.pojo.OrderSetting;
import com.guigu.pojo.OrderSettingExample;
import com.guigu.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = MemberService.class)
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private OrderSettingMapper orderSettingMapper;

    @Override
    public List<OrderSetting> selectByExample1(OrderSettingExample example){

        return orderSettingMapper.selectByExample1(example);
    }

    @Override
    public List<Map<String, Object>> findSetmealCount(){

        return memberMapper.findSetmealCount();
    }


    @Override
    public void addMember(Member member) {
        memberMapper.insertSelective(member);
    }



    @Override
    public Member getMemberByIdCard(String IdCard) {
        MemberExample memberExample=new MemberExample();
        MemberExample.Criteria m=memberExample.createCriteria();
        m.andIdCardEqualTo(IdCard);
       List<Member> memberList= memberMapper.selectByExample(memberExample);
       if (memberList.isEmpty()){
           return null;
       }
        return memberList.get(memberList.size()-1);
    }

    @Override
    public Member getMemberById(Integer id) {
        return memberMapper.selectByPrimaryKey(id);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return memberMapper.deleteByPrimaryKey(id);
    }

}
