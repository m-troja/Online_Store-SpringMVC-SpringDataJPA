package com.itbulls.learnit.onlinestore.web.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itbulls.learnit.onlinestore.core.facades.CartFacade;
import com.itbulls.learnit.onlinestore.core.facades.ProductFacade;
import com.itbulls.learnit.onlinestore.persistence.entities.Cart;
import com.itbulls.learnit.onlinestore.persistence.entities.Product;
import com.itbulls.learnit.onlinestore.persistence.entities.User;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private ProductFacade productFacade;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private CartFacade cartFacade;
	
	@GetMapping()
	public String doGet( HttpSession session) {
		User user = (User)session.getAttribute(SignInController.LOGGED_IN_USER_ATTR);
		
		if (cartFacade.findByUser(user) != null)
		{
			Cart cart = cartFacade.findByUser(user);
			BigDecimal price = cartFacade.calculatePriceOfCart(cart);
			session.setAttribute("cart", cart);
			session.setAttribute("price", price);
			
			System.out.println("GET Cart : " + cart.toString());
			System.out.println("GET Cart getSizeOfCart : " + cartFacade.getSizeOfCart(cart));
			return "cart";
		}
		else 
		{
				session.setAttribute("errorMsg", "Your cart is empty!" );
			
			return "cart";
		}
	}
	
	@GetMapping(value = "/add")
	public String add(@RequestParam("guid") String productGuid, HttpSession session) {
		
		cartFacade.addToCart(
				(User) session.getAttribute(SignInController.LOGGED_IN_USER_ATTR),
				productFacade.getProductByGuid(productGuid));

		session.setAttribute("orderStatus", messageSource.getMessage("product.added.msg", null, LocaleContextHolder.getLocale()));
		
		return "redirect:/product?guid=" + productGuid;
	}
	
	@PostMapping(value = "/remove")
	public String remove(@RequestParam("productId") Integer productId, HttpSession session, Integer cartId) {
		
		Cart freshCart = cartFacade.findCartById(cartId);
		System.out.println("freshCart from DB: " + freshCart.getProducts());

		
		System.out.println("productId : " + productId );
		System.out.println("cartId : " + cartId );

		
		Cart newCart = cartFacade.removeProductFromCart(cartId, productId);
		
		
	
		

		
		BigDecimal price = cartFacade.calculatePriceOfCart(newCart);

		System.out.println("Size of cart sent to JSP : " + cartFacade.getSizeOfCart(newCart) );
		session.setAttribute("cart", newCart);
		session.setAttribute("price", price);
		
		return "redirect:/cart" ;
	}

}
