package com.itbulls.learnit.onlinestore.web.filters;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.itbulls.learnit.onlinestore.core.facades.CartFacade;
import com.itbulls.learnit.onlinestore.persistence.entities.Cart;
import com.itbulls.learnit.onlinestore.persistence.entities.CartItem;
import com.itbulls.learnit.onlinestore.persistence.entities.User;
import com.itbulls.learnit.onlinestore.web.controllers.SignInController;

import java.io.IOException;

@WebFilter("/*")
public class CartSizeFilter implements Filter {

	@Autowired
	private CartFacade cartFacade;
	
    public static final String CART_ITEM_COUNT_ATTRIBUTE = "cartItemCount";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // Cast to HttpServletRequest to access getSession method
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        // Retrieve the existing session without creating a new one
        if (httpRequest.getSession(false) != null)
        {
	     
	        HttpSession session = httpRequest.getSession(false);
	
	        if ( session.getAttribute(SignInController.LOGGED_IN_USER_ATTR) != null) 
	    	{
		        int cartItemCount = 0;
		
	            // Retrieve the logged-in user from the session
	            User user = (User) session.getAttribute(SignInController.LOGGED_IN_USER_ATTR);
	
                // Fetch the user's cart
                Cart cart = cartFacade.findByUser(user);

                if (cart != null) 
                {
                    // Calculate the cart size
                    cartItemCount = cart.getItems().stream()
                            .mapToInt(CartItem::getQuantity)
                            .sum();

                    // Set the cart size as a request attribute
                    request.setAttribute(CART_ITEM_COUNT_ATTRIBUTE, cartItemCount);
                }
	    	}
        }
        // Continue with the next filter in the chain
        chain.doFilter(request, response);
        
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    	
//    	Filter are initiated by Tomcat, here we need to inject filter into Spring because of Autowire of CartFacade
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, filterConfig.getServletContext());

    }

    @Override
    public void destroy() {
        // Cleanup code, if needed
    }
}
