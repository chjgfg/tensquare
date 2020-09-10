package com.tensquare.sms.listener;


import com.aliyuncs.exceptions.ClientException;
import com.tensquare.sms.utils.NewSmsUtils;
import com.tensquare.sms.utils.SmsUtils;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RabbitListener(queues = "sms")
public class SmsListener {

  @Autowired
  private NewSmsUtils smsUtils;

/*  @Value("${aliyun.sms.template_code}")
  private String template_code;

  @Value("${aliyun.sms.sign_name}")
  private String sign_name;*/

  @RabbitHandler//用于从消息队列中消费消息,获取手机号和验证码,从而用阿里的短信验证去给用户发送消息
  public void excuteSms(Map<String,String> map) throws ClientException {
    String mobile = map.get("mobile");
    String checkcode = map.get("checkcode");
    System.out.println("手机号 --> " + map.get("mobile"));
    System.out.println("验证码 --> " + map.get("checkcode"));
    smsUtils.sendSms(mobile,checkcode);
  }
}
