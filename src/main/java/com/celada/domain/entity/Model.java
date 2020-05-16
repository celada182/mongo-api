package com.celada.domain.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Model {

  private String id;
  private Integer amount;
  private String content;
}
