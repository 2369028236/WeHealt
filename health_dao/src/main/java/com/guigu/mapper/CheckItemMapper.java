package com.guigu.mapper;

import com.guigu.pojo.CheckItem;
import com.guigu.pojo.CheckItemExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CheckItemMapper {
    long countByExample(CheckItemExample example);

    int deleteByExample(CheckItemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CheckItem record);

    int insertSelective(CheckItem record);

    List<CheckItem> selectByExample(CheckItemExample example);

    CheckItem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CheckItem record, @Param("example") CheckItemExample example);

    int updateByExample(@Param("record") CheckItem record, @Param("example") CheckItemExample example);

    int updateByPrimaryKeySelective(CheckItem record);

    int updateByPrimaryKey(CheckItem record);

    List<CheckItem> getAllCheckItem(@Param("code") String code,@Param("name") String name);

    int findCountByCheckItemId(int id);

    CheckItem findById(int id);

    List<CheckItem> findAll();
}