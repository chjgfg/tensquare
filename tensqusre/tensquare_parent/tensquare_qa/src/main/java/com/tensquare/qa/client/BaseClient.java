package com.tensquare.qa.client;

import com.tensquare.qa.pojo.Label;

import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient("tensquare-base")
public interface BaseClient {

  @GetMapping("label/findAll")
  public Result findAll();


  @GetMapping(value="label/{id}")
  public Result findById(@PathVariable("id") String id);


  @PostMapping("label/search/{page}/{size}")
  public Result findSearch(@RequestBody Map searchMap , @PathVariable("page") int page, @PathVariable("size") int size);


  @PostMapping("label/search")
  public Result findSearch( @RequestBody Map searchMap);


  @PostMapping("label/saveOne")
  public Result add(@RequestBody Label label);


  @PutMapping("label/updateById/{id}")
  public Result updateById(@RequestBody Label label, @PathVariable String id);


  @DeleteMapping("label/deleteById/{id}")
  public Result deleteById(@PathVariable("id") String id );
}
