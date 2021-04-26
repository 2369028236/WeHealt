package com.guigu.service.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.guigu.entity.PageResult;
import com.guigu.entity.QueryPageBean;
import com.guigu.mapper.PermissionMapper;
import com.guigu.mapper.RoleMapper;
import com.guigu.mapper.UserMapper;
import com.guigu.pojo.User;
import com.guigu.pojo.UserAndOrder;
import com.guigu.pojo.UserExample;
import com.guigu.service.UserService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional(rollbackFor = Exception.class)
@Service(interfaceClass = UserService.class)
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleMapper roleMapper;

    @Override
    public User getUser(String userName, String password) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(userName);
        criteria.andPasswordEqualTo(password);
        return userMapper.selectByExample(example).get(0);
    }

    @Override
    public boolean addUser(User user) {
        return userMapper.insertSelective(user) > 0 ? true : false;
    }

    @Override
    public User findByUsername(String username) {
        System.out.println(username);
        return userMapper.findByUsername(username);
    }

    @Override
    public User emailShow(String username, String email, String password) {
        System.out.println("username = " + username);
        return userMapper.emailShow(username, email, password);
    }

    @Override
    public int emailAdd(String email, String password) {
        return userMapper.emailAdd(email, password);
    }

    @Override
    public User emailCha(String email) {
        return userMapper.emailCha(email);
    }

    @Override
    public int emailUpdate(String password, String email) {
        return userMapper.emailUpdate(password, email);
    }

    @Override
    public int addUserOrder(Integer userId, Integer orderId) {
        return userMapper.addUserOder(userId, orderId);
    }

    @Override
    public List<UserAndOrder> getAll(Integer userId) {
        return userMapper.getAll(userId);
    }

    @Override
    public int deleteByOrderId(Integer orderId) {
        return userMapper.deleteByOrderId(orderId);
    }


    @Resource
    private PermissionMapper permissionMapper;



    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        //1 调用分页组件
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        //2 调用CheckItemDao
        Page<User> page = userMapper.findPage(queryPageBean.getQueryString());
        //3 返回PageResult
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public void add(User user, Integer[] roleIds) {
        //添加用户并且返回对应的ID
        userMapper.add(user);
        System.out.println(user);
        //给用户添加对应的角色
        if (roleIds != null && roleIds.length>0) {
            saveUserAndRoleAssociation(roleIds,user.getId());
        }
    }

    @Override
    public User findById(Integer id) {
        User user=userMapper.findById(id);
        return user;
    }

    @Override
    public void edit(User user, Integer[] roleIds) {
        //添加用户并且返回对应的ID
        userMapper.edit(user);
        userMapper.deleteAssociation(user.getId());
        System.out.println(roleIds);
        //给用户添加对应的角色
        if (roleIds != null && roleIds.length>0) {
            saveUserAndRoleAssociation(roleIds,user.getId());
        }
    }

    @Override
    public List<Integer> findRoleIdsByUserId(Integer id) {
        return userMapper.findRoleIdsByUserId(id);
    }

    @Override
    public void delete(Integer id) {
        userMapper.deleteAssociation(id);
        userMapper.delete(id);
    }

    /*
     *
     *把对应的基本信息和对应的角色相关写入两张表的关联
     * userId //对应的数据的用户的id,
     * roleIds//对应的用户给予的角色
     * */
    private void saveUserAndRoleAssociation(Integer[] roleIds, int userId) {
        for (Integer roleId : roleIds) {
            Map<String, Integer> map = new HashMap<String, Integer>(2);
            map.put("user_id", userId);
            map.put("role_id", roleId);
            userMapper.setUserAndRole(map);
        }

    }
}
