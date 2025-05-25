package com.itbulls.learnit.onlinestore.web.controllers;
import java.util.Enumeration;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itbulls.learnit.onlinestore.core.facades.CartFacade;
import com.itbulls.learnit.onlinestore.core.facades.PurchaseFacade;
import com.itbulls.learnit.onlinestore.persistence.entities.Cart;
import com.itbulls.learnit.onlinestore.persistence.entities.Product;
import com.itbulls.learnit.onlinestore.persistence.entities.Purchase;
import com.itbulls.learnit.onlinestore.persistence.entities.User;

@Controller
public class CheckoutController {
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private CartFacade cartFacade;
	
	@Autowired
	private PurchaseFacade purchaseFacade;

	@PostMapping("/checkout")
	public String createPurchase(@RequestParam Integer cartId, HttpSession session) {
		
		User user = (User) session.getAttribute(SignInController.LOGGED_IN_USER_ATTR);
		Cart cart = cartFacade.findByUser(user);
		
//		Create purchase object and get this purchase object
		Purchase purchase = purchaseFacade.createPurchaseAndReturnPurchase(user, cart);
		
//		Clear the cart after purchase is created
		cartFacade.deleteCart(cartId);
		
//		Set message that purchase is created
		session.setAttribute("purchaseId", purchase.getId());

		return "redirect:/checkout";
	}
	
	@GetMapping("/checkout")
	public String doGet(HttpSession session) {

		if ( session.getAttribute("purchaseId") == null)
		{
			return "redirect:/homepage";
		}
		
//		Get purhcaseId from POST method to later get purchase object
		Integer purchaseId = (Integer) session.getAttribute("purchaseId");
		
//		Get purchase object
		Purchase purchase = purchaseFacade.getPurchaseById(purchaseId);
		
//		Get list of product from purchase object
		Set<Product> productsFromPurchase = purchase.getProducts();

		session.setAttribute("orderStatus", messageSource.getMessage("checkout.created.msg", null, LocaleContextHolder.getLocale()));
		session.setAttribute("productsFromPurchase", productsFromPurchase);
		
		session.removeAttribute("purchaseId");
		session.removeAttribute("cart");
		session.removeAttribute("purchaseId");

		  Enumeration<String> attributeNames = session.getAttributeNames();
		    while (attributeNames.hasMoreElements()) {
		        String name = attributeNames.nextElement();
		        Object value = session.getAttribute(name);
		        System.out.println(name + " : " + value);
		    }
		    
		return "checkout";
	}
	
}
