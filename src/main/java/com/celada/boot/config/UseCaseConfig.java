package com.celada.boot.config;

import com.celada.domain.ModelRepository;
import com.celada.domain.ModelUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

  @Bean
  public ModelUseCase modelUseCase(ModelRepository modelRepository) {
    return new ModelUseCase(modelRepository);
  }

}
