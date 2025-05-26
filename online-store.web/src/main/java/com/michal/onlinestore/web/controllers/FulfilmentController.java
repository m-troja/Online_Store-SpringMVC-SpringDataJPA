package com.michal.onlinestore.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.michal.onlinestore.persistence.entities.Purchase;
import com.michal.onlinestore.core.facades.PurchaseFacade;

@Controller
@RequestMapping
public class FulfilmentController {
	
	@Autowired
	private PurchaseFacade purchaseFacade;
	
	@PostMapping("/admin/management-fulfilment")
	public String doPost(@RequestParam Integer purchaseId) {
		purchaseFacade
			.markFulfilmentStageForPurchaseIdAsCompleted(purchaseId);
		return "redirect:/admin/management-fulfilment"; 
	}
	
	
	@GetMapping("/admin/management-fulfilment")
	public String doGet(Model model)
	{
		List<Purchase> purchases = purchaseFacade.getNotCompletedPurchases();
		model.addAttribute("purchases", purchases);
		 return "orders";
	}
}
