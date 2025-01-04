package com.itbulls.learnit.onlinestore.core.facades.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itbulls.learnit.onlinestore.core.facades.RoleFacade;
import com.itbulls.learnit.onlinestore.persistence.entities.Role;
import com.itbulls.learnit.onlinestore.persistence.repo.JpaRoleRepo;


@Repository
public class DefaultRoleFacade implements RoleFacade {

	@Autowired
	JpaRoleRepo<Role> roleRepo;
	
	@Override
	public Role setRoleByRoleName(String roleName) {
		return roleRepo.findByName(roleName);
	}
	
}
