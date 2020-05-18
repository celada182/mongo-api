package com.celada.adapter.in.kafka;

import com.celada.domain.entity.Model;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;

import java.io.IOException;

@AllArgsConstructor
public class EventMapper {

  private ObjectMapper objectMapper;

  public Model execute(String body) throws InvalidEventException, IOException {
    if (body == null) {
      return null;
    }
    return objectMapper.readValue(body, Model.class);
  }

  public String execute(Model model) throws InvalidEventException, IOException {
    if (model == null) {
      return null;
    }
    return objectMapper.writeValueAsString(model);
  }
}
