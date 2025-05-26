package com.michal.onlinestore.core.facades.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

<<<<<<< HEAD
import com.michal.onlinestore.persistence.entities.Role;
import com.michal.onlinestore.persistence.repo.JpaRoleRepo;
import com.michal.onlinestore.core.facades.RoleFacade;
=======
import com.michal.onlinestore.core.facades.RoleFacade;
import com.michal.onlinestore.persistence.entities.Role;
import com.michal.onlinestore.persistence.repo.JpaRoleRepo;
>>>>>>> 695085bb892170646e452eb0cdff3bf54a05b59b


@Repository
public class DefaultRoleFacade implements RoleFacade {

	@Autowired
	JpaRoleRepo<Role> roleRepo;
	
	@Override
	public Role setRoleByRoleName(String roleName) {
		return roleRepo.findByName(roleName);
	}
	
}
