package com.guigu.service;

import com.guigu.entity.PageResult;
import com.guigu.entity.QueryPageBean;
import com.guigu.exception.HealthException;
import com.guigu.pojo.SetMeal;

import java.util.List;

public interface SetmealService {
    /**
     * 添加套餐
     * @param setmeal
     * @param checkgroupIds
     */
    void add(SetMeal setmeal, Integer[] checkgroupIds);
    /**
     * 分页查询
     * @param queryPageBean
     * @return
     */
    PageResult<SetMeal> findPage(QueryPageBean queryPageBean);
    /**
     * 通过id查询
     * @param id
     * @return
     */
    SetMeal findById(int id);
    /**
     * 通过id查询选中的检查组ids
     * @param id
     * @return
     */
    List<Integer> findCheckgroupIdsBySetmealId(int id);
    /**
     * 修改套餐
     * @param setmeal
     * @param checkgroupIds
     */
    void update(SetMeal setmeal, Integer[] checkgroupIds);

    void deleteById(Integer id) throws HealthException;
    /**
     * 查出数据库中的所有图片
     * @return
     */
    List<String> findImgs();


}
