package com.guigu.service.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.guigu.mapper.MemberMapper;
import com.guigu.pojo.Member;
import com.guigu.pojo.MemberExample;
import com.guigu.service.MemberService;

import javax.annotation.Resource;

@Service(interfaceClass = MemberService.class)
public class MemberServiceImpl implements MemberService {
    @Resource
    private MemberMapper memberMapper;
    @Override
    public void addMember(Member member) {
        memberMapper.insertSelective(member);
    }

    @Override
    public Member getMember(String phone) {
        MemberExample memberExample=new MemberExample();
        MemberExample.Criteria m=memberExample.createCriteria();
        m.andPhoneNumberEqualTo(phone);
        return memberMapper.selectByExample(memberExample).get(0);
    }

    @Override
    public Member getMemberByIdCard(String IdCard) {
        MemberExample memberExample=new MemberExample();
        MemberExample.Criteria m=memberExample.createCriteria();
        m.andIdCardEqualTo(IdCard);
        return memberMapper.selectByExample(memberExample).get(0);
    }
}
