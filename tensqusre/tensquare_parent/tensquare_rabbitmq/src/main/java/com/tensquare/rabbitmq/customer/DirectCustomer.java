package com.tensquare.rabbitmq.customer;


import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

//@Component
@Configuration
@RabbitListener(queues = "itcast")//用RabbitMQ监听
public class DirectCustomer {

  @RabbitHandler
  public void getMsg(String msg) {
    System.out.println("直接模式消费消息: " + msg);
  }

}
