package com.celada.domain;

import com.celada.domain.entity.Model;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ModelUseCase {

  private final ModelRepository modelRepository;

  public void create(Model model) {
    modelRepository.create(model);
  }

  public Model read(String id) {
    return modelRepository.read(id);
  }

}
