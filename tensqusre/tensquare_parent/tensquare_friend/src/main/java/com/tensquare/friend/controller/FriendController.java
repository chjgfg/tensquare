package com.tensquare.friend.controller;

import com.tensquare.friend.client.UserClient;
import com.tensquare.friend.service.FriendService;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("friend")
public class FriendController {
  @Resource
  private HttpServletRequest request;


  @Autowired
  private FriendService friendService;


  @Resource
  private UserClient userClient;


  //添加好友或添加非好友
  @PutMapping("like/{friendId}/{type}")
  public Result addFriend(@PathVariable String friendId, @PathVariable String type) {
    //验证是否登录,并且拿到当前登录的用户id
    Claims user = (Claims) request.getAttribute("claims_user");
    System.out.println("user:" + user);
    if (user == null || "".equals(user)) {
      return new Result(false, StatusCode.ERROR, "请先登录");
    }

    //拿到当前登录的用户id
    String id = user.getId();
    System.out.println("id:" + id);
    //判断添加好友还是添加非好友
    if (type != null) {
      if (type.equals("1")) {
        //添加好友
        int i = friendService.addFriend(id, friendId);
        if (i == 1) {
          Result result = userClient.updateFansAndFollow(id, friendId, "1");
          return new Result(true, StatusCode.OK, "添加成功",result);
        } else if (i == 0) {
          return new Result(true, StatusCode.OK, "不能重复添加好友");
        }
      } else if (type.equals("2")) {
        //添加非好友
        int m = friendService.addNoFriend(id, friendId);
        if (m == 1) {
          return new Result(true, StatusCode.OK, "添加成功");
        } else if (m == 0) {
          return new Result(true, StatusCode.OK, "不能重复添加非好友");
        }
      }
      return new Result(false, StatusCode.ERROR, "参数异常");
    } else {
      return new Result(false, StatusCode.ERROR, "参数异常");
    }
  }


  @DeleteMapping("delete/{friendId}")
  public Result delete(@PathVariable String friendId) {
    //验证是否登录,并且拿到当前登录的用户id
    Claims user = (Claims) request.getAttribute("claims_user");
    System.out.println("user:" + user);
    if (user == null || "".equals(user)) {
      return new Result(false, StatusCode.ERROR, "请先登录");
    }

    //拿到当前登录的用户id
    String id = user.getId();
    System.out.println("id:"+id);
    friendService.deleteFriend(id,friendId);
    userClient.updateFansAndFollow(id, friendId, "-1");

    return new Result(true,StatusCode.OK,"删除成功");
  }


}
