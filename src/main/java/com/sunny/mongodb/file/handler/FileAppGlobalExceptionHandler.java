package com.sunny.mongodb.file.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.util.Objects;

/**
 * @author sunny
 * @class: com.sunny.mongodb.file.handler.FileAppGlobalExceptionHandler
 * @date: 2018-05-22 0:23
 * @des: 全局异常处理
 */
@ControllerAdvice
public class FileAppGlobalExceptionHandler {
  /**
   * 文件超出限制异常
   *
   * @param ex
   * @return
   */
  @ExceptionHandler({MaxUploadSizeExceededException.class})
  public ResponseEntity<Object> fileSizeLimitException(MaxUploadSizeExceededException ex) {
    String msg = ex.getMessage();
    return ResponseEntity.status(HttpStatus.NOT_EXTENDED)
        .body(Objects.requireNonNull(msg).substring(msg.lastIndexOf(":") + 1));
  }
}
