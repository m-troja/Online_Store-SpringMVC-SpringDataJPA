package com.michal.onlinestore.web.controllers;
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

<<<<<<< HEAD
=======
import com.michal.onlinestore.core.facades.CartFacade;
import com.michal.onlinestore.core.facades.PurchaseFacade;
>>>>>>> 695085bb892170646e452eb0cdff3bf54a05b59b
import com.michal.onlinestore.persistence.entities.Cart;
import com.michal.onlinestore.persistence.entities.Product;
import com.michal.onlinestore.persistence.entities.Purchase;
import com.michal.onlinestore.persistence.entities.User;
<<<<<<< HEAD
import com.michal.onlinestore.core.facades.CartFacade;
import com.michal.onlinestore.core.facades.PurchaseFacade;
=======
>>>>>>> 695085bb892170646e452eb0cdff3bf54a05b59b

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
<<<<<<< HEAD
=======
		        System.out.println(name + " : " + value);
>>>>>>> 695085bb892170646e452eb0cdff3bf54a05b59b
		    }
		    
		return "checkout";
	}
	
}
