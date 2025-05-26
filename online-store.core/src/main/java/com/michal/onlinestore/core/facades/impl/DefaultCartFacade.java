package com.michal.onlinestore.core.facades.impl;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.michal.onlinestore.core.facades.CartFacade;
import com.michal.onlinestore.core.facades.CartItemFacade;
import com.michal.onlinestore.persistence.entities.Cart;
import com.michal.onlinestore.persistence.entities.CartItem;
import com.michal.onlinestore.persistence.entities.Product;
import com.michal.onlinestore.persistence.entities.User;
import com.michal.onlinestore.persistence.repo.JpaCartItemRepo;
import com.michal.onlinestore.persistence.repo.JpaCartRepo;

/**
 * Default implementation of CartFacade.
 * Manages user carts and their items.
 */
@Service
public class DefaultCartFacade implements CartFacade {

    @Autowired
    private JpaCartRepo cartRepo;

    @Autowired
    private JpaCartItemRepo cartItemRepo;

    @Autowired
    private CartItemFacade cartItemFacade;

    /**
     * Adds a product to the user's cart.
     * If no cart exists, creates a new cart with the product.
     * If product exists in the cart, increments its quantity.
     */
    @Override
    public void addToCart(User user, Product product) {
        Cart cart = cartRepo.findByUser(user);

        if (cart == null) {
            // Create new cart with a single cart item
            Set<CartItem> items = new HashSet<>();
            CartItem newItem = new CartItem(product, 1);
            items.add(newItem);
            createCart(items, user);
        } else {
            // Check if product already in cart
            for (CartItem item : cart.getItems()) {
                if (item.getProduct().getId().equals(product.getId())) {
                    item.setQuantity(item.getQuantity() + 1);
                    cartItemRepo.save(item);
                    cartRepo.save(cart);
                    return;
                }
            }

            // Product not found in cart, add new item
            CartItem newItem = new CartItem(product, 1);
            newItem.setCart(cart);
            cart.addItem(newItem);
            cartItemRepo.save(newItem);
            cartRepo.save(cart);
        }
    }

    /**
     * Creates a new cart for the user with the given set of items.
     * Sets quantity of each item to 1 and associates them with the cart.
     */
    @Override
    public void createCart(Set<CartItem> items, User user) {
        Cart cart = new Cart();
        cart.setUser(user);

        for (CartItem item : items) {
            item.setCart(cart);
            item.setQuantity(1);
        }

        cart.setItems(items);
        cartRepo.save(cart);
    }

    @Override
    public Cart findCartById(Integer cartId) {
        return cartRepo.findById(cartId).orElse(null);
    }

    @Override
    public void deleteCart(Cart cart) {
        cartRepo.delete(cart);
    }

    public Cart findByUser(User user) {
        return cartRepo.findByUser(user);
    }

    /**
     * Calculates total price of all items in the cart.
     */
    public BigDecimal calculatePriceOfCart(Cart cart) {
        BigDecimal total = BigDecimal.ZERO;
        for (CartItem item : cart.getItems()) {
            total = total.add(item.getProduct().getPrice()
                .multiply(BigDecimal.valueOf(item.getQuantity())));
        }
        return total;
    }

    /**
     * Removes one unit of the specified item from the cart.
     * Removes item completely if quantity reaches zero.
     */
    public Cart removeItemFromCart(Integer cartId, Integer itemId) {
        Cart cart = findCartById(cartId);
        if (cart == null) {
            return new Cart();
        }

        Iterator<CartItem> iterator = cart.getItems().iterator();

        while (iterator.hasNext()) {
            CartItem item = iterator.next();
            if (item.getId().equals(itemId)) {
                if (item.getQuantity() == 1) {
                    cart.removeItem(item);
                    cartItemRepo.delete(item);
                } else {
                    item.setQuantity(item.getQuantity() - 1);
                    cartItemRepo.save(item);
                }
                cartRepo.save(cart);
                break;
            }
        }
        return cart;
    }

    /**
     * Returns count of distinct cart items.
     */
    public Integer getSizeOfCart(Cart cart) {
        return cart.getItems().size();
    }

    /**
     * Returns quantity of the specified product in the cart.
     */
    public Integer getQtyOfItemInCart(Cart cart, Product product) {
        return cartItemRepo.countByCartAndProduct(cart, product);
    }

    /**
     * Deletes a cart by its ID.
     */
    public void deleteCart(Integer cartId) {
        Cart cart = findCartById(cartId);
        if (cart != null) {
            cartRepo.delete(cart);
        }
    }

    /**
     * Returns total quantity of all products (sum of quantities) in the cart.
     */
    public Integer getNumberOfProductsInCart(Cart cart) {
        return cart.getItems().stream()
            .mapToInt(CartItem::getQuantity)
            .sum();
    }
}
