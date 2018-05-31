package com.packt.webstore.validator;

import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.binding.validation.ValidationContext;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.packt.webstore.domain.Customer;
import com.packt.webstore.service.CustomerService;


public class CustomerValidator implements Validator {
	
	@Autowired
    private javax.validation.Validator beanValidator;
	
	
	
	@Autowired
	private CustomerService customerService;
	
	 private Set<Validator> springValidators;
	 
	 
	 public void loadCustomerInfoValidator (Customer newCustomer, ValidationContext context) {
		 MessageContext messages = context.getMessageContext();
		 if(customerService.read(newCustomer.getCustomerId())== null) {
			 messages.addMessage(new MessageBuilder().error().source("customerId").defaultText("No such Customer Found").build());
		 }
	 }
	 
	 public void collectCustomerInfoValidator (Customer newCustomer, ValidationContext context) {
		 MessageContext messages = context.getMessageContext();
		 if(customerService.read(newCustomer.getCustomerId())== null) {
			 messages.addMessage(new MessageBuilder().error().source("customerId").defaultText("No such Customer Found").build());
		 }
	 }
	 
	 
	 public CustomerValidator() {
		 springValidators = new HashSet<Validator>();
		     
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