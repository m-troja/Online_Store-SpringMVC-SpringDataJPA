package com.michal.onlinestore.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.michal.onlinestore.persistence.entities.Purchase;
import com.michal.onlinestore.core.facades.PurchaseFacade;

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
