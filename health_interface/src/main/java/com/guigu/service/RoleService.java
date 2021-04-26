package com.guigu.service;

import com.guigu.entity.PageResult;
import com.guigu.entity.QueryPageBean;
import com.guigu.pojo.Menu;
import com.guigu.pojo.Permission;
import com.guigu.pojo.Role;

import java.util.List;

/**
 * @author kangliehao
 * @create 2021/4/19  13:48
 */
public interface RoleService {
    PageResult findAll(QueryPageBean queryPageBean);

    void roleAdd(Role role, Integer[] menuIds, Integer[] permissionIds);



    void roleEdit(Role role, Integer[] menuIds, Integer[] permissionIds);

    void roleDelete(Integer id) throws Exception;

    List<Menu> findAllMenu();

    List<Permission> findAllPermission();

    /**
     * 回显数据Start
     * @param id
     * @return
     */
    Role roleUpdateOne(Integer id);

    List<Integer> roleUpdateTwo(Integer id);

    List<Integer> roleUpdateThree(Integer id);
    //  * 回显数据stop

    List<Role> findAll();
}
