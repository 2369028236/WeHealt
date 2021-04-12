package com.guigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.guigu.constant.MessageConstant;
import com.guigu.pojo.CheckItem;
import com.guigu.entity.Result;
import com.guigu.service.CheckItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("checkItem")
public class CheckItemController {

    @Reference
    private CheckItemService checkItemService;

    @ResponseBody
    @RequestMapping("/add")
    public Result add(@RequestBody CheckItem checkItem) {
        checkItemService.add(checkItem);
        return new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS);
    }

    @ResponseBody
    @RequestMapping("/getAllCheckItem")
    public Object getAllCheckItem(Integer index,Integer pageSize,String queryCondition){
        if (index==null){
            index=1;
        }
        if (pageSize==null){
            pageSize=5;
        }
        PageInfo pageInfo=checkItemService.getAllCheckItem(queryCondition,index,pageSize);
        boolean flag=false;
        Result result=new Result();
       if (!pageInfo.getList().isEmpty()){
            flag=true;
            result.setFlag(flag);
            result.setMessage(MessageConstant.QUERY_CHECKITEM_SUCCESS);
            result.setData(pageInfo);
       }else {
           result.setFlag(flag);
           result.setMessage(MessageConstant.QUERY_CHECKITEM_FAIL);
           result.setData(pageInfo);
       }
        return result;
    }

    @PostMapping("/deleteById")
    public Result deleteById(int id) {
        //调用业务服务
    try {
            checkItemService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //响应结果
    return new Result(true, MessageConstant.DELETE_CHECKITEM_SUCCESS);
    }

    @GetMapping("/findById")
    public Result findById(int id) {
        CheckItem checkItem = checkItemService.findById(id);
        return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS, checkItem);
    }
    @ResponseBody
    @PostMapping("/update")
    public Result update(@RequestBody CheckItem checkitem){
        checkItemService.update(checkitem);
        return new Result(true, MessageConstant.EDIT_CHECKITEM_SUCCESS);
    }
    @GetMapping("/findAll")
    public Result findAll() { // 调用服务查询所有的检查项
        List<CheckItem> list = checkItemService.findAll();
        // 封装返回的结果
        return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,list); }
}
