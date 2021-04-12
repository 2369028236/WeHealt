package com.guigu.service;

import com.github.pagehelper.PageInfo;
import com.guigu.exception.HealthException;
import com.guigu.pojo.CheckItem;

import java.util.List;


public interface CheckItemService {
    PageInfo getAllCheckItem(String queryCondition,Integer index,Integer pageSize);
    void add(CheckItem checkItem);
    void delete(Integer id) throws HealthException;
    CheckItem findById(int id);
    void update(CheckItem checkitem);
    List<CheckItem> findAll();
}
