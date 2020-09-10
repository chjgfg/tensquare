package com.tensquare.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tensquare.user.pojo.User;
import com.tensquare.user.service.UserService;

import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import util.JwtUtil;

import javax.annotation.Resource;

/**
 * 控制器层
 *
 * @author Administrator
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;

  @Autowired
  private RedisTemplate redisTemplate;

  @Resource
  private JwtUtil jwtUtil;


  //更新好友粉丝数和用户关注数,之后让friend模块调动
  @PutMapping("{userId}/{friendId}/{number}")
  public Result updateFansAndFollow(@PathVariable String userId, @PathVariable String friendId, @PathVariable String number){
    userService.updateFansAndFollow(userId,friendId,number);
    return new Result(true,StatusCode.OK,"更新成功");
  }


  //用手机号发送短信验证码
  @PostMapping("sendSms/{mobile}")
  public Result sendSms(@PathVariable String mobile) {
    userService.send(mobile);
    return new Result(true, StatusCode.OK, "发送成功");
  }

  //用验证进行用户注册
  @PostMapping("register/{code}")
  public Result register(@PathVariable String code, @RequestBody User user) {
    //得到缓存中的验证码
    String checkcode = (String) redisTemplate.opsForValue().get("user_" + user.getMobile());
    if (checkcode.isEmpty() || code.isEmpty()) {
      return new Result(false, StatusCode.ERROR, "请先获取验证码");
    }
    if (!checkcode.equals(code)) {
      return new Result(false, StatusCode.ERROR, "验证码错误");
    }
    userService.add(user);
    return new Result(true, StatusCode.OK, "注册成功");
  }

  //登录
  @PostMapping("login")
  public Result login(@RequestBody User user){
    User use = userService.login(user.getMobile(),user.getPassword());
    if (use == null) {
      return new Result(false, StatusCode.LOGINERROR, "登录失败");
    }
    String token = jwtUtil.createJWT(use.getId(), use.getNickname(), "user");
    Map<String,Object> map = new HashMap<>();
    map.put("token",token);
    map.put("role","user");
    return new Result(true, StatusCode.OK, "登录成功",map);
  }


  /**
   * 查询全部数据
   *
   * @return
   */
  @GetMapping("findAll")
  public Result findAll() {
    return new Result(true, StatusCode.OK, "查询成功", userService.findAll());
  }

  /**
   * 根据ID查询
   *
   * @param id ID
   * @return
   */
  @GetMapping("findById/{id}")
  public Result findById(@PathVariable String id) {
    return new Result(true, StatusCode.OK, "查询成功", userService.findById(id));
  }


  /**
   * 分页+多条件查询
   *
   * @param searchMap 查询条件封装
   * @param page      页码
   * @param size      页大小
   * @return 分页结果
   */
  @PostMapping("/search/{page}/{size}")
  public Result findSearch(@RequestBody Map searchMap, @PathVariable int page, @PathVariable int size) {
    Page<User> pageList = userService.findSearch(searchMap, page, size);
    return new Result(true, StatusCode.OK, "查询成功", new PageResult<User>(pageList.getTotalElements(), pageList.getContent()));
  }

  /**
   * 根据条件查询
   *
   * @param searchMap
   * @return
   */
  @PostMapping("/search")
  public Result findSearch(@RequestBody Map searchMap) {
    return new Result(true, StatusCode.OK, "查询成功", userService.findSearch(searchMap));
  }

  /**
   * 增加
   *
   * @param user
   */
  @PostMapping("saveOne")
  public Result add(@RequestBody User user) {
    userService.add(user);
    return new Result(true, StatusCode.OK, "增加成功");
  }

  /**
   * 修改
   *
   * @param user
   */
  @PutMapping("updateById/{id}")
  public Result updateById(@RequestBody User user, @PathVariable String id) {
    user.setId(id);
    userService.update(user);
    return new Result(true, StatusCode.OK, "修改成功");
  }

  /**
   * 删除,必须有admin角色才能删除
   *
   * @param id
   */
  @DeleteMapping(value = "deleteById/{id}")
  public Result deleteById(@PathVariable String id) {
    userService.deleteById(id);
    return new Result(true, StatusCode.OK, "删除成功");
  }

}
