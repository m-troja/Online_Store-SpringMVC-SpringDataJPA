package com.michal.onlinestore.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.*;

import com.michal.onlinestore.persistence.entities.Purchase;
import com.michal.onlinestore.core.facades.PurchaseFacade;

/**
 * Controller for administrative operations related to purchases.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    private final PurchaseFacade purchaseFacade;

    @Autowired
    public AdminController(PurchaseFacade purchaseFacade) {
        this.purchaseFacade = purchaseFacade;
    }


    @PostMapping("/panel")
    public String fulfillPurchase(@RequestParam("purchaseId") Integer purchaseId) {
        purchaseFacade.markFulfilmentStageForPurchaseIdAsCompleted(purchaseId);
        return "redirect:/admin/panel";
    }

    @GetMapping("/panel")
    public String showAdminPanel(Model model) {
        List<Purchase> purchases = purchaseFacade.getNotCompletedPurchases();
        model.addAttribute("purchases", purchases);
        return "orders";
    }
=======
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.michal.onlinestore.core.facades.PurchaseFacade;
import com.michal.onlinestore.persistence.entities.Purchase;

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

>>>>>>> 695085bb892170646e452eb0cdff3bf54a05b59b
}
