package com.guigu.service.Impl;

import com.alibaba.dubbo.config.annotation.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guigu.constant.MessageConstant;
import com.guigu.exception.HealthException;
import com.guigu.mapper.CheckItemMapper;

import com.guigu.pojo.CheckItem;
import com.guigu.pojo.CheckItemExample;
import com.guigu.service.CheckItemService;
import com.mysql.jdbc.StringUtils;


import javax.annotation.Resource;
import java.util.List;

@Service(interfaceClass = CheckItemService.class)
public class CheckItemServiceImpl implements CheckItemService {
    @Resource
    private CheckItemMapper checkItemMapper;
    @Override
    public PageInfo getAllCheckItem(String queryCondition,Integer index,Integer pageSize) {
        List<CheckItem> list=null;
        if (StringUtils.isNullOrEmpty(queryCondition)||queryCondition.equals("null")){
            PageHelper.startPage(index,pageSize);
            list=checkItemMapper.selectByExample(new CheckItemExample());
            PageInfo pageInfo=new PageInfo(list);
            return pageInfo;
        }else {
            PageHelper.startPage(index,pageSize);
        list=checkItemMapper.getAllCheckItem("%"+queryCondition+"%","%"+queryCondition+"%");
            PageInfo pageInfo=new PageInfo(list);
        return pageInfo;
        }

    }

    @Override
    public void add(CheckItem checkItem) {
        checkItemMapper.insertSelective(checkItem);
    }

    @Override
    public void delete(Integer id) throws HealthException {
        int cnt = checkItemMapper.findCountByCheckItemId(id);
        if(cnt > 0){
            //??? health_web能捕获到这个异常吗？
            throw new HealthException(MessageConstant.CHECKITEM_IN_USE);
        }
        checkItemMapper.deleteByPrimaryKey(id);
    }

    @Override
    public CheckItem findById(int id) {
        return checkItemMapper.findById(id);
    }

    @Override
    public void update(CheckItem checkitem) {
        CheckItemExample checkItemExample=new CheckItemExample();
        CheckItemExample.Criteria criteria=checkItemExample.createCriteria();
        criteria.andIdEqualTo(checkitem.getId());
        checkItemMapper.updateByExampleSelective(checkitem,checkItemExample);
    }
    @Override
    public List<CheckItem> findAll() { return checkItemMapper.findAll(); }
}
