package com.guigu.mapper;

import com.github.pagehelper.Page;
import com.guigu.pojo.Menu;
import com.guigu.pojo.MenuExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuMapper {
    long countByExample(MenuExample example);

    int deleteByExample(MenuExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    int insertSelective(Menu record);

    List<Menu> selectByExample(MenuExample example);

    Menu selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Menu record, @Param("example") MenuExample example);

    int updateByExample(@Param("record") Menu record, @Param("example") MenuExample example);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    List<Menu> findAllMenu();

    Page<Menu> findAll(String queryString);

    void menuAdd(Menu menu);

    Menu menuUpdate(Integer id);

    void menuEdit(Menu menu);

    void menuDelete(Integer id);

    Integer menuDeletRole(Integer id);

    List<Menu> findByRoleId(int id);
}