package com.michal.onlinestore.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

<<<<<<< HEAD
import com.michal.onlinestore.persistence.entities.Purchase;
import com.michal.onlinestore.core.facades.PurchaseFacade;
=======
import com.michal.onlinestore.core.facades.PurchaseFacade;
import com.michal.onlinestore.persistence.entities.Purchase;
>>>>>>> 695085bb892170646e452eb0cdff3bf54a05b59b

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
	
	
<<<<<<< HEAD
	@GetMapping("/admin/management-fulfilment")
=======
	@GetMapping("/management-fulfilment")
>>>>>>> 695085bb892170646e452eb0cdff3bf54a05b59b
	public String doGet(Model model)
	{
		List<Purchase> purchases = purchaseFacade.getNotCompletedPurchases();
		model.addAttribute("purchases", purchases);
		 return "orders";
	}
}
