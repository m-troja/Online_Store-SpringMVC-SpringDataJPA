package com.itbulls.learnit.onlinestore.core.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itbulls.learnit.onlinestore.persistence.entities.User;

public interface UserManagementService {


	String registerUser(User user);

	List<User> getUsers();

	User getUserByEmail(String userEmail);
	
	void resetPasswordForUser(User user);

}
