package com.tensquare.friend.client;

import entity.Result;
import entity.StatusCode;
import org.springframework.stereotype.Component;


@Component
public class UserClientImpl implements UserClient {
  @Override
  public Result updateFansAndFollow(String userId, String friendId, String number) {
    return new Result(false, StatusCode.ERROR,"远程调用出现了问题,触发了熔断器");
  }
}
