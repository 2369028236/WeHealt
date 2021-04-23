package com.guigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.guigu.entity.Result;
import com.guigu.pojo.Member;
import com.guigu.service.MemberService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;


@RestController
@RequestMapping("/member")
public class MemberController {
    @Reference
    private MemberService memberService;
    @PostMapping("/getMember")
    public Result getMembre(HttpServletRequest request) throws IOException {
        Map map = JSON.parseObject(request.getInputStream(),Map.class);
        Integer id=Integer.valueOf(String.valueOf(map.get("id")));
        System.out.println(id);
       Member member= memberService.getMemberById(id);
        System.out.println(member);
        return new Result(true,"",member);
    }
}
