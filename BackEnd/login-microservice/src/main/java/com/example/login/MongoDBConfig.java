package com.example.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;


@Configuration
public class MongoDBConfig {
	
	@Autowired
	private MongoDBPropertiesConfig mongoDbPropertiesConfig;

    @Bean
    @RefreshScope
	public MongoDatabaseFactory mongoDatabaseFactory() {
		MongoDatabaseFactory mongoDbFactory = new SimpleMongoClientDatabaseFactory(
				com.mongodb.client.MongoClients.create(mongoDbPropertiesConfig.getConnectionString()),
				mongoDbPropertiesConfig.getDatabaseName());
		return mongoDbFactory;
	}
}
