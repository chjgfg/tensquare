package com.tensquare.user.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import io.jsonwebtoken.Claims;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import util.IdWorker;

import com.tensquare.user.dao.UserDao;
import com.tensquare.user.pojo.User;
import util.JwtUtil;

/**
 * 服务层
 *
 * @author Administrator
 */
@Service
public class UserService {

  @Autowired
  private UserDao userDao;

  @Autowired
  private IdWorker idWorker;

  @Autowired
  private RedisTemplate redisTemplate;

  @Autowired
  private RabbitTemplate rabbitTemplate;

  @Autowired
  private BCryptPasswordEncoder encoder;

  @Resource
  private JwtUtil jwtUtil;

  @Resource
  private HttpServletRequest request;

  /**
   * 查询全部列表
   *
   * @return
   */
  public List<User> findAll() {
    return userDao.findAll();
  }


  /**
   * 条件查询+分页
   *
   * @param whereMap
   * @param page
   * @param size
   * @return
   */
  public Page<User> findSearch(Map whereMap, int page, int size) {
    Specification<User> specification = createSpecification(whereMap);
    PageRequest pageRequest = PageRequest.of(page - 1, size);
    return userDao.findAll(specification, pageRequest);
  }


  /**
   * 条件查询
   *
   * @param whereMap
   * @return
   */
  public List<User> findSearch(Map whereMap) {
    Specification<User> specification = createSpecification(whereMap);
    return userDao.findAll(specification);
  }

  /**
   * 根据ID查询实体
   *
   * @param id
   * @return
   */
  public User findById(String id) {
    return userDao.findById(id).get();
  }

  /**
   * 增加
   *
   * @param user
   */
  public void add(User user) {
    user.setId(idWorker.nextId() + "");

    //密码加密
    user.setPassword(encoder.encode(user.getPassword()));
    user.setFollowcount(0);//关注数         
    user.setFanscount(0);//粉丝数         
    user.setOnline(0L);//在线时长         
    user.setRegdate(new Date());//注册日期         
    user.setUpdatedate(new Date());//更新日期         
    user.setLastdate(new Date());//后登陆日期
    userDao.save(user);
  }

  /**
   * 修改
   *
   * @param user
   */
  public void update(User user) {
    userDao.save(user);
  }

  /**
   * 删除
   *
   * @param id
   */
  public void deleteById(String id) {
    /*String authorization = request.getHeader("Authorization");
    if (authorization == null || authorization.isEmpty()){
      throw  new RuntimeException("请先登录,权限不足!!!");
    }
    if (!authorization.startsWith("Bearer")){//约定以Bearer开头
      throw  new RuntimeException("请先登录,权限不足!!!");
    }
    String token = authorization.substring(6);
    try{
      Claims claims = jwtUtil.parseJWT(token);
      String roles = (String) claims.get("roles");
      if (roles == null || !roles.equals("admin")){
        throw  new RuntimeException("请先登录,权限不足!!!");
      }
    }catch (Exception e){
      throw  new RuntimeException("请先登录,权限不足!!!");
    }*/
    String admin =(String) request.getAttribute("claims_admin");//从Jwt拦截器中拿到admin的权限
    if (admin == null || "".equals(admin)){
      throw  new RuntimeException("请先登录,权限不足!!!");
    }
    userDao.deleteById(id);
  }

  /**
   * 动态条件构建
   *
   * @param searchMap
   * @return
   */
  private Specification<User> createSpecification(Map searchMap) {

    return new Specification<User>() {

      @Override
      public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicateList = new ArrayList<Predicate>();
        // ID
        if (searchMap.get("id") != null && !"".equals(searchMap.get("id"))) {
          predicateList.add(cb.like(root.get("id").as(String.class), "%" + (String) searchMap.get("id") + "%"));
        }
        // 手机号码
        if (searchMap.get("mobile") != null && !"".equals(searchMap.get("mobile"))) {
          predicateList.add(cb.like(root.get("mobile").as(String.class), "%" + (String) searchMap.get("mobile") + "%"));
        }
        // 密码
        if (searchMap.get("password") != null && !"".equals(searchMap.get("password"))) {
          predicateList.add(cb.like(root.get("password").as(String.class), "%" + (String) searchMap.get("password") + "%"));
        }
        // 昵称
        if (searchMap.get("nickname") != null && !"".equals(searchMap.get("nickname"))) {
          predicateList.add(cb.like(root.get("nickname").as(String.class), "%" + (String) searchMap.get("nickname") + "%"));
        }
        // 性别
        if (searchMap.get("sex") != null && !"".equals(searchMap.get("sex"))) {
          predicateList.add(cb.like(root.get("sex").as(String.class), "%" + (String) searchMap.get("sex") + "%"));
        }
        // 头像
        if (searchMap.get("avatar") != null && !"".equals(searchMap.get("avatar"))) {
          predicateList.add(cb.like(root.get("avatar").as(String.class), "%" + (String) searchMap.get("avatar") + "%"));
        }
        // E-Mail
        if (searchMap.get("email") != null && !"".equals(searchMap.get("email"))) {
          predicateList.add(cb.like(root.get("email").as(String.class), "%" + (String) searchMap.get("email") + "%"));
        }
        // 兴趣
        if (searchMap.get("interest") != null && !"".equals(searchMap.get("interest"))) {
          predicateList.add(cb.like(root.get("interest").as(String.class), "%" + (String) searchMap.get("interest") + "%"));
        }
        // 个性
        if (searchMap.get("personality") != null && !"".equals(searchMap.get("personality"))) {
          predicateList.add(cb.like(root.get("personality").as(String.class), "%" + (String) searchMap.get("personality") + "%"));
        }

        return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

      }
    };

  }


  //发验证码
  public void send(String mobile) {
    //生成6位随机数
    String checkcode = RandomStringUtils.randomNumeric(6);
    //向缓存中存一份
    redisTemplate.opsForValue().set("user_" + mobile, checkcode, 6, TimeUnit.HOURS);
    //向用户发一份
    Map<String, String> map = new HashMap<>();
    map.put("mobile", mobile);
    map.put("checkcode", checkcode);
    rabbitTemplate.convertAndSend("sms", map);
    //控制台打印一份
    Logger send = LoggerFactory.getLogger("send");
    send.info("验证码  ----------->" + checkcode);
  }


  //拿到密码和数据库里的进行对比
  public User login(String mobile, String password) {
    User nickname = userDao.findByMobile(mobile);
    if (nickname != null && encoder.matches(password, nickname.getPassword())) {
      return nickname;
    }
    return null;
  }

  public void updateFansAndFollow(String userId, String friendId, String number) {
    userDao.updateFans(number,friendId);
    userDao.updateFollow(number,userId);

  }
}
