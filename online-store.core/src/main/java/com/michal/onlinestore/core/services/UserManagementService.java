package com.michal.onlinestore.core.services;

import java.util.List;

import com.michal.onlinestore.persistence.entities.User;

public interface UserManagementService {


	String registerUser(User user);

	List<User> getUsers();

	User getUserByEmail(String userEmail);
	
	void resetPasswordForUser(User user);

}
