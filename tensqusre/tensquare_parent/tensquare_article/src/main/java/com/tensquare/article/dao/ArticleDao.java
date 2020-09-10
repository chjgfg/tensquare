package com.tensquare.article.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.article.pojo.Article;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ArticleDao extends JpaRepository<Article,String>,JpaSpecificationExecutor<Article>{


//  @Transactional
  @Modifying //增删改都得用
  @Query(value = "update tb_article set state = 1 where id = ? ",nativeQuery = true)
  public int updateSatus(String id);

//  @Transactional
  @Modifying //增删改都得用
  @Query(value = "update tb_article set thumbup = thumbup + 1 where id = ? ",nativeQuery = true)
  public int addThumbup(String id);
}
