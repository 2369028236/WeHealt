package com.guigu.mapper;

import com.github.pagehelper.Page;
import com.guigu.pojo.SetMeal;
import com.guigu.pojo.SetMealExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SetMealMapper {
    long countByExample(SetMealExample example);

    int deleteByExample(SetMealExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SetMeal record);

    int insertSelective(SetMeal record);

    List<SetMeal> selectByExample(SetMealExample example);

    SetMeal selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SetMeal record, @Param("example") SetMealExample example);

    int updateByExample(@Param("record") SetMeal record, @Param("example") SetMealExample example);

    int updateByPrimaryKeySelective(SetMeal record);

    int updateByPrimaryKey(SetMeal record);

    /**
     * 添加套餐
     * @param setmeal
     */
    void add(SetMeal setmeal);
    /**
     * 添加套餐与检查组的关系
     * @param setmealId
     * @param checkgroupId
     */
    void addSetmealCheckGroup(@Param("setmealId") Integer setmealId, @Param("checkgroupId")
            Integer checkgroupId);
    /**
     * 条件查询
     * @param queryString
     * @return
     */
    Page<SetMeal> findByCondition(String queryString);
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
     * 更新套餐信息
     * @param setmeal
     */
    void update(SetMeal setmeal);
    /**
     * 删除旧关系
     * @param id
     */
    void deleteSetmealCheckGroup(Integer id);
    /**
     * 通过套餐的id查询使用了这个套餐的订单个数
     * @param id
     * @return
     */
    int findOrderCountBySetmealId(int id);
    /**
     * 通过id删除套餐信息
     * @param id
     */
    void deleteById(int id);
    /**
     * 查数据中套餐的所有图片
     * @return
     */
    List<String> findImgs();
}