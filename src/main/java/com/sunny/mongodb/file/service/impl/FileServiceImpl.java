package com.sunny.mongodb.file.service.impl;

import com.sunny.mongodb.file.model.Document;
import com.sunny.mongodb.file.model.File;
import com.sunny.mongodb.file.model.FileProcess;
import com.sunny.mongodb.file.service.FileService;
import com.sunny.mongodb.file.util.MD5Util;
import net.coobird.thumbnailator.Thumbnails;
import org.bson.types.Binary;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;


/**
 * @author sunny
 * @class: com.sunny.mongodb.file.service.impl.FileServiceImpl
 * @date: 2018-05-20 13:15
 * @des: 文件操作实现类
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
      files.setUploadDate(new Date());
      files.setContentType(file.getContentType());
      mongoTemplate.insert(files, "fs");
      return files.getId();
    } catch (NoSuchAlgorithmException e) {
      //ignore
      return "";
    } catch (IOException e) {
      //ignore
      return "";
    }
  }

  /**
   * 获取文件
   *
   * @param id
   * @return
   */
  @Override
  public File getFileById(String id) {
    File file = mongoTemplate.findOne(new Query(Criteria.where("id").is(id)), File.class, "fs");
    if (null == file) {
      return null;
    }
    Document doc = getDocById(file.getDocId());
    doc.setId(file.getId());
    doc.setUploadDate(file.getUploadDate());
    doc.setContentType(file.getContentType());
    BeanUtils.copyProperties(doc, file);
    return file;
  }

  /**
   * 处理图片
   *
   * @param id
   * @param process
   * @return
   */
  @Override
  public File getProcessFileById(String id, String process) {
    File file = getFileById(id);
    if (null == file) {
      return null;
    }
    if (null == process) {
      return file;
    }
    try {
      byte[] bytes = file.getContent().getData();
      //重新设置图片http://localhost:8101/file/5b02fadd973bb623f4706671?process=w_100,h_200
      byte[] resizeBytes = resizeImage(bytes, process);
      file.setContent(new Binary(resizeBytes));
      file.setSize(resizeBytes.length);
      file.setMd5(MD5Util.getMD5(resizeBytes));
    } catch (NoSuchAlgorithmException e) {
      //ignore
      return file;
    }
    return file;
  }

  /**
   * 根据md5获取文件
   * 存在时返回文件id，不存在时插入返回文件id
   *
   * @param file
   * @return
   */
  private String getDocIdByMd5(MultipartFile file) throws IOException, NoSuchAlgorithmException {
    byte[] bytes = contraction(file.getInputStream());
    String md5 = MD5Util.getMD5(bytes);
    Document doc = mongoTemplate.findOne(new Query(Criteria.where("md5").is(md5)), Document.class, "doc");
    if (doc == null) {
      doc = new Document();
      doc.setMd5(md5);
      doc.setContent(new Binary(bytes));
      doc.setSize(bytes.length);
      doc.setUploadDate(new Date());
      mongoTemplate.insert(doc, "doc");
    }
    return doc.getId();
  }

  /**
   * 根据id获取doc
   *
   * @param id
   * @return
   */
  private Document getDocById(String id) {
    return mongoTemplate.findOne(new Query(Criteria.where("id").is(id)), Document.class, "doc");
  }

  /**
   * 压缩返回字节数组
   * 原图压缩0.3
   * 参考链接：https://www.jianshu.com/p/ad8af8214e60
   * outputQuality是图片的质量，值也是在0到1，越接近于1质量越好，越接近于0质量越差。
   *
   * @param is
   * @return
   */
  private byte[] contraction(InputStream is) throws IOException {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    Thumbnails.of(is).scale(1f).outputQuality(0.3f).toOutputStream(out);
    return out.toByteArray();
  }

  /**
   * 设置图片大小
   *
   * @param bytes
   * @param process
   * @return
   */
  private byte[] resizeImage(byte[] bytes, String process) {
    try {
      ByteArrayOutputStream out = new ByteArrayOutputStream();
      InputStream in = new ByteArrayInputStream(bytes);
      FileProcess fileProcess = parseProcess(process);
      int width = fileProcess.getWidth(), height = fileProcess.getHeight();
      if (width != 0 && height != 0) {
        Thumbnails.of(in).size(width, height).keepAspectRatio(false).toOutputStream(out);
      } else if (width != 0) {
        Thumbnails.of(in).width(width).toOutputStream(out);
      } else if (height != 0) {
        Thumbnails.of(in).height(height).toOutputStream(out);
      } else {
        return bytes;
      }
      return out.toByteArray();
    } catch (IOException e) {
      return bytes;
    }
  }

  /**
   * 解析参数
   *
   * @param process
   * @return
   */
  private FileProcess parseProcess(String process) {
    FileProcess fileProcess = new FileProcess();
    if (process.isEmpty()) {
      return fileProcess;
    }
    String[] ps = process.split(",");
    for (int i = 0; i < ps.length; i++) {
      String param = ps[i];
      Integer value = Integer.valueOf(param.substring(2));
      if (param.startsWith("h_")) {
        fileProcess.setHeight(value);
      } else if (param.startsWith("w_")) {
        fileProcess.setWidth(value);
      }
    }
    return fileProcess;
  }

  /**
   * 获取图片列表
   *
   * @param start
   * @param item
   * @return
   */
  @Override
  public List<File> getAll(Integer start, Integer item) {
    int skip = (start-1)*item;

    return mongoTemplate.find(new Query().with(new Sort(Sort.Direction.DESC,"uploadDate")).limit(item).skip(skip), File.class, "fs");
  }
}
