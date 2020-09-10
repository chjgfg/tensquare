package com.tensquare.sms.utils;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
@RabbitListener(queues = "sms")
public class NewSmsUtils {


  @Value("${aliyun.sms.accessKeyId}")
  private String accessKeyId;
  @Value("${aliyun.sms.accessKeySecret}")
  private String accessKeySecret;
  @Value("${aliyun.sms.template_code}")
  private String template_code;
  @Value("${aliyun.sms.sign_name}")
  private String sign_name;


  public void sendSms(String mobile,String code) {
    DefaultProfile profile = DefaultProfile.getProfile("default", accessKeyId, accessKeySecret);
    IAcsClient client = new DefaultAcsClient(profile);

    CommonRequest request = new CommonRequest();
    request.setSysMethod(MethodType.POST);
    request.setSysDomain("dysmsapi.aliyuncs.com");
    request.setSysVersion("2017-05-25");
    request.setSysAction("SendSms");
    request.putQueryParameter("PhoneNumbers", mobile);
    request.putQueryParameter("TemplateParam", "{\"chesckcode\":\""+code+"\"}");
    request.putQueryParameter("TemplateCode", template_code);
    request.putQueryParameter("SignName", sign_name);
    try {
      CommonResponse response = client.getCommonResponse(request);
      System.out.println(response.getData());
    } catch (ClientException e) {
      e.printStackTrace();
    }
  }
}
