package com.tensquare.qa.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.qa.pojo.Problem;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ProblemDao extends JpaRepository<Problem,String>,JpaSpecificationExecutor<Problem>{

  //最新
  @Query(value = "select * from tb_problem pm inner join tb_pl pl on pm.id = pl.problemid and pl.labelid = ? order by  replytime desc",nativeQuery = true)
  public Page<Problem> newList(String labelid, Pageable pageable);

  //热门
  @Query(value = "select * from tb_problem pm inner join tb_pl pl on pm.id = pl.problemid and pl.labelid = ? order by  reply desc",nativeQuery = true)
  public Page<Problem> hotList(String labelid, Pageable pageable);

  //等待
  @Query(value = "select * from tb_problem pm inner join tb_pl pl on pm.id = pl.problemid and pl.labelid = ? and reply = 0 order by  createtime desc",nativeQuery = true)
  public Page<Problem> waitList(String labelid, Pageable pageable);


}
