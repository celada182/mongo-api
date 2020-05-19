package com.celada.boot.config;

import com.celada.adapter.in.rest.ModelController;
import com.celada.domain.RestUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestConfig {

  @Bean
  public ModelController modelController(RestUseCase restUseCase) {
    return new ModelController(restUseCase);
  }

}
