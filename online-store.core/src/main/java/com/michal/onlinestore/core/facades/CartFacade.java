package com.michal.onlinestore.core.facades;

import java.math.BigDecimal;
<<<<<<< HEAD
=======
import java.util.List;
>>>>>>> 695085bb892170646e452eb0cdff3bf54a05b59b
import java.util.Set;

import org.springframework.stereotype.Service;

import com.michal.onlinestore.persistence.entities.Cart;
import com.michal.onlinestore.persistence.entities.CartItem;
import com.michal.onlinestore.persistence.entities.Product;
import com.michal.onlinestore.persistence.entities.User;

<<<<<<< HEAD
/**
 * Facade interface for cart-related operations.
 * Provides methods to manage user shopping carts including creation,
 * modification, retrieval, and deletion of cart and cart items.
 */
@Service
public interface CartFacade {

    /**
     * Creates a new cart for the specified user with given cart items.
     *
     * @param items set of CartItem objects to be added to the new cart
     * @param user the user to whom the cart belongs
     */
    void createCart(Set<CartItem> items, User user);

    /**
     * Adds a product to the specified user's cart.
     * If the user has no existing cart, a new cart is created.
     * If the product already exists in the cart, its quantity is incremented.
     *
     * @param user the user adding the product to their cart
     * @param product the product to add
     */
    void addToCart(User user, Product product);

    /**
     * Finds a cart by its unique identifier.
     *
     * @param cartId the ID of the cart to find
     * @return the Cart if found, otherwise null
     */
    Cart findCartById(Integer cartId);

    /**
     * Deletes the specified cart.
     *
     * @param cart the cart to delete
     */
    void deleteCart(Cart cart);

    /**
     * Finds a cart associated with the specified user.
     *
     * @param user the user whose cart is to be found
     * @return the Cart belonging to the user, or null if none exists
     */
    Cart findByUser(User user);

    /**
     * Calculates the total price of all items in the specified cart.
     *
     * @param cart the cart to calculate the price for
     * @return the total price as BigDecimal
     */
    BigDecimal calculatePriceOfCart(Cart cart);

    /**
     * Removes one quantity of a specific cart item from the cart.
     * If the quantity is 1, the item is removed entirely.
     *
     * @param cartId the ID of the cart
     * @param itemId the ID of the cart item to remove
     * @return the updated Cart after removal
     */
    Cart removeItemFromCart(Integer cartId, Integer itemId);

    /**
     * Returns the number of distinct cart items in the cart.
     *
     * @param cart the cart to check
     * @return the count of distinct items in the cart
     */
    Integer getSizeOfCart(Cart cart);

    /**
     * Retrieves the quantity of a specific product in the given cart.
     *
     * @param cart the cart containing the product
     * @param product the product to count
     * @return the quantity of the product in the cart
     */
    Integer getQtyOfItemInCart(Cart cart, Product product);

    /**
     * Deletes a cart by its ID.
     *
     * @param cartId the ID of the cart to delete
     */
    void deleteCart(Integer cartId);

    /**
     * Calculates the total number of products (accounting for quantities)
     * in the given cart.
     *
     * @param cart the cart to count products for
     * @return the total quantity of products in the cart
     */
    Integer getNumberOfProductsInCart(Cart cart);
=======
@Service
public interface CartFacade {

	void createCart(Set<CartItem> items, User user);

	void addToCart(User user, Product product);

	Cart findCartById(Integer cartId);

	void deleteCart(Cart cart);
	
	 Cart findByUser(User user);
	 
	 public BigDecimal calculatePriceOfCart(Cart cart);
	 
	 Cart removeItemFromCart(Integer cartId, Integer itemId);
	 
	 Integer getSizeOfCart(Cart cart);
	 
	 Integer getQtyOfItemInCart(Cart cart, Product product);
	 
	 void deleteCart(Integer cartId);
	 
	 Integer getNumberOfProductsInCart(Cart cart);
>>>>>>> 695085bb892170646e452eb0cdff3bf54a05b59b
}
