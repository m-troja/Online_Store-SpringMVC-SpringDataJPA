package com.michal.onlinestore.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

<<<<<<< HEAD
import com.michal.onlinestore.persistence.entities.Purchase;
import com.michal.onlinestore.core.facades.PurchaseFacade;
=======
import com.michal.onlinestore.core.facades.PurchaseFacade;
import com.michal.onlinestore.persistence.entities.Purchase;
>>>>>>> 695085bb892170646e452eb0cdff3bf54a05b59b

@Controller
public class PurchaseController {
	
	@Autowired
	private PurchaseFacade purchaseFacade;
	
	@GetMapping("/management-orders")
	public String doGet(Model model) {
		List<Purchase> purchases = purchaseFacade.getNotCompletedPurchases();
		model.addAttribute("purchases", purchases);
		return "orders";
	}

}
