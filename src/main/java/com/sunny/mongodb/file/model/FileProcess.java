package com.sunny.mongodb.file.model;

import java.io.Serializable;

/**
 * @author sunny
 * @class: com.sunny.mongodb.file.model.FileProcess
 * @date: 2018-05-22 10:44
 * @des:
 */
public class FileProcess implements Serializable {
  private static final long serialVersionUID = 1770399523395749814L;
  /**
   * 宽
   */
  private int width;
  /**
   * 高
   */
  private int height;

  public int getWidth() {
    return width > 0 ? width : 0;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public int getHeight() {
    return height > 0 ? height : 0;
  }

  public void setHeight(int height) {
    this.height = height;
  }
}
