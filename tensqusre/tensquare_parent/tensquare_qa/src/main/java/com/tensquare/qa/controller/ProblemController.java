package com.tensquare.qa.controller;

import java.util.List;
import java.util.Map;

import com.tensquare.qa.client.BaseClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

import com.tensquare.qa.pojo.Problem;
import com.tensquare.qa.service.ProblemService;

import entity.PageResult;
import entity.Result;
import entity.StatusCode;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 控制器层
 *
 * @author Administrator
 */
@RestController
@CrossOrigin
@RequestMapping("/problem")
public class ProblemController {

  @Autowired
  private ProblemService problemService;

  @Resource
  private HttpServletRequest request;

  @Autowired
  private BaseClient baseClient;


  //通过id查找到base模块的东西
  @GetMapping(value="label/{id}")
  public Result findByLableId(@PathVariable String id){
    return baseClient.findById(id);
  }






  /**
   * 查询全部数据
   *
   * @return
   */
  @GetMapping("findAll")
  public Result findAll() {
    return new Result(true, StatusCode.OK, "查询成功", problemService.findAll());
  }

  /**
   * 根据ID查询
   *
   * @param id ID
   * @return
   */
  @GetMapping("findById/{id}")
  public Result findById(@PathVariable String id) {
    return new Result(true, StatusCode.OK, "查询成功", problemService.findById(id));
  }


  /**
   * 分页+多条件查询
   *
   * @param searchMap 查询条件封装
   * @param page      页码
   * @param size      页大小
   * @return 分页结果
   */
  @PostMapping(value = "search/{page}/{size}")
  public Result findSearch(@RequestBody Map searchMap, @PathVariable int page, @PathVariable int size) {
    Page<Problem> pageList = problemService.findSearch(searchMap, page, size);
    return new Result(true, StatusCode.OK, "查询成功", new PageResult<Problem>(pageList.getTotalElements(), pageList.getContent()));
  }

  /**
   * 根据条件查询
   *
   * @param searchMap
   * @return
   */
  @PostMapping(value = "search")
  public Result findSearch(@RequestBody Map searchMap) {
    return new Result(true, StatusCode.OK, "查询成功", problemService.findSearch(searchMap));
  }

  /**
   * 增加
   *
   * @param problem
   */
  @PostMapping("saveOne")
  public Result add(@RequestBody Problem problem) {
    String token = (String) request.getAttribute("claims_user");

    if (token == null||"".equals(token)){
      return new Result(false, StatusCode.LOGINERROR, "添加失败,权限不足,请先登陆");
    }


    problemService.add(problem);
    return new Result(true, StatusCode.OK, "增加成功");
  }

  /**
   * 修改
   *
   * @param problem
   */
  @PutMapping(value = "updateById/{id}")
  public Result updateById(@RequestBody Problem problem, @PathVariable String id) {
    problem.setId(id);
    problemService.update(problem);
    return new Result(true, StatusCode.OK, "修改成功");
  }

  /**
   * 删除
   *
   * @param id
   */
  @DeleteMapping(value = "deleteById/{id}")
  public Result deleteById(@PathVariable String id) {
    problemService.deleteById(id);
    return new Result(true, StatusCode.OK, "删除成功");
  }



  //最新
  @GetMapping("newList/{labelId}/{page}/{size}")
  public Result newList(@PathVariable String labelId, @PathVariable int page,@PathVariable int size){
    Page<Problem> problems = problemService.newList(labelId, page, size);
    return new Result(true,StatusCode.OK,"查找成功", new PageResult<>(problems.getTotalElements(), problems.getContent()));
  }

  //热门
  @GetMapping("hotList/{labelId}/{page}/{size}")
  public Result hotList(@PathVariable String labelId, @PathVariable int page,@PathVariable int size){
    Page<Problem> problems = problemService.hotList(labelId, page, size);
    return new Result(true,StatusCode.OK,"查找成功", new PageResult<>(problems.getTotalElements(), problems.getContent()));
  }

  //等待
  @GetMapping("waitList/{labelId}/{page}/{size}")
  public Result waitList(@PathVariable String labelId, @PathVariable int page,@PathVariable int size){
    Page<Problem> problems = problemService.waitList(labelId, page, size);
    return new Result(true,StatusCode.OK,"查找成功", new PageResult<>(problems.getTotalElements(), problems.getContent()));
  }


}
