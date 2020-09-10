package com.tensquare.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyJwtTest {

  @Test
  public void createJwt() {
    JwtBuilder jwtBuilder = Jwts.builder()//载荷
        .setId("111111")
        .setSubject("对象")
        .setIssuedAt(new Date())//日期
        .signWith(SignatureAlgorithm.HS256, "secrite")//头部的编码格式和盐
        .claim("role","admin")//自定义用户角色
        .setExpiration(new Date(new Date().getTime() + 60000L));//过期时间
    System.out.println(jwtBuilder.compact());//生成token字符串
  }


  @Test
  public void parseJwt() {
    String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMTExMTEiLCJzdWIiOiLlr7nosaEiLCJpYXQiOjE1OTkzMDg2NjQsInJvbGUiOiJhZG1pbiIsImV4cCI6MTU5OTMwODcyNH0.AqivSTsk6xT02X6AP7xW5-lCSCU4R61512rggQxyU78";
    Claims secrite = Jwts.parser().setSigningKey("secrite")
        .parseClaimsJws(token)
        .getBody();
    System.out.println(secrite.getId());
    System.out.println(secrite.getSubject());
    System.out.println(secrite.get("role"));
    System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(secrite.getIssuedAt()));
    System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(secrite.getExpiration()));
  }
}
