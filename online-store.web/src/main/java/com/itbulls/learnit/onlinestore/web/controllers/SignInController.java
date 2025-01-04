package com.itbulls.learnit.onlinestore.web.controllers;


import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itbulls.learnit.onlinestore.core.facades.UserFacade;
import com.itbulls.learnit.onlinestore.persistence.entities.Role;
import com.itbulls.learnit.onlinestore.persistence.entities.User;
import com.itbulls.learnit.onlinestore.web.utils.PBKDF2WithHmacSHA1EncryptionService;

@Controller
@RequestMapping("/signin")
public class SignInController {

    private static final String UNSUCCESSFUL_LOGIN_COUNT_ATTR_KEY = "UNSUCCESSFUL_LOGIN_COUNT";
    public static final String LOGGED_IN_USER_ATTR = "loggedInUser";
    private static final Logger LOGGER = LoggerFactory.getLogger(SignInController.class);
    public static final String ADMIN_ROLE_NAME = "ROLE_ADMIN";
    public static final String MANAGER_ROLE_NAME = "ROLE_MANAGER";
    public static final String CUSTOMER_ROLE_NAME = "ROLE_CUSTOMER";

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private PBKDF2WithHmacSHA1EncryptionService encryptionService;

    @GetMapping
    public String doGet() {
        return "signin";
    }

    @PostMapping
    public String doPost(@RequestParam String email, HttpSession session, @RequestParam String password) throws ServletException, IOException {
        User user = userFacade.getUserByEmail(email);
        LOGGER.debug("Password: {}", password);
        if (user == null) {
            LOGGER.warn("User with email {} does not exist", email);
            session.setAttribute("errMsg", "Invalid credentials");
            return "redirect:/signin";
        }
        
        LOGGER.debug("Stored password: {}", user.getPassword());
        LOGGER.debug("Password validation result: {}", encryptionService.validatePassword(password, user.getPassword()));

        if (encryptionService.validatePassword(password, user.getPassword())) {
            session.setAttribute(LOGGED_IN_USER_ATTR, user);
            LOGGER.info("User with ID {} is added to the session", user.getId());

            Set<Role> roles = user.getRoles();
            for (Role role : roles) {
                if (ADMIN_ROLE_NAME.equals(role.getName())) {
                    LOGGER.info("User with ID {} is redirected to the admin panel", user.getId());
                    return "redirect:/admin/panel";
                }
            }

            LOGGER.info("User with ID {} is redirected to the homepage", user.getId());
            return "redirect:/homepage";
        } else {
            Integer failedLoginCounter = (Integer) session.getAttribute(UNSUCCESSFUL_LOGIN_COUNT_ATTR_KEY);
            session.setAttribute(UNSUCCESSFUL_LOGIN_COUNT_ATTR_KEY, failedLoginCounter == null ? 1 : ++failedLoginCounter);
            LOGGER.warn("Unsuccessful login attempt #{}", failedLoginCounter);
            return "redirect:/signin";
        }
    }
}
