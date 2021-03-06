package com.packt.webstore.service;

import java.math.BigDecimal;

import java.util.List;
import java.util.Map;

import com.packt.webstore.domain.Product;

public interface ProductService {
	
	void updateAllStock();
	
	void updateStockAfterSale(String productId, int quantity);
	
	List<Product> getAllProducts();
	List<Product> getProductsByCategory(String category);
	List<Product> getProductsByFilter(Map<String, List<String>> filterParams);
	Product getProductById(String productId);
	List<Product> filterProducts(Map<String,List<String>> filterParams);

	List<Product> getProductsByManufacturer(String manufacturer);

	List<Product> getProductsByPrice(BigDecimal low, BigDecimal high);
	
	void addProduct(Product product);

}
