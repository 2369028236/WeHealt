package com.guigu.email;

import com.sun.mail.util.MailSSLSocketFactory;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *  邮件发送工具类
 */
public class SendMail {
                //发送第二封验证码邮件
         public static void sendMail(String to, String vcode) throws Exception{
            //设置发送邮件的主机  smtp.qq.com

            String host = "smtp.qq.com";

        //1.创建连接对象，连接到邮箱服务器

            Properties props = System.getProperties();

            //Properties 用来设置服务器地址，主机名 。。 可以省略

            //设置邮件服务器

            props.setProperty("mail.smtp.host", host);

             props.put("mail.smtp.auth", "true");

            //SSL加密

             MailSSLSocketFactory sf = new MailSSLSocketFactory();

            sf.setTrustAllHosts(true);

             props.put("mail.smtp.ssl.enable","true");

             props.put("mail.smtp.ssl.socketFactory", sf);

            //props：用来设置服务器地址，主机名；Authenticator：认证信息

            Session session = Session.getDefaultInstance(props,new Authenticator() {

                //通过密码认证信息

                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
            //new PasswordAuthentication(用户名, password);

            //这个用户名密码就可以登录到邮箱服务器了,用它给别人发送邮件

             return new PasswordAuthentication("2096854550@qq.com","kpxtwhcyxkcmehid");
        }
                    });
             try {
               Message message = new MimeMessage(session);

//2.1设置发件人：

            message.setFrom(new InternetAddress("2096854550@qq.com"));

 //2.2设置收件人 这个TO就是收件人

           message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));

 //2.3邮件的主题

            message.setSubject("来自注册网站验证码邮件");

 //2.4设置邮件的正文 第一个参数是邮件的正文内容 第二个参数是：是文本还是html的连接

             message.setContent("<h1>来自注册网站验证码邮件,请接收你的验证码：</h1><h3>你的验证码是："+vcode+"，请妥善保管好你的验证码！</h3>", "text/html;charset=UTF-8");

 //3.发送一封激活邮件

        Transport.send(message);

    }catch(MessagingException mex){
        mex.printStackTrace();

 }

}


}
