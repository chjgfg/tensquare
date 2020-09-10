package com.tensquare.spit.controller;

import com.tensquare.spit.pojo.Comment;
import com.tensquare.spit.service.CommentService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/comment")
public class CommentController {
  @Autowired
  private CommentService commentService;

  @PostMapping("saveOne")
  public Result save(@RequestBody Comment comment) {
    commentService.add(comment);
    return new Result(true, StatusCode.OK, "提交成功 ");
  }

  @GetMapping("article/{articleid}")
  public Result findByArticleid(@PathVariable String articleid){
    return new Result(true, StatusCode.OK, "查询成功", commentService.findByArticleid(articleid));
  }

}
