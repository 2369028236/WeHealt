package com.guigu.mapper;

import com.github.pagehelper.Page;
import com.guigu.pojo.User;
import com.guigu.pojo.UserAndOrder;
import com.guigu.pojo.UserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 根据登陆用户名称查询用户权限信息
     * @param username
     * @return
     */
    User findByUsername(String username);
    /**
     * 登陆 username
     * @param username
     * @return
     */
    User emailShow(@Param("username") String username,@Param("email") String email,@Param("password") String password);

    /**
     * 注册
     * @param email
     * @param password
     * @return
     */
    int emailAdd(@Param("email")String email,@Param("password") String password);

    /**
     * email 查
     * @param email
     * @return
     */
    User emailCha(@Param("email") String email);


    /**
     * 获取邮箱修改密码
     * @param password
     * @param email
     * @return
     */
    int emailUpdate(@Param("password")String password,@Param("email") String email);

    int addUserOder(@Param("userId") Integer userId,@Param("orderId") Integer orderId);

    List<UserAndOrder> getAll(Integer userId);
    int deleteByOrderId(Integer orderId);

    Page<User> findPage(String queryString);

    void add(User user);

    void setUserAndRole(Map<String, Integer> map);

    User findById(Integer id);

    void edit(User user);

    void deleteAssociation(Integer id);

    List<Integer> findRoleIdsByUserId(Integer id);

    void delete(Integer id);
}