package com.guigu.service.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.guigu.mapper.UserMapper;
import com.guigu.pojo.User;
import com.guigu.pojo.UserExample;
import com.guigu.service.UserService;

import javax.annotation.Resource;
import java.util.List;

@Service(interfaceClass = UserService.class)
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

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
        return userMapper.insertSelective(user)>0?true:false;
    }
}
