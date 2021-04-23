package com.guigu.controller;

import com.alibaba.fastjson.JSON;
import com.guigu.email.SendMail;
import com.guigu.pojo.User;
import com.guigu.service.UserService;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller
@RequestMapping("/email")
public class EmailLogin {

@Resource
private UserService userService;
    /**
     * 发送邮箱
     */
    @ResponseBody
    @PostMapping("/getEmail")
    public String  showEmil(String email) {
        //随机生成4位整数为验证码
        Random r = new Random();
       int x = r.nextInt(9999 - 1000 + 1) + 1000;
        Map<String,Object> map=new HashMap<String, Object>();
        try {
            SendMail.sendMail(email, String.valueOf(x));
            map.put("flag",true);
            map.put("yzm",x);

        } catch (Exception e) {
            e.printStackTrace();
            map.put("flag",false);
        }
        String json= JSON.toJSONString(map);
        return json;
    }
    @ResponseBody
    @PostMapping("/login")
    public String doLogin(String username,String password,HttpSession session) {

        String email=username;
        User user = userService.emailShow(username,email,password);
        Map map=new HashMap();
        if (user!=null){
            session.setAttribute("user2",user);
            map.put("flag",true);
        }else {
            map.put("flag",false);
        }
        return JSON.toJSONString(map);
    }

    /**
     * 注册
     * @param email
     * @return
     */
    @ResponseBody
    @PostMapping("emailAdd")
    public Map email(String email){
        String password="123456";
        User user=userService.emailCha(email);
        Map<String,Object> map=new HashMap<>();
        if (user==null){
            int a=userService.emailAdd(email,password);
            if (a>0){
                map.put("flag",true);
            }else {
                map.put("flag",false);
            }
            return map;
        }else {
            map.put("flag",false);
        }


        return map;
    }

    /**
     * 先查询数据库是否有这邮箱
     * @param password
     * @param email
     * @return
     */
    @ResponseBody
    @PostMapping("emailUpdate")
    public Map emailUpdate(String password,String email){
        User user=userService.emailCha(email);
        Map<String,Object> map=new HashMap<String,Object>();
        if (user!=null){
            int a=userService.emailUpdate(password, email);
            if (a>0){
                map.put("flag",true);
                System.out.println("修改成功");
            }else {
                System.out.println(" 修改失败" );
                map.put("flag",false);
            }
        }else {
            map.put("flag",false);
        }
        return map;
    }

}
