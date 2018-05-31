package com.packt.webstore.validator;



import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;







public class CategoryValidator implements ConstraintValidator<Category, String> {


	List<String> allowedCategories = Arrays.asList("Laptop", "SmartPhone", "Tablet");
	

	@Override
	public void initialize(Category constraintAnnotation) {

		// nothing
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		for(String validGategory: allowedCategories) if (validGategory.toUpperCase().equals(value.toUpperCase())) return true;
		
		return false;
}
}
