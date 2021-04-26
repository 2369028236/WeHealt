package com.guigu.service;

import com.guigu.entity.PageResult;
import com.guigu.entity.QueryPageBean;
import com.guigu.pojo.Menu;

import java.util.List;

/**
 * @author zhouwenkai
 * @create 2021/4/19  14:13
 */
public interface MenuService {
    PageResult findAll(QueryPageBean queryPageBean);

    void menuAdd(Menu menu);

    Menu menuUpdate(Integer id);

    void menuEdit(Menu menu);

    void menuDelete(Integer id) throws Exception;

    List<Menu> findByRoleId(int id);
}
