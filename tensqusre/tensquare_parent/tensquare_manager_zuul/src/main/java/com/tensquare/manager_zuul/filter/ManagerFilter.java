package com.tensquare.manager_zuul.filter;


import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;

@Component
public class ManagerFilter extends ZuulFilter {


  @Autowired
  private JwtUtil jwtUtill;


  //过滤器的类型,pre是前执行,post是后执行
  @Override
  public String filterType() {
    return "pre";
  }


  //过滤器的级别,越小这个过滤器越先执行
  @Override
  public int filterOrder() {
    return 1;
  }

  //是否开启这个过滤器
  @Override
  public boolean shouldFilter() {
    return true;
  }


  //过滤器执行的内容,返回任何东西都表示继续执行
  //只有 setSendZuulRespsonse(false)才会不再执行
  @Override
  public Object run() throws ZuulException {
    System.out.println("经过了后端的路由过滤器");
    RequestContext currentContext = RequestContext.getCurrentContext();
    HttpServletRequest request = currentContext.getRequest();


    if (request.getMethod().equals("OPTIONS")){//zuul自带的进行请求转发的那个方法
      return null;
    }

    if (request.getRequestURL().indexOf("login")>0) {//登录的就要放行
      System.out.println("登录页面 :"+  request.getRequestURL());
      return null;
    }




    String header = request.getHeader("Authorization");
    if (header != null && !"".equals(header)) {
      if (header.startsWith("Bearer ")) {
        String substring = header.substring(7);
        try {
          Claims claims = jwtUtill.parseJWT(substring);
          System.out.println("claims  --------->"+claims);
          String roles = (String) claims.get("roles");
          if (roles.equals("admin")) {
            currentContext.addZuulRequestHeader("Authorization",header);
            return null;
          }
        } catch (Exception r) {
          r.printStackTrace();
          currentContext.setSendZuulResponse(false);//终止运行
        }
      }
    }
    currentContext.setSendZuulResponse(false);//终止运行
    currentContext.setResponseStatusCode(403);//springsecurity自定义的权限不足
    currentContext.setResponseBody("权限不足");
    currentContext.getResponse().setContentType("text/html;charset=utf-8");
    return null;
  }
}
