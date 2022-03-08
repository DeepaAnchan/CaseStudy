package com.example.audit.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.audit.model.MessageModel;

@Repository
public interface MessageAuditRepository extends MongoRepository<MessageModel, String> {

}
