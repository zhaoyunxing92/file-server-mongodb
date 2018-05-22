package com.sunny.mongodb.file.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author sunny
 * @class: com.sunny.mongodb.file.config.FileWebMvcConfigurer
 * @date: 2018-05-22 14:13
 * @des:
 */
public class FileWebMvcConfigurer implements WebMvcConfigurer {
  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
        .allowedOrigins("*")
        .allowCredentials(true)
        .allowedMethods("GET", "POST", "DELETE", "PUT")
        .maxAge(3600);

  }
}
