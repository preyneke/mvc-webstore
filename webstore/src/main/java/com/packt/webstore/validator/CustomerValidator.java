package com.packt.webstore.validator;

import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.packt.webstore.domain.Customer;


public class CustomerValidator implements Validator {
	
	@Autowired
    private javax.validation.Validator beanValidator;
	
	 private Set<Validator> springValidators;
	 
	 
	 public CustomerValidator() {
		   springValidators = new HashSet<Validator>();   
	   }
	 

	public CustomerValidator(Set<Validator> springValidators) {
		super();
		this.springValidators = springValidators;
	}
	
	

	public void setSpringValidators(Set<Validator> springValidators) {
		this.springValidators = springValidators;
	}



	@Override
	public boolean supports(Class<?> clazz) {
		
		return Customer.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		 Set<ConstraintViolation<Object>> constraintViolations = beanValidator.validate(target);

	      for (ConstraintViolation<Object> constraintViolation : constraintViolations) {
	         String propertyPath = constraintViolation.getPropertyPath().toString();
	         String message = constraintViolation.getMessage();
	         errors.rejectValue(propertyPath, "", message);
	      }

	      for(Validator validator: springValidators) {
	         validator.validate(target, errors);
	      }
	   }
	}