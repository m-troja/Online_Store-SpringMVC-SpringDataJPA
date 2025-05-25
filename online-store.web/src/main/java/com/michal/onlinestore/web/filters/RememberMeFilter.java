package com.michal.onlinestore.web.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.michal.onlinestore.core.facades.UserFacade;
import com.michal.onlinestore.persistence.entities.User;
import com.michal.onlinestore.web.security.DefaultAuthenticationSuccessHandler;


@WebFilter("/*")
public class RememberMeFilter implements Filter {
	
	Logger LOGGER = LogManager.getLogger(RememberMeFilter.class);
	private UserFacade userFacade;
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest)request).getSession();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		// If there is no logged in user in session
		if (session.getAttribute(DefaultAuthenticationSuccessHandler.LOGGED_IN_USER_ATTR) == null) {
//			LOGGER.info("RememberMeFilter: user == null");
//			LOGGER.info("RememberMeFilter: session.getAttribute(DefaultAuthenticationSuccessHandler.LOGGED_IN_USER_ATTR) = " + session.getAttribute(DefaultAuthenticationSuccessHandler.LOGGED_IN_USER_ATTR));
		}
			// If there is authenticated user - let's add it to the current session
			if (!(authentication instanceof AnonymousAuthenticationToken)) {
//				LOGGER.info("RememberMeFilter: session.getAttribute(DefaultAuthenticationSuccessHandler.LOGGED_IN_USER_ATTR) = " + session.getAttribute(DefaultAuthenticationSuccessHandler.LOGGED_IN_USER_ATTR));
//				LOGGER.info("RememberMeFilter: authentication instanceof AnonymousAuthenticationToken = " + (authentication instanceof AnonymousAuthenticationToken));

			    User user = userFacade.getUserByEmail(authentication.getName());
			    if (user != null) {
			    	session.setAttribute(DefaultAuthenticationSuccessHandler.LOGGED_IN_USER_ATTR, user);
//			    	LOGGER.info("RememberMeFilter: user = " + user.getEmail());
//					LOGGER.info("RememberMeFilter: session.getAttribute(DefaultAuthenticationSuccessHandler.LOGGED_IN_USER_ATTR) = " + ((User) (session.getAttribute(DefaultAuthenticationSuccessHandler.LOGGED_IN_USER_ATTR))).getEmail());
			    }
			}
		
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		 ApplicationContext ctx = WebApplicationContextUtils
			      .getRequiredWebApplicationContext(filterConfig.getServletContext());
		 this.userFacade = ctx.getBean(UserFacade.class);
	}

	@Override
	public void destroy() {
	}

}