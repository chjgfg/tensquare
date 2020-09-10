package com.tensquare.search.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tensquare.search.dao.ArticleDao;
import com.tensquare.search.dao.ArticleReporsity;
import com.tensquare.search.pojo.Article;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

import javax.annotation.Resource;

@Service
@Transactional
public class ArticleService {
  @Autowired
  private ArticleDao articleDao;

  @Resource
  private ArticleReporsity articleReporsity;

  @Autowired
  private ElasticsearchTemplate elasticsearchTemplate;
  @Autowired
  private IdWorker idWorker;

  public void save(Article article) {
    //article.setId(idWorker.nextId() + "");
    articleDao.save(article);
  }

  public Page<Article> findByKey(String key, int page, int size) {
    return articleDao.findByTitleOrContentLike(key, key, PageRequest.of(page - 1, size));
  }

  public AggregatedPage<Article> findByKeyOfTemplate(String key, int page, int size) {
    SearchQuery searchQuery = new NativeSearchQueryBuilder()
        .withIndices("tensquare_articles")
        .withTypes("article")
        .withQuery(QueryBuilders.queryStringQuery(key).field("title").field("content"))
        //.withSort(new FieldSortBuilder("id").order(SortOrder.ASC))
        .withPageable(PageRequest.of(page - 1, size))
        .build();
    return elasticsearchTemplate.queryForPage(searchQuery, Article.class);
  }


  public Iterable<Article> findAll() {//mb
    QueryWrapper<Article> queryWrapper = new QueryWrapper<>(null);
    return articleReporsity.selectList(queryWrapper);
  }

  public Article findByTitle(String a) {
    QueryWrapper<Article> qw = new QueryWrapper<>();
    qw.eq("title", a);
    return articleReporsity.selectOne(qw);
  }

  public Article findById(String a) {
    QueryWrapper<Article> qw = new QueryWrapper<>();
    qw.eq("id", a);
    return articleReporsity.selectOne(qw);
  }


  public int saveToMs(Article article) {
    article.setId(idWorker.nextId() + "");
    return articleReporsity.insert(article);
  }

  @Bean
  public int msToEs() {
    Iterable<Article> all = findAll();
    for (Article article : all) {
      save(article);
      System.out.println(article);
    }
    return 1;
  }


  public String saveOne(Article article) {
    Article byTitle = findByTitle(article.getTitle());
    if (byTitle == null || "".equals(byTitle)) {
      int i = saveToMs(article);
      if (i == 1) {
        save(article);
        return "insert to mysql and elaseticsearch!!!!!";
      }
    } else {
      save(article);
      return "mysql is ok ,insert to elasetvcseah!!!!!!";
    }
    return "mysql or elasticsearch are not to insert!!!!!";
  }

  public String deleteOne(String id) {
    int i = articleReporsity.deleteById(id);
    if (i == 1) {
      QueryWrapper<Article> qw = new QueryWrapper<>();
      qw.eq("id", id);
      int delete = articleReporsity.delete(qw);
      if (delete == 1) {
        articleDao.deleteById(id);
      }
      return "mysql and elsticsearch are deleted";
    }
    return "mysql or elseticsearch is not to delete";
  }

  public String updateOne(Article article){
    QueryWrapper<Article> qw = new QueryWrapper<>();
    qw.eq("id", article.getId());
    int update = articleReporsity.update(article, qw);
    if (update == 1){
      Article save = articleDao.save(article);
      if (save != null){
        return "mysql and elsticsearch are update";
      }
    }
    return "mysql or elseticsearch is not to update";
  }





}








