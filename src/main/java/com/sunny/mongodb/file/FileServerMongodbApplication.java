package com.sunny.mongodb.file;

import com.sunny.mongodb.file.config.FileWebMvcConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({FileWebMvcConfigurer.class})
public class FileServerMongodbApplication {

  public static void main(String[] args) {
    SpringApplication.run(FileServerMongodbApplication.class, args);
  }
}
