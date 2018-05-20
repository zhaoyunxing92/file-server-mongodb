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
//    Criteria criteria = Criteria.where("addresses").elemMatch(Criteria.where("name").is("朝阳区"));
//    Query query = new Query(criteria);
//    Document doc = mongoTemplate.findOne(query, Document.class);
    try {
      String md5 = MD5Util.getMD5(file.getInputStream());

      File files = new File();
      files.setContentType(file.getContentType());
      files.setName(file.getOriginalFilename());
      files.setMd5(md5);
      files.setSize(file.getSize());
      files.setContent(new Binary(file.getBytes()));
      //mongoTemplate.save(files);
      mongoTemplate.insert(files,"fs");
      return files.getId();
    } catch (NoSuchAlgorithmException e) {

    } catch (IOException e) {
    }

    return null;
  }
}
