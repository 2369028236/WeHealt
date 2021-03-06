package com.guigu.controller;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONArray;
import com.guigu.constant.MessageConstant;
import com.guigu.entity.PageResult;
import com.guigu.entity.QueryPageBean;
import com.guigu.entity.Result;
import com.guigu.pojo.User;
import com.guigu.pojo.UserAndOrder;
import com.guigu.service.UserService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController {
    @Reference
    UserService userService;

    @RequestMapping(value = "addUser",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String addUser(User user){
        boolean bool=userService.addUser(user);
        if (bool){
            return "redirect:/login.html";
        }else {
            return "redirect:/register.html";
        }

    }
    @RequestMapping("/doLogin")
    public String doLogin(String userName,String passWord,HttpSession session){
        if (userName==null||userName==""){
            if (passWord==null||passWord==""){
                if (session.getAttribute("user")!=null){
                    return "main";
                }else {
                    return "redirect:/login.html";
                }
            }
            return "redirect:/login.html";
        }
       User userList= userService.getUser(userName,passWord);
       if (userList!=null){
           session.setAttribute("user",userList);
           return "main";
       }else {
           return "redirect:/login.html";
       }
    }

    @RequestMapping("/logOut")
    public String logOut(HttpSession session){
        session.invalidate();
        return "redirect:/login.html";
    }

    /**
     * ?????????????????????
     */
    @GetMapping("/getUsername")
    @ResponseBody
    public Result getUsername(){
        // ?????????????????????????????????
        User loginUser = (User)
                SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // ???????????????
        String username = loginUser.getUsername();
        // ???????????????
        return new Result(true, MessageConstant.GET_USERNAME_SUCCESS,username);
    }

    @ResponseBody
    @PostMapping("/getAll")
    public Result getAll(HttpServletRequest request){
       HttpSession session= request.getSession();
        User user= (User) session.getAttribute("user2");
        if (user==null){
            return new Result(false,"???????????????????????????????????????????????????");
        }
       List<UserAndOrder>list= userService.getAll(user.getId());
       if (!list.isEmpty()){
           return new Result(true,"????????????????????????",list);
       }
       return new Result(false,"?????????????????????",list);
    }

    /**
     * ??????????????????
     * @param queryPageBean
     * @return
     */
    @PreAuthorize("hasAuthority('USER_QUERY')")
    @ResponseBody
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        //???????????????service???????????????
        PageResult pageResult =userService.findPage(queryPageBean);
        return pageResult;
    }

    /**
     * ????????????
     * @param user
     * @param roleIds
     * @return
     */
    @PreAuthorize("hasAuthority('USER_ADD')")
    @ResponseBody
    @RequestMapping("/add")
    public Result add(@RequestBody com.guigu.pojo.User user,Integer[] roleIds){
        try {
            //???????????????????????????
            BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
            String s = encoder.encode(user.getPassword());
            user.setPassword(s);
            userService.add(user,roleIds);
            return  new Result(true,MessageConstant.ADD_USER_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return  new Result(false,MessageConstant.ADD_USER_FAIL);
        }
    }

    /**
     * ???????????????id?????????????????????????????????
     * @param id
     * @return
     */
    @RequestMapping("/findById")
    @ResponseBody
    public Result findById(Integer id){
        try {
            //???id??????service
            com.guigu.pojo.User user= userService.findById(id);
            return new Result(true,MessageConstant.QUERY_USER_SUCCESS,user);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_USER_FAIL);
        }

    }

    @RequestMapping("/findRoleIdsByUserId")
    @ResponseBody
    public Result findRoleIdsByUserId(Integer id){
        try {
            List<Integer> roleIds=userService.findRoleIdsByUserId(id);
            System.out.println(roleIds);
            return  new Result(true,"",roleIds);
        } catch (Exception e) {
            e.printStackTrace();
            return  new Result(false,"");
        }

    }

    /**
     * ??????
     * @param user
     * @param roleIds
     * @return
     */
    @PreAuthorize("hasAuthority('USER_EDIT')")
    @ResponseBody
    @RequestMapping("/edit")
    public Result edit(@RequestBody com.guigu.pojo.User user,Integer[] roleIds){
        try {
            userService.edit(user,roleIds);
            return  new Result(true,MessageConstant.EDIT_USER_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return  new Result(false,MessageConstant.EDIT_USER_FAIL);
        }

    }

    /**
     * ??????????????????????????????????????????
     * ???????????????????????????
     * @param id
     * @return
     */
    @PreAuthorize("hasAuthority('USER_DELETE')")
    @ResponseBody
    @RequestMapping("/delete")
    public Result delete(Integer id){
        User loginUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // ???????????????
        String username2 = loginUser.getUsername();
        try {
            com.guigu.pojo.User username = userService.findByUsername(username2);
            System.out.println(username.getId());
            if(id.equals(username.getId())){
                return new Result(false,"????????????????????????????????????");
            }
            userService.delete(id);
            return new Result(true,MessageConstant.DELETE_USER_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(true,MessageConstant.DELETE_USER_FAIL);
        }
    }
}
