package com.tensquare.friend.dao;

import com.tensquare.friend.pojo.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FriendDao extends JpaRepository<Friend, String> {

  public Friend findByUseridAndAndFriendid(String userId, String friendId);

  @Modifying
  @Query(value = "UPDATE tb_friend SET islike = ? WHERE userid = ? AND friendid = ?", nativeQuery = true)
  public void updateIslike(String islike, String userid, String friendId);

  @Modifying
  @Query(value = "delete from tb_friend WHERE userid = ? AND friendid = ?", nativeQuery = true)
  public void deleteFriend(String id, String friendId);
}
