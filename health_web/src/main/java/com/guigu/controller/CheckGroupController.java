package com.guigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.guigu.constant.MessageConstant;
import com.guigu.entity.PageResult;
import com.guigu.entity.QueryPageBean;
import com.guigu.pojo.CheckGroup;
import com.guigu.entity.Result;
import com.guigu.service.CheckGroupService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/checkgroup")
public class CheckGroupController {

    @Reference
   private CheckGroupService checkGroupService;

    @ResponseBody
    @RequestMapping(value = "getAllCheckGroup")
    @PreAuthorize("hasAuthority('CHECKGROUP_QUERY')")
    public Object getAllCheckGroup(){
      List<CheckGroup> list= checkGroupService.getAllCheckGroup();
      if (!list.isEmpty()){
          String s = JSONObject.toJSONString(list);
          return s;
      }else {
          return null;
      }
    }

    /*** 添加检查组 * @param checkGroup * @param checkitemIds * @return */
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('CHECKGROUP_ADD')")
    public Result add(@RequestBody CheckGroup checkGroup, Integer[] checkitemIds) { // 调用业务服务
        checkGroupService.add(checkGroup, checkitemIds);
        // 响应结果
        return new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);
    }
    /*** 分页条件查询 */
    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean) {
        // 调用业务查询
        PageResult<CheckGroup> pageResult = checkGroupService.findPage(queryPageBean);
        return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS, pageResult);
    }
    /**
     * 通过id获取检查组
     */
    @GetMapping("/findById")
    public Result findById(int checkGroupId){
// 调用业务服务
        CheckGroup checkGroup = checkGroupService.findById(checkGroupId);
        return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,checkGroup);
    }
    /**
     * 通过检查组id查询选中的检查项id
     */
    @GetMapping("/findCheckItemIdsByCheckGroupId")
    public Result findCheckItemIdsByCheckGroupId(int checkGroupId){
        // 调用服务查询
        List<Integer> checkItemIds = checkGroupService.findCheckItemIdsByCheckGroupId(checkGroupId);
        return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,checkItemIds);
    }
    /**
     * 修改提交
     */
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('CHECKGROUP_EDIT')")
    public Result update(@RequestBody CheckGroup checkGroup, Integer[] checkitemIds){
// 调用业务服务
        checkGroupService.update(checkGroup, checkitemIds);
// 响应结果
        return new Result(true, MessageConstant.EDIT_CHECKGROUP_SUCCESS);
    }
    /**
     * 删除检查组
     * @param id
     * @return
     */
    @PostMapping("/deleteById")
    @PreAuthorize("hasAuthority('CHECKGROUP_DELETE')")
    public Result deleteById(int id){
//调用业务服务删除
        checkGroupService.deleteById(id);
        return new Result(true, MessageConstant.DELETE_CHECKGROUP_SUCCESS);
    }

    @GetMapping("/findAll")
    public Result findAll(){
        List<CheckGroup> all = checkGroupService.findAll();
        return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,all);
    }

}
