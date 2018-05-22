package com.sunny.mongodb.file.model;

import org.bson.types.Binary;

import java.io.Serializable;
import java.util.Date;

/**
 * @author sunny
 * @class: com.sunny.mongodb.file.model.File
 * @date: 2018-05-18 18:06
 * @des: 文件
 */

public class File implements Serializable {
  private static final long serialVersionUID = 3718103350794331126L;
  /**
   * 主键id
   */
  private String id;
  /**
   * 文件名称
   */
  private String name;

  /**
   * 文件md5
   */
  private String md5;
  /**
   * 文件大小
   */
  private Integer size;
  /**
   * 文件内容
   */
  private Binary content;

  /**
   * 文件类型
   */
  private String contentType;
  /**
   * 上传时间
   */
  private Date uploadDate;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getMd5() {
    return md5;
  }

  public void setMd5(String md5) {
    this.md5 = md5;
  }

  public Integer getSize() {
    return size;
  }

  public void setSize(Integer size) {
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

  public Date getUploadDate() {
    return uploadDate;
  }

  public void setUploadDate(Date uploadDate) {
    this.uploadDate = uploadDate;
  }

  @Override
  public String toString() {
    return "File{" +
        "id='" + id + '\'' +
        ", name='" + name + '\'' +
        ", md5='" + md5 + '\'' +
        ", size=" + size +
        ", content=" + content +
        ", contentType='" + contentType + '\'' +
        ", uploadDate=" + uploadDate +
        '}';
  }
}
