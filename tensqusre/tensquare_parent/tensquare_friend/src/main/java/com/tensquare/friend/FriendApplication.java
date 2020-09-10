package com.tensquare.friend;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import util.JwtUtil;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.tensquare.friend.client"})
@EnableDiscoveryClient
public class FriendApplication {

  public static void main(String[] args) {
    SpringApplication.run(FriendApplication.class, args);
  }


  @Bean
  public JwtUtil getJwt(){
    return new JwtUtil();
  }
}
