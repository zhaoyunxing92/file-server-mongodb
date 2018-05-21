package com.sunny.mongodb.file.model;

import java.io.Serializable;

/**
 * @author sunny
 * @class: com.sunny.mongodb.file.model.File
 * @date: 2018-05-18 18:06
 * @des: 文件
 */

public class File extends Document implements Serializable {
  private static final long serialVersionUID = 3718103350794331126L;
  /**
   * 文档id
   */
  private String docId;
  /**
   * 文件名称
   */
  private String name;

  public String getDocId() {
    return docId;
  }

  public void setDocId(String docId) {
    this.docId = docId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "File{" +
        "docId='" + docId + '\'' +
        ", name='" + name + '\'' +
        ", id='" + getId() + '\'' +
        ", md5='" + getMd5() + '\'' +
        ", size=" + getSize() +
        ", content=" + getContent() +
        ", contentType='" + getContentType() + '\'' +
        ", uploadDate=" + getUploadDate() +
        "} ";
  }
}
