package com.celada.boot.config;

import com.celada.domain.EventService;
import com.celada.domain.EventUseCase;
import com.celada.domain.ModelRepository;
import com.celada.domain.RestUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

  @Bean
  public EventUseCase modelUseCase(ModelRepository modelRepository) {
    return new EventUseCase(modelRepository);
  }

  @Bean
  public RestUseCase restUseCase(EventService eventService) {
    return new RestUseCase(eventService);
  }
}
