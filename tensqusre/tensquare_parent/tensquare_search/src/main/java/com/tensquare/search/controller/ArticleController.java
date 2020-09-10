package com.tensquare.search.controller;


import com.tensquare.search.pojo.Article;
import com.tensquare.search.service.ArticleService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/searchArticle")
@CrossOrigin
public class ArticleController {

  @Autowired
  private ArticleService articleService;

  @PostMapping("saveOne")
  public Result save(@RequestBody Article article) {
    articleService.saveOne(article);
    return new Result(true, StatusCode.OK, "保存成功");
  }

  @GetMapping("findByKey/{key}/{page}/{size}")
  public Result findByKey(@PathVariable String key, @PathVariable int page, @PathVariable int size) {
    Page<Article> byKey = articleService.findByKey(key, page, size);
    return new Result(true, StatusCode.OK, "查找成功", new PageResult<>(byKey.getTotalElements(), byKey.getContent()));
  }

  @GetMapping("findByKeyOfTemplate/{key}/{page}/{size}")
  public Result findByKeyOfTemplate(@PathVariable String key, @PathVariable int page, @PathVariable int size) {
//    Page<Article> byKey = articleService.findByKeyOfTemplate(key, page, size);
    return new Result(true, StatusCode.OK, "查找成功", articleService.findByKeyOfTemplate(key, page, size));
  }

  @DeleteMapping("deleteOne/{id}")
  public Result deleteOne(@PathVariable String id) {
    return new Result(true, StatusCode.OK, "删除成功", articleService.deleteOne(id));
  }

  @PutMapping("updateOne")
  public Result updateOne(@RequestBody Article article) {
    return new Result(true, StatusCode.OK, "修改成功", articleService.updateOne(article));
  }


}
