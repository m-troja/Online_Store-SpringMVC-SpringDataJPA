package com.itbulls.learnit.onlinestore.web.filters;

import static com.itbulls.learnit.onlinestore.web.controllers.SignInController.*;

import java.io.IOException;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itbulls.learnit.onlinestore.persistence.entities.Role;
import com.itbulls.learnit.onlinestore.persistence.entities.User;
import com.itbulls.learnit.onlinestore.web.controllers.SignInController;

@WebFilter(urlPatterns = {"/management-fulfilment", "/management-orders"})
public class ManagementFilter implements Filter {
       
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		User user = (User)((HttpServletRequest)request).getSession().getAttribute(LOGGED_IN_USER_ATTR);
		if (user != null) 
		{
			Set<Role> roles = user.getRoles();
            for (Role role : roles) 
            {
                if (MANAGER_ROLE_NAME.equals(role.getName())) 
                {
                	chain.doFilter(request, response);
                } 
                else 
                {
                	((HttpServletResponse)response).sendError(403);
                }
            }
		}
        else 
        {
			((HttpServletResponse)response).sendRedirect(
					request.getScheme()
				      + "://"
				      + request.getServerName()
				      + ":"
				      + request.getServerPort()
				      + request.getServletContext().getContextPath() + "/signin");
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}

}
