package com.guigu.mapper;

import com.github.pagehelper.Page;
import com.guigu.pojo.Role;
import com.guigu.pojo.RoleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface RoleMapper {
    long countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    /**
     * 根据用户id查询该用户的角色信息
     * @param id
     * @return
     */
    Set<Role> findByUserId(Integer id);

    void roleAdd(Role role);

    void roleMenu(@Param("roleId") Integer roleId,@Param("menuId") Integer menuId);

    void rolePermission(@Param("roleId") Integer roleId,@Param("permissionId") Integer permissionId);

    Page<Role> findAll(String queryString);

    Role roleUpdateOne(Integer id);

    List<Integer> roleUpdateTwo(Integer id);

    List<Integer> roleUpdateThree(Integer id);

    void roleEdit(Role role);

    void roleDeleteMenu(Integer id);

    void roleDeletePermission(Integer id);

    Integer findCountRoleMenu();

    Integer findCountRoleUser();

    Integer findCountRolePermission();

    void roleDelete(Integer id);

    List<Role> findAll4User();
}