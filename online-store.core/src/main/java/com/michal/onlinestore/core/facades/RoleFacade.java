package com.michal.onlinestore.core.facades;

import org.springframework.stereotype.Service;

import com.michal.onlinestore.persistence.entities.Role;

@Service
public interface RoleFacade {
	Role setRoleByRoleName(String roleName);
}
