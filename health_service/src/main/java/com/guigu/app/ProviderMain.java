package com.guigu.app;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class  ProviderMain {
    public static void main(String[] args) throws IOException {
        new ClassPathXmlApplicationContext("classpath:applicationContext-service.xml");
      //阻塞进程,不然进程挂掉,因为挂掉之后,容器资源就释放了
       System.in.read();
    }
}
