package com.guigu.service.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.guigu.mapper.UserMapper;
import com.guigu.pojo.User;
import com.guigu.pojo.UserAndOrder;
import com.guigu.pojo.UserExample;
import com.guigu.service.UserService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional(rollbackFor = Exception.class)
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
    @Override
    public User emailShow(String username,String email,String password) {
        System.out.println("username = " + username);
        return userMapper.emailShow(username,email,password);
    }

    @Override
    public int emailAdd(String email, String password) {
        return userMapper.emailAdd(email,password);
    }

    @Override
    public User emailCha(String email) {
        return userMapper.emailCha(email);
    }

    @Override
    public int emailUpdate(String password, String email) {
        return userMapper.emailUpdate(password,email);
    }

    @Override
    public int addUserOrder(Integer userId, Integer orderId) {
        return userMapper.addUserOder(userId,orderId);
    }

    @Override
    public List<UserAndOrder> getAll(Integer userId) {
        return userMapper.getAll(userId);
    }

    @Override
    public int deleteByOrderId(Integer orderId) {
        return userMapper.deleteByOrderId(orderId);
    }
}
