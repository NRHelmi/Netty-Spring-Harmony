package com.logicblox.repository;

import com.logicblox.entity.RequestEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends MongoRepository<RequestEntity, String> {
  @Override
  public List<RequestEntity> findAll();
}
