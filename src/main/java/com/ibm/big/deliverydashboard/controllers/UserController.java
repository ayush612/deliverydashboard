package com.ibm.big.deliverydashboard.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

	@RequestMapping(method = RequestMethod.GET, value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<User> getUserByEmail(
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "id", required = false) String id)
	{
		ResponseEntity<User> response;
		try
		{
			User user = null;
			if (id != null)
			{
				user = userService.findById(id);
			}
			else if (email != null)
			{
				user = userService.findByEmail(email);
			}
				
			response = ResponseEntity.ok(user);
		} catch (Exception e)
		{
			logger.error(e);
			response = ResponseEntity.badRequest().body(null);
		}
		return response;
	}

	@RequestMapping(method = RequestMethod.PATCH, value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("#user.email == authentication.name")
	public @ResponseBody ResponseEntity<Long> updateUserPassword(@RequestBody(required = true) User user)
	{
		ResponseEntity<Long> response;
		try
		{
			long l = userService.updateUserPassword(user.getEmail(), user.getPassword());
			response = ResponseEntity.ok(l);
		} catch (Exception e)
		{
			logger.error(e);
			response = ResponseEntity.badRequest().body(null);
		}
		return response;
	}
}
