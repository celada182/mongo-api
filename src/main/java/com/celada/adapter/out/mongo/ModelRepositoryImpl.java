package com.celada.adapter.out.mongo;

import com.celada.domain.ModelRepository;
import com.celada.domain.entity.Model;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class ModelRepositoryImpl implements ModelRepository {

  private final ModelMongoRepository repository;

  @Override
  public void create(Model model) {
    ModelEntity entity = ModelMapper.execute(model);
    log.info("Saving model: {}", entity);
    repository.save(entity);
  }

  @Override
  public Model read(String id) {
    return null;
  }

  @Override
  public void update(Model model) {

  }

  @Override
  public void delete(String id) {

  }
}
