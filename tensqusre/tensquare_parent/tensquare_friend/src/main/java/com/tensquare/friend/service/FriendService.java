package com.tensquare.friend.service;


import com.tensquare.friend.dao.FriendDao;
import com.tensquare.friend.dao.NoFriendDao;
import com.tensquare.friend.pojo.Friend;
import com.tensquare.friend.pojo.NoFriend;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class FriendService {

  @Resource
  private FriendDao friendDao;

  @Resource
  private NoFriendDao noFriendDao;


  public int addFriend(String id, String friendId) {

    //判断userid到friendID是否有数据,有就是重复添加
    Friend friend = friendDao.findByUseridAndAndFriendid(id, friendId);
    if (friend != null) {
      return 0;
    }
    //直接添加好友,让userid到friendid的type为0,即单项添加
    friend = new Friend();
    friend.setUserid(id);
    friend.setFriendid(friendId);
    friend.setIslike("0");
    friendDao.save(friend);
    //判断从friendid到userid是否有数据,有就把双方的type都改为1
    if (friendDao.findByUseridAndAndFriendid(friendId, id) != null) {
      friendDao.updateIslike("1", id, friendId);
      friendDao.updateIslike("1", friendId, id);
    }
    return 1;
  }

  public int addNoFriend(String id, String friendId) {
    NoFriend noFriendid = noFriendDao.findByUseridAndAndFriendid(id, friendId);
    if (noFriendid != null) {
      return 0;//重复添加
    }
    noFriendid = new NoFriend();
    noFriendid.setUserid(id);
    noFriendid.setFriendid(friendId);
    noFriendDao.save(noFriendid);
    return 1;
  }

  public void deleteFriend(String id, String friendId) {
    //删除好友表中userID向friendId这条数据
    friendDao.deleteFriend(id,friendId);
    //更新friendId到userID的islike为0
    friendDao.updateIslike("0",friendId,id);
    //非好友表中添加这条数据
    NoFriend n = new NoFriend();
    n.setFriendid(friendId);
    n.setUserid(id);
    noFriendDao.save(n);

  }
}
