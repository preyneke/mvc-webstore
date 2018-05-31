package com.packt.webstore.service.impl;

import java.math.BigDecimal;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.packt.webstore.domain.Product;
import com.packt.webstore.domain.repository.ProductRepository;
import com.packt.webstore.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public void updateAllStock() {
	List<Product> allProducts =
	productRepository.getAllProducts();
	for(Product product : allProducts) {
	if(product.getUnitsInStock()<500)
	productRepository.updateStock
	(product.getProductId(),
	product.getUnitsInStock()+1000);
	}
	}
	@Override
	public List<Product> getAllProducts() {
	List<Product> products=	productRepository.getAllProducts();
		return products;
	}
	@Override
	public List<Product> getProductsByCategory(String category) {
		
		return productRepository.getProductByCategory(category);
	}
	@Override
	public List<Product> getProductsByFilter(Map<String, List<String>> filterParams) {
		
		return productRepository.getProductsByFilter(filterParams);
	}
	@Override
	public Product getProductById(String productId) {
		
		return productRepository.getProductById(productId);
	}
	@Override
	public List<Product> filterProducts(Map<String, List<String>> filterParams) {
		
		return productRepository.filterProducts(filterParams);
	}
	@Override
	public List<Product> getProductsByManufacturer(String manufacturer) {
	
		return productRepository.getProductsByManufacturer(manufacturer);
	}
	@Override
	public List<Product> getProductsByPrice(BigDecimal low, BigDecimal high) {
		
		return productRepository.getProductsByPrice(low, high);
	}
	@Override
	public void addProduct(Product product) {
		
		
		productRepository.addProduct(product);
		
	}
	
	@Override
	public void updateStockAfterSale(String productId, int quantity) {
		Product productToUpdate = productRepository.getProductById(productId);
		Long updatedamount = new Long(Long.sum(productToUpdate.getUnitsInStock(), -quantity));
		
		productRepository.updateStock(productId, updatedamount);
				}
				
		
	}
	
	