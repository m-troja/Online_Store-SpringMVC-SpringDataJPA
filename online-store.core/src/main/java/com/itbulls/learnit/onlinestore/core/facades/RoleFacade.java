package com.itbulls.learnit.onlinestore.core.facades;

import org.springframework.stereotype.Service;

import com.itbulls.learnit.onlinestore.persistence.entities.Role;

@Service
public interface RoleFacade {
	Role setRoleByRoleName(String roleName);
}
