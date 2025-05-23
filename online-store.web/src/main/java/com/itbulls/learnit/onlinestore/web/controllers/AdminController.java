package com.itbulls.learnit.onlinestore.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itbulls.learnit.onlinestore.core.facades.PurchaseFacade;
import com.itbulls.learnit.onlinestore.persistence.entities.Purchase;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private PurchaseFacade purchaseFacade;
	
	@PostMapping("/panel")
	public String doPost(@RequestParam Integer purchaseId)
	{
		purchaseFacade.markFulfilmentStageForPurchaseIdAsCompleted(purchaseId);
	 return "orders";
	}
	
	@GetMapping("/panel")
	public String doGet(Model model)
	{
		List<Purchase> purchases = purchaseFacade.getNotCompletedPurchases();
		model.addAttribute("purchases", purchases);
		 return "orders";
	}

}
