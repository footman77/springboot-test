package cn.footman.task;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.internet.MimeMessage;
import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootTaskApplicationTests {

    @Autowired
    private JavaMailSenderImpl mailSender;

    @Test
    public void contextLoads() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        //设置简单邮件内容
        simpleMailMessage.setSubject("测试邮件");
        simpleMailMessage.setText("没有内容");

        simpleMailMessage.setTo("ykkwpp@163.com");
        simpleMailMessage.setFrom("1415632002@qq.com");

        mailSender.send(simpleMailMessage);


    }

    @Test
    public void test02() throws Exception{
        //复杂邮件内容
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);

        //邮件设置内容
        helper.setSubject("这是标题");
        helper.setText("<p style='color:red'>这是内容</p>",true);

        helper.setTo("ykkwpp@163.com");
        helper.setFrom("1415632002@qq.com");

        //上传文件
//        helper.addAttachment("1.jpg",new File());

        mailSender.send(mimeMessage);
    }

}
