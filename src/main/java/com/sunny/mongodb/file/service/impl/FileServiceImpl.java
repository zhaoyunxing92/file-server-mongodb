package com.sunny.mongodb.file.service.impl;

import com.sunny.mongodb.file.model.Document;
import com.sunny.mongodb.file.model.File;
import com.sunny.mongodb.file.service.FileService;
import com.sunny.mongodb.file.util.MD5Util;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * @author sunny
 * @class: com.sunny.mongodb.file.service.impl.FileServiceImpl
 * @date: 2018-05-20 13:15
 * @des:
 */
@Service
public class FileServiceImpl implements FileService {
  @Autowired
  private MongoTemplate mongoTemplate;

  /**
   * 保存文件
   *
   * @param file
   * @return
   */
  @Override
  public String addFile(MultipartFile file) {
    try {
      File files = new File();
      files.setName(file.getOriginalFilename());
      files.setDocId(getDocIdByMd5(file));
      mongoTemplate.insert(files, "fs");
      return files.getId();
    } catch (NoSuchAlgorithmException e) {

    } catch (IOException e) {
    }

    return null;
  }

  /**
   * 根据md5获取文件
   * 存在时返回文件id，不存在时插入返回文件id
   *
   * @param file
   * @return
   */
  private String getDocIdByMd5(MultipartFile file) throws IOException, NoSuchAlgorithmException {
    String md5 = MD5Util.getMD5(file.getInputStream());
    Document doc = mongoTemplate.findOne(new Query(Criteria.where("md5").is(md5)), Document.class, "doc");
    if (doc == null) {
      doc = new Document();
      doc.setMd5(md5);
      doc.setContent(new Binary(file.getBytes()));
      doc.setSize(file.getSize());
      mongoTemplate.insert(doc, "doc");
    }
    return doc.getId();
  }
}
