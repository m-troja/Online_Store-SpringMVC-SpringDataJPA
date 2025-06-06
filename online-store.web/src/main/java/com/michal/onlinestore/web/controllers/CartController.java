package com.michal.onlinestore.web.controllers;

import java.math.BigDecimal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.michal.onlinestore.core.facades.CartFacade;
import com.michal.onlinestore.core.facades.ProductFacade;
import com.michal.onlinestore.persistence.entities.Cart;
import com.michal.onlinestore.persistence.entities.User;


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
		
		User user = (User) session.getAttribute(SignInController.LOGGED_IN_USER_ATTR);
		
		cartFacade.addToCart(
				(User) session.getAttribute(SignInController.LOGGED_IN_USER_ATTR),
				productFacade.getProductByGuid(productGuid));

		session.setAttribute("orderStatus", messageSource.getMessage("product.added.msg", null, LocaleContextHolder.getLocale()));
		
		return "redirect:/product?guid=" + productGuid;
	}
	
	@PostMapping(value = "/remove")
	public String remove(@RequestParam("itemId") Integer itemId, HttpSession session, @RequestParam("cartId") Integer cartId) {

		
		
		
		Cart newCart = cartFacade.removeItemFromCart(cartId, itemId);
		
		BigDecimal price = cartFacade.calculatePriceOfCart(newCart);

		session.setAttribute("cart", newCart);
		session.setAttribute("price", price);

		
		return "redirect:/cart" ;
	}
	
	@PostMapping(value = "/clear")
	public String doClear(@RequestParam Integer cartId, HttpSession session)
	{
		User user = (User) session.getAttribute(SignInController.LOGGED_IN_USER_ATTR);

		if (cartFacade.findCartById(cartId) == null)
		{
			session.setAttribute("cart", cartFacade.findByUser(user) );

			return "redirect:/homepage";
		}
		

		cartFacade.deleteCart(cartId);
		
		session.setAttribute("cart", cartFacade.findByUser(user) );

		return "redirect:/cart";
	}

	public CartController(CartFacade cartFacade) {
		super();
		this.cartFacade = cartFacade;
	}
	
}
