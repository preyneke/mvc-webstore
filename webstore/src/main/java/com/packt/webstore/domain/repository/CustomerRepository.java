package com.packt.webstore.domain.repository;

import java.util.List;

import com.packt.webstore.domain.Address;
import com.packt.webstore.domain.Customer;


public interface CustomerRepository {
	
	Long createCustomer(Customer newCustomer);
	
	List<Customer> getAllCustomers();

	
	
	List<Customer> readCustomer(String custId);
	
	void deleteCustomer(String custId);
	
	Customer getCustomerById(String customerId);
	
	

	Address getAddressById(Long addressId);
	
	
	
	

}
