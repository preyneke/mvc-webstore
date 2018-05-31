package com.packt.webstore.domain;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.packt.webstore.domain.CartItem;
import com.packt.webstore.domain.Product;



public class CartItemTest {
	
	private CartItem cartItem;
	
	@Before
	public void setup() {
		cartItem = new CartItem("1");
		
	}
	@Test
	public void cartItem_total_price_should_be_equal_to_product_unit_price_in_case_of_single_quantity() {
		// arange
		Product iPhone = new Product("P1234", "iPhone X", new BigDecimal(500));
		cartItem.setProduct(iPhone);
		
		//act
		BigDecimal totalPrice = cartItem.getTotalPrice();
		
		//Assert
		
		Assert.assertEquals(iPhone.getUnitPrice(), totalPrice);
	}
	

}
