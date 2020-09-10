package com.tensquare.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.tensquare.user.pojo.Admin;
import com.tensquare.user.service.AdminService;

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
@RequestMapping("/admin")
public class AdminController {

  @Autowired
  private AdminService adminService;

  @Resource
  private JwtUtil jwtUtil;


  @PostMapping("login")
  public Result login(@RequestBody Admin admin){
    System.out.println("admin"+admin);

    Admin admin1 = adminService.login(admin);
    System.out.println("admin1"+admin1);
    if (admin1 == null || "".equals(admin1)){
      return new Result(false,StatusCode.ACCESSERROR,"用户名或密码错误");
    }
    //使得前后端可以通话的操作,使用JWT生成令牌
    String role = "admin";
    String token = jwtUtil.createJWT(admin1.getId(), admin1.getLoginname(), role);
    Map<String,Object> map = new HashMap<>();
    map.put("token",token);
    map.put("role",role);
    return new Result(true,StatusCode.OK,"登录成功",map);
  }


  /**
   * 查询全部数据
   *
   * @return
   */
  @GetMapping("findAll")
  public Result findAll() {
    return new Result(true, StatusCode.OK, "查询成功", adminService.findAll());
  }

  /**
   * 根据ID查询
   *
   * @param id ID
   * @return
   */
  @GetMapping("findById/{id}")
  public Result findById(@PathVariable String id) {
    return new Result(true, StatusCode.OK, "查询成功", adminService.findById(id));
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
    Page<Admin> pageList = adminService.findSearch(searchMap, page, size);
    return new Result(true, StatusCode.OK, "查询成功", new PageResult<Admin>(pageList.getTotalElements(), pageList.getContent()));
  }

  /**
   * 根据条件查询
   *
   * @param searchMap
   * @return
   */
  @PostMapping("/search")
  public Result findSearch(@RequestBody Map searchMap) {
    return new Result(true, StatusCode.OK, "查询成功", adminService.findSearch(searchMap));
  }

  /**
   * 增加
   *
   * @param admin
   */
  @PostMapping("saveOne")
  public Result add(@RequestBody Admin admin) {
    adminService.add(admin);
    return new Result(true, StatusCode.OK, "增加成功");
  }

  /**
   * 修改
   *
   * @param admin
   */
  @PutMapping("updateById/{id}")
  public Result updateById(@RequestBody Admin admin, @PathVariable String id) {
    admin.setId(id);
    adminService.update(admin);
    return new Result(true, StatusCode.OK, "修改成功");
  }

  /**
   * 删除
   *
   * @param id
   */
  @DeleteMapping(value = "deleteById/{id}")
  public Result deleteById(@PathVariable String id) {
    adminService.deleteById(id);
    return new Result(true, StatusCode.OK, "删除成功");
  }

}
