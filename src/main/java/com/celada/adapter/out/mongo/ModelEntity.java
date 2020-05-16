package com.celada.adapter.out.mongo;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "models")
@Builder
@Data
public class ModelEntity {

  @Id
  private String id;
  private Integer amount;
  private String content;
}
