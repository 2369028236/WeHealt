package com.guigu.service;

import com.guigu.pojo.Member;
import com.guigu.pojo.MemberExample;

import java.util.List;

public interface MemberService {
    void addMember(Member member);
    Member getMember(String phone);
    Member getMemberByIdCard(String IdCard);
}
