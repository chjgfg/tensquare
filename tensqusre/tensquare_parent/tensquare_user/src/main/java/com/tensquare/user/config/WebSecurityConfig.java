package com.tensquare.user.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;

@Component
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity security) throws Exception {

    /*
    authorizeRequests所有security全注解配置实现的开端,表示开始说明需要的权限
    需要的权限分两部分1.拦截的路径 2.访问该路径需要的权限
    antMatchers表示拦截什么路径,permitAll任何权限都可以访问,直接放行所有
    anyRequest任何请求, authenticated认证后才可以访问
    .and().csrf().disable();固定写法,csrf是一种网络攻击手段,security中认为除了他自己的请求,其余的请求都是在攻击他,因此需要禁用csrf
     */
    security.authorizeRequests()
        .antMatchers("/**").permitAll()
        .anyRequest().authenticated()
        .and().csrf().disable();

  }

}
