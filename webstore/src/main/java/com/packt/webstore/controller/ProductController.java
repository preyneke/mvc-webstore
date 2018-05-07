package com.packt.webstore.controller;

import java.math.BigDecimal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.packt.webstore.domain.Product;

@Controller
public class ProductController {
	
	@RequestMapping("/products")
	public String list(Model model) {
		Product iphone = new Product("P1234", "iPhone x", new BigDecimal(500));
		iphone.setDescription("Apple iPhone X smartphone with Super Retina HD display\r\n" + 
				"5.8-inch (diagonal) all-screen OLED Multi-Touch display\r\n" + 
				"HDR display and 12MP wide-angle and telephoto cameras\r\n");
		iphone.setCategory("Smartphone");
		iphone.setManufacturer("Apple");
		iphone.setUnitsInStock(1000);
		model.addAttribute("product", iphone);
		
		return "products";
	}

}
