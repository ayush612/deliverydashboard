package com.ibm.big.deliverydashboard.dao.mongo;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Update.update;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.springframework.data.mongodb.core.query.Query.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.ibm.big.deliverydashboard.beans.user.User;
import com.mongodb.WriteResult;

public class MongoUserRepositoryImpl implements MongoUserRepositoryCustom
{

	private static final Logger logger = LogManager.getLogger(MongoConfiguration.class);

	@Autowired
	MongoTemplate mongoTemplate;
	
	@Override
	public long updatePassword(String email, String password)
	{
		logger.debug("updating password of user with email = " + email);
		WriteResult wr = mongoTemplate.updateFirst(query(where("email").is(email)), update("password", password), User.class);
		logger.debug("update result = " + wr.getN());
		return wr.getN();
	}

	@Override
	public long updateBand(String email, String band)
	{
		logger.debug("updating band of user with email = " + email);
		WriteResult wr = mongoTemplate.updateFirst(query(where("email").is(email)), update("band", band), User.class);
		logger.debug("update result = " + wr.getN());
		return wr.getN();
	}

}
