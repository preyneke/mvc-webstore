package com.packt.webstore.validator;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.packt.webstore.domain.Customer;


@Component
public class BillingAddressValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		
		return Customer.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Customer newCustomer = (Customer) target;
		
		if(StringUtils.isBlank(newCustomer.getBillingAddress().getDoorNo())) {
			 errors.rejectValue("billingAddress.doorNo", null, "Door Number must not be empty");
		 }
		 if(StringUtils.isBlank(newCustomer.getBillingAddress().getStreetName())) {
			 errors.rejectValue("billingAddress.streetName", null, "Street Name must not be empty");
		 }
		 if(StringUtils.isBlank(newCustomer.getBillingAddress().getAreaName())) {
			 errors.rejectValue("billingAddress.areaName", null, "Area Name must not be empty");
		 }
		 if(StringUtils.isBlank(newCustomer.getBillingAddress().getState())) {
			 errors.rejectValue("billingAddress.state", null, "State must not be empty");
		 }
		 if(StringUtils.isBlank(newCustomer.getBillingAddress().getCountry())) {
			 errors.rejectValue("billingAddress.country", null, "Country must not be empty");
		 }
		 if(StringUtils.isBlank(newCustomer.getBillingAddress().getZipCode())) {
			 errors.rejectValue("billingAddress.zipCode", null, "Zip Code must not be empty");
		 }
		
	}
	

}
