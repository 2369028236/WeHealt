package com.guigu.mapper;

import com.guigu.pojo.Member;
import com.guigu.pojo.MemberExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MemberMapper {
    long countByExample(MemberExample example);

    int deleteByExample(MemberExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Member record);

    int insertSelective(Member record);

    List<Member> selectByExample(MemberExample example);

    Member selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Member record, @Param("example") MemberExample example);

    int updateByExample(@Param("record") Member record, @Param("example") MemberExample example);

    int updateByPrimaryKeySelective(Member record);

    int updateByPrimaryKey(Member record);

    List<Map<String, Object>> findSetmealCount();

    Integer findMemberCountBeforeDate(String date);

    int findMemberCountByDate(String date);

    int findMemberCountAfterDate(String date);

    int findMemberTotalCount();

}