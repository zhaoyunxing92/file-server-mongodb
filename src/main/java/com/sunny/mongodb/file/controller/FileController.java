package com.sunny.mongodb.file.controller;

import com.sunny.mongodb.file.model.File;
import com.sunny.mongodb.file.service.FileService;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * @author sunny
 * @class: com.sunny.mongodb.file.controller.FileController
 * @date: 2018-05-18 17:53
 * @des: 文件控制器
 */
@RestController
@RequestMapping("/file")
public class FileController {
  @Autowired
  private FileService fileService;
  @Value("${server.address}")
  private String serverAddress;

  @Value("${server.port}")
  private String serverPort;

  @GetMapping("/{id}")
  public void handleFileUpload(@PathVariable String id, HttpServletResponse res) {

  }


  /**
   * 上传接口
   *
   * @param file
   * @return
   */
  @PostMapping
  public ResponseEntity<String> addFile(@RequestParam("file") MultipartFile file) {
    return ResponseEntity.status(HttpStatus.OK).body(fileService.addFile(file));
  }

}
