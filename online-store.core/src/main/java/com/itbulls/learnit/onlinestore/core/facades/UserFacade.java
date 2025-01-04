package com.itbulls.learnit.onlinestore.core.facades;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itbulls.learnit.onlinestore.persistence.entities.User;

@Service
public interface UserFacade {

	void registerUser(User user, String partnerCode);

	User getUserByEmail(String email);
	
	List<User> getUsers();

	User getUserById(Integer userId);

	void updateUser(User referrerUser);

	List<User> getReferralsForUser(User loggedInUser);
}
