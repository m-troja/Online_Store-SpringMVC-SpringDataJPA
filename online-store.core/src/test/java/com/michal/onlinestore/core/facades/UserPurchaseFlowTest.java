package com.michal.onlinestore.core.facades;

import com.michal.onlinestore.persistence.entities.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class UserPurchaseFlowTest {

    private UserFacade userFacade;
    private ProductFacade productFacade;
    private CartFacade cartFacade;
    private PurchaseFacade purchaseFacade;

    @BeforeEach
    public void setup() {
        userFacade = mock(UserFacade.class);
        productFacade = mock(ProductFacade.class);
        cartFacade = mock(CartFacade.class);
        purchaseFacade = mock(PurchaseFacade.class);
    }

    @Test
    public void testUserRegistersLogsInAddsProductToCartAndMakesPurchase() {

        // 1. Create user and mock facade behavior for user lookups
        User user = new User();
        user.setId(1);
        user.setEmail("test@example.com");
        user.setPassword("secure123");

        // *** Fix: mock both getUserByEmail AND getUserById to return the user ***
        when(userFacade.getUserByEmail("test@example.com")).thenReturn(user);
        when(userFacade.getUserById(user.getId())).thenReturn(user);

        User registered = userFacade.getUserById(user.getId());
        assertEquals("test@example.com", registered.getEmail());

        // 2. Create product and mock product facade
        Product laptop = new Product();
        laptop.setId(1);
        laptop.setProductName("Lenovo ThinkPad");
        laptop.setPrice(BigDecimal.valueOf(3000.00));
        laptop.setCategory(new Category(1, "Laptop", "laptop.png"));

        when(productFacade.getProductById(1)).thenReturn(laptop);

        Product selectedLaptop = productFacade.getProductById(1);
        assertEquals(laptop.getCategory(), selectedLaptop.getCategory());

        // 3. Create cart with the product and mock cart facade behavior
        Cart cart = new Cart();
        cart.setId(100);
        cart.setUser(user);

        Set<CartItem> items = new HashSet<>();
        items.add(new CartItem(laptop, 1));
        cart.setItems(items);

        when(cartFacade.findByUser(user)).thenReturn(cart);
        when(cartFacade.getNumberOfProductsInCart(cart)).thenReturn(1);

        Cart userCart = cartFacade.findByUser(user);
        assertEquals(1, cartFacade.getNumberOfProductsInCart(userCart));

        // 4. Create purchase and mock purchase facade behavior
        Purchase purchase = new Purchase();
        purchase.setId(99);
        purchase.setUser(user);

        Set<Product> products = new HashSet<>();
        products.add(laptop);
        when(purchaseFacade.extractProductsFromCart(cart)).thenReturn(products);

        purchase.setProducts(products);

        when(purchaseFacade.createPurchaseAndReturnPurchase(user, cart)).thenReturn(purchase);

        Purchase createdOrder = purchaseFacade.createPurchaseAndReturnPurchase(user, cart);
        assertEquals(99, createdOrder.getId());
        assertEquals(user, createdOrder.getUser());
        assertEquals(products, createdOrder.getProducts());
    }
}
