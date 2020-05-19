package com.celada.domain;

import com.celada.domain.entity.Model;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public interface EventService {

  void create(Model model) throws ExecutionException, InterruptedException, JsonProcessingException;

  Model read(String id) throws ExecutionException, InterruptedException, IOException;

  void update(Model model);

  void delete(String id);

}
