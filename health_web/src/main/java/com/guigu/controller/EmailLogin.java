package com.guigu.controller;

import com.alibaba.fastjson.JSON;
import com.guigu.constant.MessageConstant;
import com.guigu.email.SendMail;
import com.guigu.entity.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller
@RequestMapping("/email")
public class EmailLogin {

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


}
