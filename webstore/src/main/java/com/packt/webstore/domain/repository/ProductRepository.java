package com.packt.webstore.domain.repository;

import java.util.List;
import java.util.Map;

import com.packt.webstore.domain.Product;

public interface ProductRepository {
	
	List <Product> getAllProducts();
	void updateStock(String productId, long noOfUnits);
	List<Product> getProductByCategory(String category);
	List<Product> getProductsByFilter(Map<String,List<String>> filterParams); 
	Product getProductById(String productId);
	

}
