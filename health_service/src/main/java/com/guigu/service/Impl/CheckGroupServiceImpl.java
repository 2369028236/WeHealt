package com.guigu.service.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.guigu.constant.MessageConstant;
import com.guigu.entity.PageResult;
import com.guigu.entity.QueryPageBean;
import com.guigu.exception.HealthException;
import com.guigu.mapper.CheckGroupMapper;
import com.guigu.pojo.CheckGroup;
import com.guigu.pojo.CheckGroupExample;
import com.guigu.service.CheckGroupService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service(interfaceClass = CheckGroupService.class)
public class CheckGroupServiceImpl implements CheckGroupService {
    @Resource
    private CheckGroupMapper checkgroupMapper;
    @Override
    public List<CheckGroup> getAllCheckGroup() {
        return checkgroupMapper.selectByExample(new CheckGroupExample());
    }

    @Override
    @Transactional
    public void add(CheckGroup checkGroup, Integer[] checkitemIds) {
        // 添加检查组
        checkgroupMapper.add(checkGroup);
        // 获取检查组的id
        Integer checkGroupId = checkGroup.getId();
        // 遍历检查项id, 添加检查组与检查项的关系
        if(null != checkitemIds){
            // 有钩选
            for (Integer checkitemId : checkitemIds) {
                //添加检查组与检查项的关系
                checkgroupMapper.addCheckGroupCheckItem(checkGroupId, checkitemId);
            }
        }
    }

    @Override
    public PageResult<CheckGroup> findPage(QueryPageBean queryPageBean) {
        // 使用PageHelper.startPage
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        // 有查询条件的处理, 模糊查询
        if(!StringUtils.isEmpty(queryPageBean.getQueryString())){
        // 拼接%
            queryPageBean.setQueryString("%" + queryPageBean.getQueryString()+ "%");
        }
        // 紧接着的查询会被分页
        Page<CheckGroup> page = checkgroupMapper.findByCondition(queryPageBean.getQueryString());
        return new PageResult<CheckGroup>(page.getTotal(), page.getResult());

    }

    @Override
    public List<Integer> findCheckItemIdsByCheckGroupId(int checkGroupId) {
        return checkgroupMapper.findCheckItemIdsByCheckGroupId(checkGroupId);

    }

    @Override
    public CheckGroup findById(int checkGroupId) {
        return checkgroupMapper.findById(checkGroupId);

    }

    @Override
    public void update(CheckGroup checkGroup, Integer[] checkitemIds) {
        // 先更新检查组
        checkgroupMapper.update(checkGroup);
// 删除旧关系
        checkgroupMapper.deleteCheckGroupCheckItem(checkGroup.getId());
// 建立新关系
        if(null != checkitemIds){
            for (Integer checkitemId : checkitemIds) {
                checkgroupMapper.addCheckGroupCheckItem(checkGroup.getId(), checkitemId);
            }
        }

    }

    @Override
    @Transactional
    public void deleteById(int id) throws HealthException {
// 检查 这个检查组是否被套餐使用了
        int count = checkgroupMapper.findSetmealCountByCheckGroupId(id);
        if(count > 0){
// 被使用了
            throw new HealthException(MessageConstant.CHECKGROUP_IN_USE);
        }
// 没有被套餐使用,就可以删除数据
// 先删除检查组与检查项的关系
        checkgroupMapper.deleteCheckGroupCheckItem(id);
// 删除检查组
        checkgroupMapper.deleteById(id);

    }

    @Override
    public List<CheckGroup> findAll() {
        return checkgroupMapper.findAll();
    }
}
