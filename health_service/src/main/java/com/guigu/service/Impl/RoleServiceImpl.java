package com.guigu.service.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.guigu .entity.PageResult;
import com.guigu .entity.QueryPageBean;
import com.guigu .mapper.MenuMapper;
import com.guigu .mapper.PermissionMapper;
import com.guigu .mapper.RoleMapper;
import com.guigu .pojo.Menu;
import com.guigu .pojo.Permission;
import com.guigu .pojo.Role;
import com.guigu .service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author kangliehao
 * @create 2021/4/19  13:47
 */
@Service(interfaceClass = RoleService.class)
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleMapper roleMapper;
    @Autowired
    MenuMapper menuMapper;
    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public PageResult findAll(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        Page<Role> page = roleMapper.findAll(queryPageBean.getQueryString());
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public void roleAdd(Role role,Integer[] menuIds,Integer[] permissionIds) {
        //1.添加role
        roleMapper.roleAdd(role);
        //获取role的ID
        Integer roleId = role.getId();
        //通过t_role_menu  t_role_permission 完成添加
        for (Integer menuId : menuIds) {
            roleMapper.roleMenu(roleId,menuId);
        }
        for (Integer permissionId : permissionIds) {
            roleMapper.rolePermission(roleId,permissionId);
        }
    }


    /**
     * 更新数据
     * @param role
     * @param menuIds
     * @param permissionIds
     */
    @Override
    public void roleEdit(Role role,Integer[] menuIds,Integer[] permissionIds) {
        roleMapper.roleEdit(role);
        //先删除roleID对应的数据在重新添加
        roleMapper.roleDeleteMenu(role.getId());
        roleMapper.roleDeletePermission(role.getId());
        for (Integer menuId : menuIds) {
            roleMapper.roleMenu(role.getId(),menuId);
        }
        for (Integer permissionId : permissionIds) {
            roleMapper.rolePermission(role.getId(),permissionId);
        }
    }

    @Override
    public void roleDelete(Integer id) throws Exception {
        //判断该id是否引用其他表
        Integer RoleMenuID = roleMapper.findCountRoleMenu();
        Integer RoleUserID = roleMapper.findCountRoleUser();
        Integer RolePermissionID = roleMapper.findCountRolePermission();
        if (!(RoleMenuID ==0&&RoleUserID==0&&RolePermissionID==0)){
            throw new Exception("要删除数据被使用,无法删除");
        }
        roleMapper.roleDelete(id);
    }


    //----Start查询所有套餐和权限
    @Override
    public List<Menu> findAllMenu() {

        return menuMapper.findAllMenu();
    }

    @Override
    public List<Permission> findAllPermission() {

        return permissionMapper.findAllPermission();
    }
    //----end

    //回显数据start
    @Override
    public Role roleUpdateOne(Integer id) {

        return roleMapper.roleUpdateOne(id);
    }

    @Override
    public List<Integer> roleUpdateTwo(Integer id) {

        return roleMapper.roleUpdateTwo(id);
    }

    @Override
    public List<Integer> roleUpdateThree(Integer id) {
        return roleMapper.roleUpdateThree(id);
    }
    //回显数据stop


    @Override
    public List<Role> findAll() {
        List<Role> roleList = roleMapper.findAll4User();
        return roleList;
    }
}
