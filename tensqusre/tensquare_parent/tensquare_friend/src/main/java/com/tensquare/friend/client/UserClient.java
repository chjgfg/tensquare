package com.tensquare.friend.client;

import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(value = "tensquare-user",fallback = UserClientImpl.class)//feign自带的hystrix
public interface UserClient {

  @PutMapping("{userId}/{friendId}/{number}")
  public Result updateFansAndFollow(@PathVariable("userId") String userId, @PathVariable("friendId") String friendId, @PathVariable("number") String number);
}
