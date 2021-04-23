package com.guigu.service;

import com.guigu.pojo.User;
import com.guigu.pojo.UserAndOrder;
import com.guigu.pojo.UserExample;

import javax.annotation.Resource;
import java.util.List;

public interface UserService {
    /**
     * 获取全部用户
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
}
