package com.ibm.big.deliverydashboard.services;

import com.ibm.big.deliverydashboard.beans.user.User;

public interface UserService
{
	public User createUser(User u) throws Exception;
	public User findByEmail(String email) throws Exception;
	public User findById(String id) throws Exception;
}
