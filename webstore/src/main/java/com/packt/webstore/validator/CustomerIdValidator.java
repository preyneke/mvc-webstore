package com.packt.webstore.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.packt.webstore.domain.Customer;
import com.packt.webstore.exception.CustomerNotFoundException;

import com.packt.webstore.service.CustomerService;

public class CustomerIdValidator implements ConstraintValidator<CustomerId, Long> {
	@Autowired
	private CustomerService customerService;
	
	
	@Override
	public void initialize(CustomerId constraintAnnotation) {
		// intentionally left blank; this is the place to initialize the constraint
		// annotation for any sensible default values.

	}

	@Override
	public boolean isValid(Long value, ConstraintValidatorContext context) {
		Customer customer;
		try {
			customer = customerService.read(value);
			} catch (CustomerNotFoundException e) {
			return true;
			}
			if(customer!= null) {
			return false;
			}
			return true;
			}
			}




