package com.tensquare.web.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


@Component
public class WebFilter extends ZuulFilter {

  //请求前过滤
  @Override
  public String filterType() {
    return "pre";
  }

  //这个过滤器的等级,数字越小,最先使用
  @Override
  public int filterOrder() {
    return 0;
  }


  //开启这个过滤器
  @Override
  public boolean shouldFilter() {
    return true;
  }

  //这里面写自己的业务逻辑,从这里面可以拿到请求头,然后设置之后就可以让被代理的接口拿到
  @Override
  public Object run() throws ZuulException {

    //得到上下文
    RequestContext currentContext = RequestContext.getCurrentContext();
    //得到requset域
    HttpServletRequest request = currentContext.getRequest();

    if (request.getMethod().equals("OPTIONS")){//zuul自带的进行请求转发的那个方法
      return null;
    }

    //得到头信息
    String header = request.getHeader("Authorization");

    System.out.println("过滤器的header -------> " + header);
    if (header != null && !"".equals(header)) {
      //把头信息继续向下传递
      currentContext.addZuulRequestHeader("Authorization", header);
      System.out.println("currentContext -------> " + currentContext);
    }

    return currentContext;
  }
}
