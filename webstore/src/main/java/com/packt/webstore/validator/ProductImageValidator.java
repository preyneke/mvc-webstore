package com.packt.webstore.validator;




import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import com.packt.webstore.domain.Product;
@Component
public class ProductImageValidator implements Validator {

	private long allowedSize = 200000;
	
	@Override
	public boolean supports(Class<?> clazz) {
		
		return Product.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		Product product = (Product) target;
		MultipartFile file = (MultipartFile) product.getProductImage();
		
		if(file.isEmpty()&& file.getSize()<=allowedSize) errors.rejectValue("productImage", "com.packt.webstore.validator.ProductImageValidator.message");
		
		
		
	}
	

}
