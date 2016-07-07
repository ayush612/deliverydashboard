package com.ibm.big.deliverydashboard.services;

import java.util.Date;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ibm.big.deliverydashboard.beans.user.User;
import com.ibm.big.deliverydashboard.controllers.UserController;
import com.ibm.big.deliverydashboard.dao.elastic.ElasticUserRepository;
import com.ibm.big.deliverydashboard.dao.mongo.MongoUserRepository;

@Service
public class UserServiceImpl implements UserService
{
	private static final Logger logger = LogManager.getLogger(UserController.class);

	@Autowired
	MongoUserRepository userRepo;

	@Autowired
	ElasticUserRepository elasticRepo;

	@Override
	public User createUser(User user) throws Exception
	{
		logger.debug("Method Entered with argument user: " + user);
		if (user == null)
		{
			throw new IllegalArgumentException("Argument User can't be null");
		}

		if (user.getEmail() == null || user.getId() == null || user.getPassword() == null || user.getBand() == null
				|| user.getFirstname() == null || user.getLastname() == null)
		{
			throw new IllegalArgumentException("Missing Mandatory User Parameters");
		}

		EmailValidator ev = EmailValidator.getInstance();

		if (!ev.isValid(user.getEmail()))
		{
			throw new IllegalArgumentException("Invalid Email Address Specified");
		}

		if (findByEmail(user.getEmail()) != null)
		{
			throw new IllegalArgumentException("User Already Exists");
		}

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));

		user = userRepo.save(user);
		user.setPassword(null);
		user = elasticRepo.save(user);

		return user;
	}

	@Override
	public User findByEmail(String email) throws Exception
	{
		return userRepo.findByEmail(email);
	}

	@Override
	public User findById(String id) throws Exception
	{
		return userRepo.findOne(id);
	}

	@Override
	public User updateUserRestricted(User user) throws Exception
	{
		User u = userRepo.findByEmail(user.getEmail());

		if (u != null)
		{
			if (user.getDesignation() != null)
			{
				u.setDesignation(user.getDesignation());
			}

			if (user.getBand() != null)
			{
				u.setBand(user.getBand());
			}

			if (user.getRoles() != null)
			{
				u.setRoles(user.getRoles());
			}

			if (user.getTags() != null)
			{
				u.setTags(user.getTags());
			}

			logger.debug("Updated User = " + u);
			u.setUpdateddate(User.dateFormat.format(new Date()));
			userRepo.save(u);
			u.setPassword(null);
			// elasticRepo.delete(u.getId());
			elasticRepo.save(u);
		}
		return u;
	}

	@Override
	public User updateUser(User user) throws Exception
	{
		User u = userRepo.findByEmail(user.getEmail());
		
		if (u != null)
		{
			if (user.getFirstname() != null)
			{
				u.setFirstname(user.getFirstname());
			}
			if (user.getLastname() != null)
			{
				u.setLastname(user.getLastname());
			}
			if (user.getPhone() != null)
			{
				u.setPhone(user.getPhone());
			}
			if (user.getPassword() != null)
			{
				u.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
			}
			u.setUpdateddate(User.dateFormat.format(new Date()));
			userRepo.save(u);
			u.setPassword(null);
			// elasticRepo.delete(u.getId());
			elasticRepo.save(u);
		}
		return u;
	}

}
