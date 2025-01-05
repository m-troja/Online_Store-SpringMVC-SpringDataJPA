package com.itbulls.learnit.onlinestore.core.facades.impl;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itbulls.learnit.onlinestore.core.facades.UserFacade;
import com.itbulls.learnit.onlinestore.core.services.AffiliateMarketingService;
import com.itbulls.learnit.onlinestore.core.services.UserManagementService;
import com.itbulls.learnit.onlinestore.persistence.entities.Role;
import com.itbulls.learnit.onlinestore.persistence.entities.User;
import com.itbulls.learnit.onlinestore.persistence.repo.JpaRoleRepo;
import com.itbulls.learnit.onlinestore.persistence.repo.JpaUserRepo;

@Repository
public class DefaultUserFacade implements UserFacade {

    public static final String CUSTOMER_ROLE_NAME = "ROLE_CUSTOMER";

    
	@Autowired
	DefaultRoleFacade roleFacade;
	
	@Autowired
	private UserManagementService userManagement;
	
	@Autowired
	JpaUserRepo<User> userRepo;
	
	@Autowired
	JpaRoleRepo<Role> roleRepo;
	
	@Autowired
	private AffiliateMarketingService marketingService;
	

	@Override
	public void registerUser(User user, String referrerCode) {
		Set<Role> roles = new HashSet<>();
		Role role =  roleRepo.findByName(CUSTOMER_ROLE_NAME);
		roles.add(role);
		System.out.println(role.toString());
		user.setRoles(roles);
		user.setPartnerCode(marketingService.generateUniquePartnerCode());
		user.setReferrerUser(userRepo.findByPartnerCode(referrerCode));
		user.setEnabled(true);
		userManagement.registerUser(user);
	}


	@Override
	public User getUserByEmail(String email) {
		return (userManagement.getUserByEmail(email));
	}

	@Override
	public List<User> getUsers() {
		return (userManagement.getUsers());
	}

	@Override
	public User getUserById(Integer userId) {
		return (userRepo.findById(userId).orElse(null));
	}

	@Override
	public void updateUser(User referrerUser) {
		userRepo.save(referrerUser);
	}

	@Override
	public List<User> getReferralsForUser(User loggedInUser) {
		return userRepo.findByReferrerUser(loggedInUser);
	}
	
	
	public List<User> findByFirstNameCaseInsensitive(String firstName)
	{
		return userRepo.findByFirstNameCaseInsensitive(firstName);
	}
	
	public List<User> getAllUsersOrderByFirstName()
	{
		return userRepo.findAllOrderByFirstName();
	}


	@Override
	public void deleteUser(Integer id) {
		userRepo.delete(getUserById(id));
		
	}


	@Override
	public boolean addUser(User user) {
		userRepo.save(user);		
		return user.getEnabled();
	}

}
