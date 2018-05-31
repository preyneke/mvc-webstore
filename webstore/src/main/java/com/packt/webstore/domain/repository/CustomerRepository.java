package com.packt.webstore.domain.repository;

import java.util.List;

import com.packt.webstore.domain.Address;
import com.packt.webstore.domain.Customer;
import com.packt.webstore.dto.CustomerDto;


public interface CustomerRepository {
	
	Long create(Customer newCustomer);
	
	List<Customer> getAllCustomers();

	
	
	Customer read(Long customerId);
	
	
	
	void delete(Long custId);
	
	Customer getCustomerById(Long customerId);
	
	

	Address getAddressById(Long addressId);
	
	
	
	

}
