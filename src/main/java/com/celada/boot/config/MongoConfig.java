package com.celada.boot.config;

import com.celada.adapter.out.mongo.ModelMongoRepository;
import com.celada.adapter.out.mongo.ModelRepositoryImpl;
import com.celada.domain.ModelRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackageClasses = ModelMongoRepository.class)
public class MongoConfig {

  @Bean
  public ModelRepository modelRepository(ModelMongoRepository repository){
    return new ModelRepositoryImpl(repository);
  }

}
