package com.guigu.service;

import com.guigu.pojo.User;
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
}
