package com.michal.onlinestore.web.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.michal.onlinestore.persistence.entities.Purchase;
import com.michal.onlinestore.persistence.entities.User;
import com.michal.onlinestore.core.facades.PurchaseFacade;

@Controller
@RequestMapping("/my-orders")
public class MyOrdersController {
	
	@Autowired
	private PurchaseFacade purchaseFacade;
	
	@PostMapping
	public String doPost(@RequestParam Integer purchaseId)
	{
		purchaseFacade.markFulfilmentStageForPurchaseIdAsCompleted(purchaseId);
	 return "orders";
	}
	
	@GetMapping
	public String doGet(HttpSession session)
	{
		if ( session.getAttribute(SignInController.LOGGED_IN_USER_ATTR) == null)
		{
			return "redirect:/homepage";
		}
		
		User user = (User) session.getAttribute(SignInController.LOGGED_IN_USER_ATTR);
		Integer userId = user.getId();
		
		List<Purchase> purchases = purchaseFacade.getPurchasesByUserId(userId);
		
		session.setAttribute("purchases", purchases);
		
		return "my-orders";
	}

}
