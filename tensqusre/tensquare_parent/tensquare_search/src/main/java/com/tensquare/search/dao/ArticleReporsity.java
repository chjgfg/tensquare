package com.tensquare.search.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tensquare.search.pojo.Article;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleReporsity extends BaseMapper<Article> {

}
