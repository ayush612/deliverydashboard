package com.ibm.big.deliverydashboard.dao.mongo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ibm.big.deliverydashboard.beans.user.User;

public interface MongoUserRepository extends MongoRepository<User, String>
{
	List<User> findByBand(String band);
	User findByEmail(String email);
	List<User> findByFirstname(String firstname);
}
