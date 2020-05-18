package com.celada.domain;

import com.celada.domain.entity.Model;
import com.celada.domain.exception.ModelException;

public interface ModelRepository {

  void create(Model model);

  Model read(String id) throws ModelException;

  void update(Model model);

  void delete(String id);

}
