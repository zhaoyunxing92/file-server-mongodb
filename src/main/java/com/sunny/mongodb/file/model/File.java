package com.sunny.mongodb.file.model;

import java.io.Serializable;

/**
 * @author sunny
 * @class: com.sunny.mongodb.file.model.File
 * @date: 2018-05-18 18:06
 * @des:
 */

public class File extends Document implements Serializable {
  private static final long serialVersionUID = 3718103350794331126L;
  /**
   * 文件名称
   */
  private String name;

  /**
   * 文件类型
   */
  private String contentType;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getContentType() {
    return contentType;
  }

  public void setContentType(String contentType) {
    this.contentType = contentType;
  }
}
