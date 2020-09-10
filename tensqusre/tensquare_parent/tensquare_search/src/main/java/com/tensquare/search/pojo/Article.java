package com.tensquare.search.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

@TableName(value = "tb_article")
@Document(indexName = "tensquare_articles")
public class Article implements Serializable {

  @Id
  @TableId
  private String id;//ID

  @TableField
  @Field(analyzer = "ik_max_word", searchAnalyzer = "ik_max_word", type = FieldType.text)
  private String title;//标题
  @TableField
  @Field(analyzer = "ik_max_word", searchAnalyzer = "ik_max_word", type = FieldType.text)
  private String content;//文章正文
  @TableField
  @Field(type = FieldType.keyword)
  private String state;//审核状态
  @TableField
  private String image;//文章封面
  @TableField
  private String columnid;//专栏ID
  @TableField
  private String userid;//用户ID
  @TableField
  private Date createtime;//发表日期
  @TableField
  private Date updatetime;//修改日期
  @TableField
  private String ispublic;//是否公开
  @TableField
  private String istop;//是否置顶
  @TableField
  private Integer visits;//浏览量
  @TableField
  private Integer thumbup;//点赞数
  @TableField
  private Integer comment;//评论数
  @TableField
  private String channelid;//所属频道
  @TableField
  private String url;//URL
  @TableField
  private String type;//类型

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getColumnid() {
    return columnid;
  }

  public void setColumnid(String columnid) {
    this.columnid = columnid;
  }

  public String getUserid() {
    return userid;
  }

  public void setUserid(String userid) {
    this.userid = userid;
  }

  public Date getCreatetime() {
    return createtime;
  }

  public void setCreatetime(Date createtime) {
    this.createtime = createtime;
  }

  public Date getUpdatetime() {
    return updatetime;
  }

  public void setUpdatetime(Date updatetime) {
    this.updatetime = updatetime;
  }

  public String getIspublic() {
    return ispublic;
  }

  public void setIspublic(String ispublic) {
    this.ispublic = ispublic;
  }

  public String getIstop() {
    return istop;
  }

  public void setIstop(String istop) {
    this.istop = istop;
  }

  public Integer getVisits() {
    return visits;
  }

  public void setVisits(Integer visits) {
    this.visits = visits;
  }

  public Integer getThumbup() {
    return thumbup;
  }

  public void setThumbup(Integer thumbup) {
    this.thumbup = thumbup;
  }

  public Integer getComment() {
    return comment;
  }

  public void setComment(Integer comment) {
    this.comment = comment;
  }

  public String getChannelid() {
    return channelid;
  }

  public void setChannelid(String channelid) {
    this.channelid = channelid;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return "Article{" +
        "id='" + id + '\'' +
        ", title='" + title + '\'' +
        ", content='" + content + '\'' +
        ", state='" + state + '\'' +
        ", image='" + image + '\'' +
        ", columnid='" + columnid + '\'' +
        ", userid='" + userid + '\'' +
        ", createtime=" + createtime +
        ", updatetime=" + updatetime +
        ", ispublic='" + ispublic + '\'' +
        ", istop='" + istop + '\'' +
        ", visits=" + visits +
        ", thumbup=" + thumbup +
        ", comment=" + comment +
        ", channelid='" + channelid + '\'' +
        ", url='" + url + '\'' +
        ", type='" + type + '\'' +
        '}';
  }
}
