package com.ibm.big.deliverydashboard.dao.mongo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@Configuration
@EnableMongoRepositories (basePackages = "com/ibm/big/deliverydashboard/dao/mongo")
public class MongoConfiguration extends AbstractMongoConfiguration
{
	private static final Logger logger = LogManager.getLogger(MongoConfiguration.class);

	@Value("${mongodb.host}")
	String host;

	@Value("${mongodb.port}")
	int port;
	
	@Value("${mongodb.dbname}")
	String dbname;

	@Override
	protected String getDatabaseName()
	{
		logger.debug("Setting DB Name = " + dbname);
		return dbname;
	}

	@Override
	public Mongo mongo() throws Exception
	{
		logger.debug("Creating Mongo client with host = " + host + " port = " + port);
		return new MongoClient(host, port);
	}

}
