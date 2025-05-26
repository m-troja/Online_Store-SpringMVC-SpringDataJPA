package com.michal.onlinestore.core.facades.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.michal.onlinestore.persistence.entities.Role;
import com.michal.onlinestore.persistence.repo.JpaRoleRepo;
import com.michal.onlinestore.core.facades.RoleFacade;


@Repository
public class DefaultRoleFacade implements RoleFacade {

	@Autowired
	JpaRoleRepo<Role> roleRepo;
	
	@Override
	public Role setRoleByRoleName(String roleName) {
		return roleRepo.findByName(roleName);
	}
	
}
