package com.celada.domain;

import com.celada.domain.entity.Model;

public interface ModelRepository {

  void create(Model model);

  Model read(String id);

  void update(Model model);

  void delete(String id);

}
