package com.tensquare.rabbitmq.product;

import com.tensquare.rabbitmq.RabbitMQApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RabbitMQApplication.class)
public class ProductTest {


  @Autowired
  private RabbitTemplate rabbitTemplate;


  //直接模式
  @Test
  public void sendMsgDirect(){
    rabbitTemplate.convertAndSend("itcast","直接模式中服务方发送的消息");//指定那个队列,并加上要发的内容
  }


  //分列模式
  @Test
  public void sendMsgFanout(){
    rabbitTemplate.convertAndSend("chuanzhi","","分列模式中服务方发送的消息");//指定那个交换器,并加上要发的内容
  }

  //主题模式
  @Test
  public void sendMsgTopic(){
    //指定那个交换器,指定往三个队列发送消息,并加上要发的内容
    rabbitTemplate.convertAndSend("it_topic","good.log","主题模式中服务方指定往三个队列发送消息");
  }



}
