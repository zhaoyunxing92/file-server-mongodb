package com.sunny.mongodb.file.model;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * @author sunny
 * @class: com.sunny.mongodb.file.model.document
 * @date: 2018-05-18 18:08
 * @des: 文件
 */
public class Document implements Serializable {
  private static final long serialVersionUID = 3908802747467893799L;
  /**
   * 主键id
   */
 // @Id
  private String id;
  /**
   * 文件md5
   */
  private String md5;
  /**
   * 文件大小
   */
  private long size;
  /**
   * 文件内容
   */
  private Binary content;

  /**
   * 文件类型
   */
  private String contentType;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getMd5() {
    return md5;
  }

  public void setMd5(String md5) {
    this.md5 = md5;
  }

  public long getSize() {
    return size;
  }

  public void setSize(long size) {
    this.size = size;
  }

  public Binary getContent() {
    return content;
  }

  public void setContent(Binary content) {
    this.content = content;
  }

  public String getContentType() {
    return contentType;
  }

  public void setContentType(String contentType) {
    this.contentType = contentType;
  }
}
