package com.celada.adapter.out.mongo;

import com.celada.domain.ModelRepository;
import com.celada.domain.entity.Model;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ModelRepositoryImpl implements ModelRepository {

  private final ModelMongoRepository repository;

  @Override
  public void create(Model model) {
    ModelEntity entity = ModelMapper.execute(model);
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
