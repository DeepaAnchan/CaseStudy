package com.example.auditRabbitMqMicroservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.auditRabbitMqMicroservice.model.MessageModel;


@Repository
public interface MessageRepository extends MongoRepository<MessageModel, String> {

}
