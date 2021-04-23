package com.guigu.service.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.guigu.mapper.MemberMapper;
import com.guigu.pojo.Member;
import com.guigu.pojo.MemberExample;
import com.guigu.service.MemberService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
@Transactional(rollbackFor = Exception.class)
@Service(interfaceClass = MemberService.class)
public class MemberServiceImpl implements MemberService {
    @Resource
    private MemberMapper memberMapper;
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
