package com.packt.webstore.service;

import java.util.List;

import com.packt.webstore.domain.Address;
import com.packt.webstore.domain.Customer;
import com.packt.webstore.dto.CustomerDto;

public interface CustomerService {

	List<Customer> getAllCustomers();
	
	Long create(Customer newCustomer);
	
	Customer read(Long customerId);
	
	
	
	Customer delete(Long customerId);
	
	Address getAddressById(Long addressId);

	Customer validate(Long customerID);
}
