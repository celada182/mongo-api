package com.celada.adapter.out.mongo;

import com.celada.domain.entity.Model;

import java.util.Optional;

public class ModelMapper {

  public static Model execute(ModelEntity model) {
    return Optional.ofNullable(model)
        .map(ModelMapper::model)
        .orElse(null);
  }

  public static ModelEntity execute(Model model) {
    return Optional.ofNullable(model)
        .map(ModelMapper::model)
        .orElse(null);
  }

  private static ModelEntity model(Model m) {
    return ModelEntity.builder()
        .id(m.getId())
        .amount(m.getAmount())
        .content(m.getContent())
        .build();
  }

  private static Model model(ModelEntity m) {
    return Model.builder()
        .id(m.getId())
        .amount(m.getAmount())
        .content(m.getContent())
        .build();
  }
}
