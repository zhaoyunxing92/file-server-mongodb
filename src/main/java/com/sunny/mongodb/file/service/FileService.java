package com.sunny.mongodb.file.service;

import com.sunny.mongodb.file.model.File;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author sunny
 * @class: com.sunny.mongodb.file.service.FileService
 * @date: 2018-05-18 18:04
 * @des:
 */
public interface FileService {
  /**
   * 保存文件
   *
   * @param file
   * @return
   */
  String addFile(MultipartFile file);

  /**
   * 获取文件
   *
   * @param id
   * @return
   */
  File getFileById(String id);
}
