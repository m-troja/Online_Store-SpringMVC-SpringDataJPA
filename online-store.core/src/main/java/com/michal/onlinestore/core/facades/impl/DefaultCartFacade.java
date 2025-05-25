package com.michal.onlinestore.core.facades.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.michal.onlinestore.core.facades.CartFacade;
import com.michal.onlinestore.persistence.entities.Cart;
import com.michal.onlinestore.persistence.entities.CartItem;
import com.michal.onlinestore.persistence.entities.Product;
import com.michal.onlinestore.persistence.entities.User;
import com.michal.onlinestore.persistence.repo.JpaCartItemRepo;
import com.michal.onlinestore.persistence.repo.JpaCartRepo;

@Service
public class DefaultCartFacade implements CartFacade {
	
	@Autowired
	JpaCartRepo cartRepo;
	
	@Autowired
	JpaCartItemRepo cartItemRepo;
	
	@Autowired
	CartItemFacade cartItemFacade;
	
/*	Called by cart controller. Check if user has a cart already . If no, create a cart and add the product into it.
*	If user has a cart already, add the product into existing cart.	
	Single user may have only one existing (opened) cart                            */
	public void addToCart(User user, Product product)
	{
		
		// Check if user has a cart
		if ( cartRepo.findByUser(user) == null)
		{
			System.out.println( " cartRepo.findByUser(user) == null" );

			// No existing cart - save a new cart for user
			Set<CartItem> items = new HashSet<>();
			CartItem item = new CartItem(product, 1);
			
			items.add(item);
			
			System.out.println( " createCart(items , user).  items : " + items.toString());

			createCart(items , user);

		}
	
		// User has a cart - add the product into cart, or increment quantity of product
	
		else {

//			Get user's cart
			Cart cart = cartRepo.findByUser(user);
			Set<CartItem> itemsInCart = cart.getItems();
			
//			If Cart contains same product as user wants to add now, increment item's quantity by 1
			for (CartItem item : itemsInCart )
			{
//				Check if has a product in cart
				if ( item.getProduct().getId().equals(product.getId()) )
				{
					
					System.out.println("Same product found in cart! Incremeting qty by 1 ");
					
					item.setQuantity(item.getQuantity() + 1);
					
					System.out.println("item.getQuantity() " + item.getQuantity() + ", product : " + item.getProduct().toString());
					
					System.out.println("Saving cart and breaking. ");
					
					cartItemRepo.save(item);
					cartRepo.save(cart);
					
					System.out.println("Added item to cart. New cart : " +  cart.toString());

					return;
				}
			}
			
//			If user does not have a product in cart, add product into cart
			CartItem itemToAdd = new CartItem(product, 1);
			System.out.println("user does not have a product in cart, add product into cart : " +  itemToAdd.toString());
			itemToAdd.setCart(cart);
			cart.addItem(itemToAdd);
			
			cartItemRepo.save(itemToAdd);
			cartRepo.save(cart);
			
			System.out.println("Added item into cart. New cart : " + cart.toString());
		}
	}
	
	@Override
	public void createCart(Set<CartItem> items, User user) {
		Cart cart = new Cart();
		cart.setUser(user);
		
//		associate items with cart
		for (CartItem item : items)
		{
			item.setCart(cart);
			item.setQuantity(1);
		}
		
//		associate cart with items
		cart.setItems(items);
		
		cartRepo.save(cart);
		
		System.out.println("Created cart with items: " +  items.toString());

	}

	@Override
	public Cart findCartById(Integer cartId) {
		
		return cartRepo.findById(cartId).orElse(null);
	}

	@Override
	public void deleteCart(Cart cart) {
		cartRepo.delete(cart);
	}

	public Cart findByUser(User user)
	{
		return cartRepo.findByUser(user);
	}

	public BigDecimal calculatePriceOfCart(Cart cart)
	{
		Set<CartItem> items = cart.getItems();
		BigDecimal priceOfProduct = BigDecimal.ZERO;
		BigDecimal total = BigDecimal.ZERO;
		
		for (CartItem item : items)
		{
			Product p = item.getProduct();
			Integer qtyOfItem = item.getQuantity();
			priceOfProduct = p.getPrice();
			
			total = total.add(priceOfProduct.multiply(BigDecimal.valueOf(qtyOfItem)));
		}
		
		return total;
	}
	
	public Cart removeItemFromCart(Integer cartId, Integer itemId) {
	    System.out.println("in removeItemFromCart... ");

	    Cart cart = findCartById(cartId);
	    
	    if (cart == null) {
	        System.out.println("Cart not found with id: " + cartId);
	        return new Cart();
	    }
	    
	    System.out.println("cart from DB: " + cart.toString());

	    Set<CartItem> items = cart.getItems();
	    System.out.println("items before removing : " + items.toString());

	    Iterator<CartItem> iterator = items.iterator();
	    while (iterator.hasNext()) {
	        CartItem item = iterator.next();

	        System.out.println("in iterator loop...");
	        System.out.println("item.id = " + item.getId() + ", itemId = " + itemId);
	        System.out.println("item qty = " + item.getQuantity());

	        if (item.getId().equals(itemId)) {
	            System.out.println("inside item.getId() == itemId");

	            if (item.getQuantity().equals(1)) {
	                System.out.println("Qty of item = 1! Removing item from cart.");
	                cart.removeItem(item);
	                
	            }
	            
	            else 
	            {
	                item.setQuantity(item.getQuantity() - 1);
	                System.out.println("Qty decremented! New qty = " + item.getQuantity());

	                cartItemRepo.save(item);
	                cartRepo.save(cart);

	                return cart;
	            }
	            
	            break; // item found, no need to continue looping
	            
	        } 
	        
	        else 
	        {
	            System.out.println("inside else, loop: CartItem item does not match itemId");
	        }
	    }

	    // Save cart after possible removal
	    cartRepo.save(cart);

	    System.out.println("items after removing: " + cart.getItems().toString());
	    System.out.println("Size of cart after removeItemFromCart: " + getSizeOfCart(cart));

	    return cart;
	}

	public Integer getSizeOfCart(Cart cart)
	{
		return cart.getItems().size();
	}
	
	public Integer getQtyOfItemInCart(Cart cart, Product product)
	{
		return cartItemRepo.countByCartAndProduct(cart, product);
	}

//	It removes the cart
	public void deleteCart(Integer cartId)
	{
		cartRepo.delete(findCartById(cartId));
	}

	public Integer getNumberOfProductsInCart(Cart cart)
	{
		Set<CartItem> items = cart.getItems();
		List<Product> products = new ArrayList<>();
		Iterator<CartItem> itemIterator = items.iterator(); 
		Integer total = 0;
		
//		Iterate over every cart's item
		while(itemIterator.hasNext())
		{
			CartItem item = itemIterator.next();
			Integer itemQty = item.getQuantity();
			
//			Add one product for one quantity of item
			for (int i = 0; i < itemQty ; i++) 
			{
				total++;
			}		
		}
		
		System.out.println("getProductsFromCart, products: " + products.toString());
		
		return total;

	}

}
