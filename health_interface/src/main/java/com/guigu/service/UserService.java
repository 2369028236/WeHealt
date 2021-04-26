package com.guigu.service;

import com.guigu.entity.PageResult;
import com.guigu.entity.QueryPageBean;
import com.guigu.pojo.User;
import com.guigu.pojo.UserAndOrder;
import java.util.List;

public interface UserService {
    /**
     * 获取用户
     * @return
     */
    public User getUser(String userName,String password);

    /**
     * 添加用户
     * @param user
     * @return
     */
    public boolean addUser(User user);

    /**
     * 根据登陆用户名称查询用户权限信息
     * @param username *
     * @return
     */
    User findByUsername(String username);
    /**
     * 登陆 username
     * @param username
     * @return
     */
    User emailShow(String username,String email,String password);

    /**
     * 注册
     * @param email
     * @param password
     * @return
     */
    int emailAdd(String email,String password);

    /**
     * email 邮箱查询
     * @param email
     * @return
     */
    User emailCha(String email);


    /**
     * email 修改 密码
     * @param password
     * @param email
     * @return
     */
    int emailUpdate(String password,String email);

    int addUserOrder(Integer userId,Integer orderId);
    List<UserAndOrder> getAll(Integer userId);
    int deleteByOrderId(Integer orderId);
    PageResult findPage(QueryPageBean queryPageBean);

    void add(User user, Integer[] roleIds);


    User findById(Integer id);

    void edit(User user, Integer[] roleIds);

    List<Integer> findRoleIdsByUserId(Integer id);

    void delete(Integer id);
}
