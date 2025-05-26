package com.michal.onlinestore.core.facades;

import java.util.List;

import org.springframework.stereotype.Service;

import com.michal.onlinestore.persistence.entities.User;

/**
 * Facade interface providing operations related to User management.
 * This interface abstracts user-related business logic and interactions.
 */
@Service
public interface UserFacade {

    /**
     * Registers a new user with an optional partner (referrer) code.
     * Assigns the user roles and enables the account.
     *
     * @param user the User entity to register
     * @param partnerCode optional referrer code for affiliate marketing
     */
    void registerUser(User user, String partnerCode);

    /**
     * Retrieves a user by their email address.
     *
     * @param email the user's email
     * @return the User entity, or null if not found
     */
    User getUserByEmail(String email);
    
    /**
     * Retrieves all users.
     *
     * @return a list of all users in the system
     */
    List<User> getUsers();

    /**
     * Retrieves a user by their unique identifier.
     *
     * @param userId the user's id
     * @return the User entity, or null if not found
     */
    User getUserById(Integer userId);

    /**
     * Updates the details of an existing user.
     *
     * @param referrerUser the User entity to update
     */
    void updateUser(User referrerUser);

    /**
     * Retrieves a list of users referred by the specified user.
     *
     * @param loggedInUser the user whose referrals are to be fetched
     * @return list of referred users
     */
    List<User> getReferralsForUser(User loggedInUser);
    
    /**
     * Finds users whose first names match the given string, case-insensitively.
     *
     * @param firstName the first name to search for
     * @return list of users matching the first name
     */
    List<User> findByFirstNameCaseInsensitive(String firstName);

    /**
     * Retrieves all users ordered alphabetically by first name.
     *
     * @return list of users sorted by first name
     */
    List<User> getAllUsersOrderByFirstName();

    /**
     * Deletes the user identified by the given id.
     *
     * @param id the user's id to delete
     */
    void deleteUser(Integer id);

    /**
     * Adds a new user to the system.
     *
     * @param user the User entity to add
     * @return true if the user is enabled after addition; false otherwise
     */
    boolean addUser(User user);
}
