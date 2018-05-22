package com.packt.webstore.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.packt.webstore.domain.Customer;
import com.packt.webstore.exception.CustomerNotFoundException;

import com.packt.webstore.service.CustomerService;
import com.packt.webstore.validator.CustomerValidator;


@Controller
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	@Autowired
	private CustomerValidator customerValidator;
	

	@RequestMapping("/customers")
	public String customerList(Model model) {
		
		model.addAttribute("customers", customerService.getAllCustomers());
		
		return "customers";
		
		
}
	@RequestMapping(value = "/customers/add", method = RequestMethod.GET)
	public String getAddNewCustomerForm(Model model) {
		
		Customer newCustomer = new Customer();
		model.addAttribute("newCustomer", newCustomer);

		return "addCustomer";
	}
	
	@RequestMapping(value = "/customers/add", method= RequestMethod.POST)
	public String processAddNewCustomerForm(@ModelAttribute("newCustomer")@Valid Customer newCustomer, BindingResult result) {
String[] suppressedFields = result.getSuppressedFields();
		
		if (suppressedFields.length > 0) {
		throw new RuntimeException("Attempting to bind disallowed fields: " +
		StringUtils.arrayToCommaDelimitedString(suppressedFields));
		}
		
		if(result.hasErrors()) {
			return "addCustomer";
		}
		
		
		customerService.createCustomer(newCustomer);
		
		return "redirect:/customers";
	}
	// exception handeling
		@ExceptionHandler(CustomerNotFoundException.class)
		public ModelAndView handleError(HttpServletRequest req, CustomerNotFoundException exception) {
			ModelAndView mav = new ModelAndView();
			mav.addObject("invalidCustomerId", exception.getCustomerId());
			mav.addObject("exception", exception);
			mav.addObject("url", req.getRequestURL() + "?" + req.getQueryString());
			mav.setViewName("CustomerNotFound");
			return mav;
		}
	
	
	@InitBinder
	public void initialiseBinder(WebDataBinder binder) {
		binder.setAllowedFields("name",
									"phoneNumber",
									"billingAddress.doorNo",
									"billingAddress.streetName",
									"billingAddress.areaName",
									"billingAddress.state",
									"billingAddress.country",
									"billingAddress.zipCode",
									"language");
		binder.setValidator(customerValidator);
	}
}
