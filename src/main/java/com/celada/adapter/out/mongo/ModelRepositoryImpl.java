package com.celada.adapter.out.mongo;

import com.celada.domain.ModelRepository;
import com.celada.domain.entity.Model;
import com.celada.domain.exception.ModelException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

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
  public Model read(String id) throws ModelException {
    Optional<ModelEntity> entity = repository.findById(id);
    log.info("Read model: {}", entity);
    return entity
        .map(ModelMapper::execute)
        .orElseThrow(() -> new ModelException("Model by id " + id + " not found"));
  }

  @Override
  public void update(Model model) {

  }

  @Override
  public void delete(String id) {

  }
}
