package com.tensquare.article.controller;

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

import com.tensquare.article.pojo.Article;
import com.tensquare.article.service.ArticleService;

import entity.PageResult;
import entity.Result;
import entity.StatusCode;

/**
 * 控制器层
 *
 * @author Administrator
 */
@RestController
@CrossOrigin
@RequestMapping("/article")
public class ArticleController {

  @Autowired
  private ArticleService articleService;


  /**
   * 查询全部数据
   *
   * @return
   */
  @GetMapping("findAll")
  public Result findAll() {
    return new Result(true, StatusCode.OK, "查询成功", articleService.findAll());
  }

  /**
   * 根据ID查询
   *
   * @param id ID
   * @return
   */
  @GetMapping("findById/{id}")
  public Result findById(@PathVariable String id) {
    return new Result(true, StatusCode.OK, "查询成功", articleService.findById(id));
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
    Page<Article> pageList = articleService.findSearch(searchMap, page, size);
    return new Result(true, StatusCode.OK, "查询成功", new PageResult<Article>(pageList.getTotalElements(), pageList.getContent()));
  }

  /**
   * 根据条件查询
   *
   * @param searchMap
   * @return
   */
  @PostMapping(value = "search")
  public Result findSearch(@RequestBody Map searchMap) {
    return new Result(true, StatusCode.OK, "查询成功", articleService.findSearch(searchMap));
  }

  /**
   * 增加
   *
   * @param article
   */
  @PostMapping("saveOne")
  public Result add(@RequestBody Article article) {
    articleService.add(article);
    return new Result(true, StatusCode.OK, "增加成功");
  }

  /**
   * 修改
   *
   * @param article
   */
  @PutMapping(value = "updateById/{id}")
  public Result update(@RequestBody Article article, @PathVariable String id) {
    article.setId(id);
    articleService.update(article);
    return new Result(true, StatusCode.OK, "修改成功");
  }

  /**
   * 删除
   *
   * @param id
   */
  @DeleteMapping(value = "deleteById/{id}")
  public Result delete(@PathVariable String id) {
    articleService.deleteById(id);
    return new Result(true, StatusCode.OK, "删除成功");
  }

  @PutMapping(value = "updateSatus/{id}")
  public Result updateSatus( @PathVariable String id){ //文章审核
    return new Result(true,StatusCode.OK,"审核成功",articleService.updateSatus(id));
  }

  @PutMapping(value = "addThumbup/{id}")
  public Result addThumbup(@PathVariable String id){//文章点赞
    return new Result(true,StatusCode.OK,"点赞成功",articleService.addThumbup(id));
  }

}
