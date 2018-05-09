package com.packt.webstore.controller;




import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.packt.webstore.domain.Product;
import com.packt.webstore.service.ProductService;

@Controller
@RequestMapping("market")
public class ProductController {
	
	
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping("/products")
	public String productList(Model model) {
		
		model.addAttribute("products", productService.getAllProducts());
		
		return "products";
	}
	@RequestMapping("/update/stock")
	public String updateStock(Model model) {
	productService.updateAllStock();
	return "redirect:/market/products";
	}

	@RequestMapping("/products/{category}")
	public String getProductsByCategory(Model model,
	@PathVariable("category") String productCategory) {
	model.addAttribute("products",
	productService.getProductsByCategory(productCategory));
	return "products";
	}
	
	
	
	@RequestMapping("/products/{category}/{price}")
	public String filterProducts(@PathVariable("category") String category,
	                             @MatrixVariable(pathVar = "price") Map<String, List<String>> filterParams,
	                             @RequestParam("manufacturer") String manufacturer,
	                             Model model) {
	    Set<Product> filteredProducts = new HashSet<Product>();

	    List<Product> productsByCategory = productService.getProductsByCategory(category);
	    List<Product> productsByManufacturer = productService.getProductsByManufacturer(manufacturer);
	    List<Product> productsByPrice = productService.getProductsByFilter(filterParams);

	  
	    

	    for(Product categoryProduct: productsByCategory) {
	        for(Product manufacturerProduct: productsByManufacturer) {
	            for(Product priceProduct: productsByPrice) {
	                if(priceProduct.equals(manufacturerProduct) && manufacturerProduct.equals(categoryProduct)) {
	                    filteredProducts.add(priceProduct);
	                }
	            }
	        }
	    }

	    model.addAttribute("products", filteredProducts);

	    return "products";
	}
	
	@RequestMapping("/products/filter/{param}")
	public String getProductsByFilter(@MatrixVariable(pathVar="param")Map<String, List<String>> filterParams, Model model) {
		model.addAttribute("products", productService.getProductsByFilter(filterParams));
		return "products";
	}
	
	
	
	@RequestMapping("/product")
	public String getProductById(@RequestParam("id") String productId, Model model) {
		model.addAttribute("product",
		productService.getProductById(productId));
		return "product";
		
	}
	
	@RequestMapping(value = "/products/add", method = RequestMethod.GET)
	public String getAddNewProductForm(Model model) {
		
		Product newProduct = new Product();
		model.addAttribute("newProduct", newProduct);

		return "addProduct";
	}
	
	@RequestMapping(value = "/products/add", method= RequestMethod.POST)
	public String processAddNewProductForm(@ModelAttribute("newProduct")Product newProduct) {
		productService.addProduct(newProduct);
		
		return "redirect:/market/products";
	}
}
