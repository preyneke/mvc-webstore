package com.packt.webstore.domain;

import java.math.BigDecimal;
import java.nio.channels.NetworkChannel;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CartTest {
	
	private Cart cart;
	private CartItem cartItem1;
	private CartItem cartItem2;
	
	
	
	
	@Before
	public void setup() {
		cart = new Cart("1");
		 cartItem1 = new CartItem("1");
		 cartItem2 = new CartItem("2");
		
	}
	
	@Test
	public void cart_Grand_Total_should_be_equal_to_combined_cartItems_TotalPrice() {
		// arange
		Product iPhone = new Product("P1234", "iPhone X", new BigDecimal(500));
		Product laptop = new Product("P1235", "laptop", new BigDecimal(500));
		cartItem1.setProduct(laptop);
		cartItem2.setProduct(iPhone);
		List<CartItem> cartItems = new ArrayList<>();
		cartItems.add(cartItem1);
		cartItems.add(cartItem2);
		cart.setCartItems(cartItems);
		
		//act
		BigDecimal grandTotal = cart.getGrandTotal();
		
		BigDecimal cartTotal = new BigDecimal(0);
		
		for(CartItem cartItem: cartItems) {
			cartTotal = cartTotal.add(cartItem.getTotalPrice());
		}
		Assert.assertEquals(cartTotal, grandTotal);
		
		
		
		
	}

}
