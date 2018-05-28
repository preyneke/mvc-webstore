package com.packt.webstore.service;

import java.util.List;

import com.packt.webstore.domain.Address;
import com.packt.webstore.domain.Customer;

public interface CustomerService {

	List<Customer> getAllCustomers();
	
	Long createCustomer(Customer newCustomer);
	
	Customer getCustomerById(String customerId);
	
	Address getAddressById(Long addressId);
}
