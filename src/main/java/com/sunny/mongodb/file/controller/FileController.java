package com.sunny.mongodb.file.controller;


import com.sunny.mongodb.file.model.File;
import com.sunny.mongodb.file.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author sunny
 * @class: com.sunny.mongodb.file.controller.FileController
 * @date: 2018-05-18 17:53
 * @des: 文件控制器
 */
@RestController
@RequestMapping("/file")
public class FileController {
  private final FileService fileService;

//  @Value("${server.address}")
//  private String serverAddress;
  @Value("${server.port}")
  private String serverPort;

  public FileController(FileService fileService) {
    this.fileService = fileService;
  }

  /**
   * 获取图片
   * http://localhost:8101/file/5b02fadd973bb623f4706671?process=w_100,h_100
   *
   * @param id
   * @param process
   * @return
   */
  @GetMapping("/img/{id}")
  public ResponseEntity<Object> getFileById(@PathVariable String id, @RequestParam(name = "process", required = false) String process) {
    File file = fileService.getProcessFileById(id, process);
    if (file != null) {
      return ResponseEntity.ok()
          //.header(HttpHeaders.ACCEPT_ENCODING, "gbk")
          .header(HttpHeaders.ETAG, file.getMd5())
//          .header(HttpHeaders.SERVER, serverAddress)
          //.header(HttpHeaders.CONTENT_DISPOSITION, "name=\"" + file.getName() + "\"")
          .header(HttpHeaders.CONTENT_TYPE, file.getContentType())
          .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(file.getSize()))
          .header("Connection", "keep-alive")
          .body(file.getContent().getData());
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("img [%s] not found", id));
    }
  }

  /**
   * 获取全部图片
   *
   * @param start
   * @param item
   * @return
   */
  @GetMapping("/imgs/{start}")
  public ResponseEntity<Object> getAll(@PathVariable Integer start, @RequestParam(name = "process", required = false, defaultValue = "20") Integer item) {
    List<File> file = fileService.getAll(start, item);
    return ResponseEntity.ok()
        .body(file);
  }

  /**
   * 上传接口
   *
   * @param file
   * @return
   */
  @PostMapping("/img")
  public ResponseEntity<Object> addFile(@RequestParam("file") MultipartFile file) {
    return ResponseEntity.ok().body(fileService.addFile(file));
  }

  /**
   * 根据id删除图片
   *
   * @param id
   * @return
   */
  @DeleteMapping("/img/{id}")
  public ResponseEntity<Object> delFile(@PathVariable String id) {
    fileService.removeFile(id);
    return ResponseEntity.ok().body(String.format("img [%s] del ok", id));
  }
}
