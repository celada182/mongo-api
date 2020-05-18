package com.celada.domain;

import com.celada.domain.entity.Model;
import com.celada.domain.exception.ModelException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
@Slf4j
public class ModelUseCase {

  private final ModelRepository modelRepository;

  public void create(Model model) {
    log.info("Creating model: {}", model);
    modelRepository.create(model);
  }

  public Model read(String id) throws ModelException {
    log.info("Reading model: {}", id);
    return modelRepository.read(id);
  }

}
