package com.guigu.mapper;

import com.github.pagehelper.Page;
import com.guigu.pojo.CheckGroup;
import com.guigu.pojo.CheckGroupExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CheckGroupMapper {
    long countByExample(CheckGroupExample example);

    int deleteByExample(CheckGroupExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CheckGroup record);

    int insertSelective(CheckGroup record);

    List<CheckGroup> selectByExample(CheckGroupExample example);

    CheckGroup selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CheckGroup record, @Param("example") CheckGroupExample example);

    int updateByExample(@Param("record") CheckGroup record, @Param("example") CheckGroupExample example);

    int updateByPrimaryKeySelective(CheckGroup record);

    int updateByPrimaryKey(CheckGroup record);
    /*** 添加检查组 * @param checkGroup */
    void add(CheckGroup checkGroup);
    /*** 添加检查组与检查项的关系 * @param checkGroupId 注意要取别名，类型相同 * @param checkitemId */
    void addCheckGroupCheckItem(@Param("checkGroupId") Integer checkGroupId, @Param("checkitemId") Integer checkitemId);
    Page<CheckGroup> findByCondition(String queryString);
    /**
     * 通过检查组id查询选中的检查项id
     * @param checkGroupId
     * @return
     */
    List<Integer> findCheckItemIdsByCheckGroupId(int checkGroupId);
/**
 * 通过id获取检查组
 * @param checkGroupId
 * @return
 */
    CheckGroup findById(int checkGroupId);
    /**
     * 更新检查组
     * @param checkGroup
     */
    void update(CheckGroup checkGroup);
    /**
     * 删除检查组与检查项的关系
     * @param id
     */
    void deleteCheckGroupCheckItem(Integer id);
    /**
     * 通过检查组id查询是否被套餐使用了
     * @param id
     * @return
     */
    int findSetmealCountByCheckGroupId(int id);
    /**
     * 删除检查组
     * @param id
     */
    void deleteById(int id);
    /**
     * 查询所有检查组
     * @return
     */
    List<CheckGroup> findAll();

}