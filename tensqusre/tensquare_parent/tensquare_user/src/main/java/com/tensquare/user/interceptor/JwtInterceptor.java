package com.tensquare.user.interceptor;


//spring的拦截器


import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import util.JwtUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class JwtInterceptor implements HandlerInterceptor {


  @Resource
  private JwtUtil jwtUtil;

  //在判断之前拦截
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    System.out.println("经过了拦截器!!!!!!");

    try{
      //都放行
      //拦截器只是负责把请求头中token的令牌进行解析验证

      String authorization = request.getHeader("Authorization");
      if (authorization != null && !"".equals(authorization)){
        if (authorization.startsWith("Bearer ")){//约定以Bearer开头
          String substring = authorization.substring(7);//获取token
          //对令牌进行验证
          Claims claims = jwtUtil.parseJWT(substring);
          String roles = (String) claims.get("roles");
          if (roles != null && roles.equals("admin")){
            request.setAttribute("claims_admin",substring);
          }else if (roles != null && roles.equals("user")){
            request.setAttribute("claims_user",substring);
          }
        }
      }

    }catch (Exception e){
      throw new RuntimeException("令牌不正确!!!!!!");
    }


    return true;//就是放行
    //return false;//就是不放行



  }
}
