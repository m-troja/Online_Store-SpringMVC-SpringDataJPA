package com.michal.onlinestore.web.security;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.michal.onlinestore.persistence.entities.User;
import com.michal.onlinestore.core.facades.UserFacade;

public class DefaultAuthenticationSuccessHandler implements AuthenticationSuccessHandler 
{
	public static final String LOGGED_IN_USER_ATTR = "loggedInUser";
    public static final String ADMIN_ROLE_NAME = "ROLE_ADMIN";
    public static final String MANAGER_ROLE_NAME = "ROLE_MANAGER";
    public static final String CUSTOMER_ROLE_NAME = "ROLE_CUSTOMER";

	Logger LOGGER = LogManager.getLogger(DefaultAuthenticationSuccessHandler.class);
	
	@Autowired
	UserFacade userFacade;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException 
	{
		User user = userFacade.getUserByEmail(authentication.getName());
		HttpSession session = request.getSession();
		String contextPath = request.getServletContext().getContextPath();
		LOGGER.info("DefaultAuthenticationSuccessHandler: Session is requested!");
		
		if (user != null)
		{
			session.setAttribute(LOGGED_IN_USER_ATTR, user);
			session.setAttribute(DefaultAuthenticationFailureHandler.UNSUCCESFUL_LOGIN_COUNT_ATTR_KEY, null);
			LOGGER.info("onAuthenticationSuccess: User with ID {} is added to the session", user.getId());
			Cookie[] cookies = ((HttpServletRequest) request).getCookies();
			for (Cookie cookie : cookies) {
				LOGGER.info("onAuthenticationSuccess: Cookie name: " +  cookie.getName() + ", value: "+ cookie.getValue());
			}
			if (user.getRoles().stream().map(role -> role.getName()).collect(Collectors.toList()).contains(ADMIN_ROLE_NAME) )
				{
					LOGGER.info("onAuthenticationSuccess: User with ID {} is redirected to the admin panel", user.getId());
					response.sendRedirect(contextPath + "/admin/panel");
				}
			else
			{
				LOGGER.info("User with ID {} is redirected to the homepage", user.getId());
				response.sendRedirect(contextPath + "/homepage");
			}
		}
	}
	
}