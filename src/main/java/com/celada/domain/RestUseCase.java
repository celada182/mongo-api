package com.celada.domain;

import com.celada.domain.entity.Model;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@AllArgsConstructor
@Component
public class RestUseCase {

  private EventService eventService;

  public void create(Model model) throws ExecutionException, InterruptedException, JsonProcessingException {
    eventService.create(model);
  }

  public Model read(String id) throws ExecutionException, InterruptedException, IOException {
    return eventService.read(id);
  }

}
