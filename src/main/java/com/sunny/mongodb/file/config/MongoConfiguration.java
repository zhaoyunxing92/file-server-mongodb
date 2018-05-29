package com.sunny.mongodb.file.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author sunny
 * @class: com.sunny.mongodb.file.config.MongoConfiguration
 * @date: 2018-05-29 10:59
 * @des:
 */
//@EnableMongoRepositories() SimpleMongoDbFactory
public class MongoConfiguration {
  @Bean
  public MongoDbFactory mongoDbFactory() {
    //String database = new MongoClientURI(mongoUrl).getDatabase();
    return null;
//    return new SimpleMongoDbFactory(mongoClient(),database);
  }

  @Bean(name = "mongoTemplate")
  public MongoTemplate mongoTemplate() {
    return new MongoTemplate(mongoDbFactory());
  }

}
