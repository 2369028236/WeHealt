package com.guigu.controller;
import com.alibaba.dubbo.config.annotation.Reference;
import com.guigu.entity.Result;
import com.guigu.pojo.User;
import com.guigu.pojo.UserAndOrder;
import com.guigu.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping("logOut")
    public String logOut(HttpSession session){
        session.invalidate();
        return "redirect:/login.html";
    }

    @ResponseBody
    @PostMapping("/getAll")
    public Result getAll(HttpServletRequest request){
       HttpSession session= request.getSession();
        User user= (User) session.getAttribute("user2");
        if (user==null){
            return new Result(false,"个人信息查询失败，请重新登录后重试");
        }
       List<UserAndOrder>list= userService.getAll(user.getId());
       if (!list.isEmpty()){
           return new Result(true,"个人信息查询成功",list);
       }
       return new Result(false,"您没有预约信息",list);
    }
}
