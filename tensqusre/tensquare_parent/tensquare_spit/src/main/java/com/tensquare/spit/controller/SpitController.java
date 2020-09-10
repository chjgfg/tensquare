package com.tensquare.spit.controller;

import com.tensquare.spit.pojo.Spit;
import com.tensquare.spit.service.SpitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/spit")
public class SpitController {

  @Autowired
  private SpitService spitService;
  @Autowired
  private RedisTemplate redisTemplate;

  @GetMapping("findAll")
  public Result findAll() {
    return new Result(true, StatusCode.OK, "查找成功", spitService.findAll());
  }

  @GetMapping("findById/{id}")
  public Result findById(@PathVariable String id) {
    return new Result(true, StatusCode.OK, "查找成功", spitService.findById(id));
  }

  @PostMapping("saveOne")
  public Result save(@RequestBody Spit spit) {
    spitService.save(spit);
    return new Result(true, StatusCode.OK, "添加成功");
  }

  @PutMapping("updateOne")
  public Result update(@RequestBody Spit spit) {
    spitService.update(spit);
    return new Result(true, StatusCode.OK, "修改成功");
  }


  @DeleteMapping("deleteById/{id}")
  public Result deleteById(@PathVariable String id) {
    spitService.deleteById(id);
    return new Result(true, StatusCode.OK, "删除成功");
  }

  @GetMapping("findPage/{page}/{size}")
  public Result findPage(@PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) @PathVariable int page, @PathVariable int size) {
    Page<Spit> page1 = spitService.findPage(page, size);
    return new Result(true, StatusCode.OK, "查找成功", new PageResult<>(page1.getTotalElements(), page1.getContent()));
  }


  @GetMapping("findByParentid/{parentid}/{page}/{size}")
  public Result findByParentid(@PathVariable String parentid, @PathVariable int page, @PathVariable int size) {
    Page<Spit> byParentid = spitService.findByParentid(parentid, page, size);
    return new Result(true, StatusCode.OK, "查找成功", new PageResult<>(byParentid.getTotalElements(), byParentid.getContent()));
  }

  @PutMapping("thumbup/{spitid}")
  public Result thumbupById(@PathVariable String spitid) {
    //判断当前是否已经点赞,还没做认证,暂时把user写死
    String userid = "111111";
    if (redisTemplate.opsForValue().get("thumbup_" + userid) != null) {
      return new Result(true, StatusCode.REPERROR, "不能重复点赞");
    }
    spitService.thumbupById(spitid);
    redisTemplate.opsForValue().set("thumbup_" + userid, 1);
    return new Result(true, StatusCode.OK, "点赞成功");
  }

}
