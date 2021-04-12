package com.guigu.service;

import com.guigu.entity.PageResult;
import com.guigu.entity.QueryPageBean;
import com.guigu.exception.HealthException;
import com.guigu.pojo.CheckGroup;

import java.util.List;

public interface CheckGroupService {
    List<CheckGroup> getAllCheckGroup();
    /*** 添加检查组 * @param checkGroup * @param checkitemIds */
    void add(CheckGroup checkGroup, Integer[] checkitemIds);
    /*** 分页条件查询 * @param queryPageBean * @return */
    PageResult<CheckGroup> findPage(QueryPageBean queryPageBean);
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
     * 修改检查组
     * @param checkGroup
     * @param checkitemIds
     */
    void update(CheckGroup checkGroup, Integer[] checkitemIds);
    /**
     * 删除检查组
     * @param id
     */
    void deleteById(int id) throws HealthException;
    /**
     * 查询所有检查组
     * @return
     */
    List<CheckGroup> findAll();
}
