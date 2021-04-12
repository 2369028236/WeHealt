package com.guigu.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.guigu.entity.PageResult;
import com.guigu.entity.QueryPageBean;
import com.guigu.exception.HealthException;
import com.guigu.mapper.SetMealMapper;
import com.guigu.pojo.SetMeal;
import com.guigu.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

public class SetmealServiceImpl implements SetmealService {
    @Autowired
    private SetMealMapper setMealMapper;
    @Override
    @Transactional
    public void add(SetMeal setmeal, Integer[] checkgroupIds) {
    // 添加套餐信息
        setMealMapper.add(setmeal);
    // 获取套餐的id
        Integer setmealId = setmeal.getId();
    // 添加套餐与检查组的关系
        if(null != checkgroupIds){
            for (Integer checkgroupId : checkgroupIds) {
                setMealMapper.addSetmealCheckGroup(setmealId, checkgroupId);
            }
        }
    }
    /**
     * 分页查询
     * @param queryPageBean
     * @return
     */
    @Override
    public PageResult<SetMeal> findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
// 查询条件
        if(!StringUtils.isEmpty(queryPageBean.getQueryString())){
// 模糊查询 %
            queryPageBean.setQueryString("%" + queryPageBean.getQueryString() + "%");
        }
// 条件查询，这个查询语句会被分页
        Page<SetMeal> page = setMealMapper.findByCondition(queryPageBean.getQueryString());
        return new PageResult<SetMeal>(page.getTotal(), page.getResult());
    }

    @Override
    public SetMeal findById(int id) {
        return setMealMapper.findById(id);
    }
    /**
     * 通过id查询选中的检查组ids
     * @param id
     * @return
     */
    @Override
    public List<Integer> findCheckgroupIdsBySetmealId(int id) {
        return setMealMapper.findCheckgroupIdsBySetmealId(id);
    }
    /**
     * 修改套餐
     * @param setmeal
     * @param checkgroupIds
     */
    @Override
    @Transactional
    public void update(SetMeal setmeal, Integer[] checkgroupIds) {
// 先更新套餐信息
        setMealMapper.update(setmeal);
// 删除旧关系
        setMealMapper.deleteSetmealCheckGroup(setmeal.getId());
// 添加新关系
        if(null != checkgroupIds){
            for (Integer checkgroupId : checkgroupIds) {
                setMealMapper.addSetmealCheckGroup(setmeal.getId(), checkgroupId);
            }
        }
    }
    // 删除套餐
    @Override
    @Transactional
    public void deleteById(Integer id) throws HealthException {
// 是否存在订单，如果存在则不能删除
        int count = setMealMapper.findOrderCountBySetmealId(id);
        if(count > 0){
// 已经有订单使用了这个套餐，不能删除
            throw new HealthException("已经有订单使用了这个套餐，不能删除！");
        }
// 先删除套餐与检查组的关系
        setMealMapper.deleteSetmealCheckGroup(id);
// 再删除套餐
        setMealMapper.deleteById(id);
    }
    /**
     * 查出数据库中的所有图片
     * @return
     */
    @Override
    public List<String> findImgs() {
        return setMealMapper.findImgs();
    }
}
