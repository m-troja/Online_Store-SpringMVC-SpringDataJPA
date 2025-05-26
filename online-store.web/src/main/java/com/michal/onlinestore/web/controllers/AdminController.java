package com.michal.onlinestore.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
}
