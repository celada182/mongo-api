package com.celada.adapter.in.kafka;

import com.celada.domain.entity.Model;

import java.util.Optional;

public class EventMapper {

  public static Model execute(Object object) throws InvalidEventException {
    return Optional.ofNullable(object)
        .map(o -> Model.builder().content("FIXED").amount(2).build())
        .orElseThrow(() -> new InvalidEventException("Invalid event body"));
  }
}
