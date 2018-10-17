package cn.footman.amqp;

import cn.footman.amqp.bean.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootAmqpApplicationTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AmqpAdmin amqpAdmin;

    @Test
    public void createExchange(){

//        amqpAdmin.declareExchange(new DirectExchange("amqpAdmin.exchange"));
//        System.out.println("创建完成");
//        amqpAdmin.declareQueue(new Queue("amqpAdmin.queue",true));
        amqpAdmin.declareBinding(new Binding("amqpAdmin.queue",Binding.DestinationType.QUEUE,"amqpAdmin.exchange","amqp.content",null));
    }


    /**
     * 1、单播（点对点）
     */
    @Test
    public void contextLoads() {
//        Message需要自己定义；定义消息体内容和消息头
//        rabbitTemplate.send();

        //object默认当成消息体，只需要传入要发送的良，自动序列化发送给rabbitmq
//        rabbitTemplate.convertAndSend(exchange,routKey,object);
        Map<String,Object> map = new HashMap<>();
        map.put("first msg","nihao shijie");
        map.put("data", Arrays.asList("hello",13324,true));
        //对象被默认序列化以后发送
        rabbitTemplate.convertAndSend("exchange.direct","atguigu.news",new Book("jean","mark"));
    }

    //接受数据，如何将数据自动转为json
    @Test
    public void receive(){
        Object o = rabbitTemplate.receiveAndConvert("atguigu.news");
        System.out.println(o.getClass());
        System.out.println(o);
    }


    /**
     * 广播
     */
    @Test
    public void sendMsg(){
        rabbitTemplate.convertAndSend("exchange.fanout","",new Book("1984","奥威尔"));
    }

}
