package com.tensquare.rabbitmq.customer;


import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

//@Component
@Configuration
@RabbitListener(queues = "itheima")//用RabbitMQ监听itheima队列
public class FanoutCustomer1 {

  @RabbitHandler
  public void getMsg(String msg) {
    System.out.println("分列模式itheima队列消费消息: " + msg);
  }

}
