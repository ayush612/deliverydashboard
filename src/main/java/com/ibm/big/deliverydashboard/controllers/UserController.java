package com.ibm.big.deliverydashboard.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.big.deliverydashboard.beans.user.User;
import com.ibm.big.deliverydashboard.services.UserService;

@RestController
public class UserController
{
	private static final Logger logger = LogManager.getLogger(UserController.class);
	
	@Autowired
	UserService userService;

	@RequestMapping(method = RequestMethod.POST, value = "/signup", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<User> createUser(@RequestBody(required = true) User user)
	{
		ResponseEntity<User> response;
		try
		{
			User p = userService.createUser(user);
			response = ResponseEntity.ok(p);
		} catch (Exception e)
		{
			logger.error(e);
			response = ResponseEntity.badRequest().body(null);
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/user/email/{email}/", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<User> getUserByEmail(@PathVariable("email") String email)
	{
		ResponseEntity<User> response;
		try
		{
			User user = userService.findByEmail(email);
			response = ResponseEntity.ok(user);
		} catch (Exception e)
		{
			logger.error(e);
			response = ResponseEntity.badRequest().body(null);
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/user/id/{id}/", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<User> getUserById(@PathVariable("id") String id)
	{
		ResponseEntity<User> response;
		try
		{
			User user = userService.findById(id);
			response = ResponseEntity.ok(user);
		} catch (Exception e)
		{
			logger.error(e);
			response = ResponseEntity.badRequest().body(null);
		}
		return response;
	}	
}
