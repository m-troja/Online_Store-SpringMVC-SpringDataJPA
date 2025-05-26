package com.michal.onlinestore.core.facades;

import org.springframework.stereotype.Service;

import com.michal.onlinestore.persistence.entities.Role;

/**
 * Facade interface for role-related operations.
 * Provides methods to manage user roles in the system.
 */
@Service
public interface RoleFacade {

    /**
     * Retrieves and sets a Role entity based on the given role name.
     *
     * @param roleName the name of the role to retrieve
     * @return the Role entity matching the role name
     */
    Role setRoleByRoleName(String roleName);
}
