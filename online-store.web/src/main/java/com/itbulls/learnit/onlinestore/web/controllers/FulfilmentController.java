package com.itbulls.learnit.onlinestore.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itbulls.learnit.onlinestore.core.facades.PurchaseFacade;
import com.itbulls.learnit.onlinestore.persistence.entities.Purchase;

@Controller
public class FulfilmentController {
	
	@Autowired
	private PurchaseFacade purchaseFacade;
	
	@PostMapping("/management-fulfilment")
	public String doPost(@RequestParam Integer purchaseId) {
		purchaseFacade
			.markFulfilmentStageForPurchaseIdAsCompleted(purchaseId);
		return "redirect:/management-fulfilment"; 
	}
	@GetMapping("/management-fulfilment")
	public String doGet(Model model)
	{
		List<Purchase> purchases = purchaseFacade.getNotCompletedPurchases();
		model.addAttribute("purchases", purchases);
		 return "orders";
	}
}
