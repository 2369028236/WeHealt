package com.guigu.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONArray;
import com.guigu.pojo.User;
import com.guigu.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


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
                    return "/user/userMain";
                }else {
                    return "redirect:/login.html";
                }
            }
            return "redirect:/login.html";
        }
       User userList= userService.getUser(userName,passWord);
       if (userList!=null){
           session.setAttribute("user",userList);
           return "/user/userMain";
       }else {
           return "redirect:/login.html";
       }
    }

    @RequestMapping("logOut")
    public String logOut(HttpSession session){
        session.invalidate();
        return "redirect:/login.html";
    }

}
