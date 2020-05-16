package com.celada.adapter.out.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelMongoRepository extends MongoRepository<ModelEntity, String> {

}
