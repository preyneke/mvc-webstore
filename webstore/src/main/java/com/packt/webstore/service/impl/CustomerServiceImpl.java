package com.packt.webstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.packt.webstore.domain.Address;
import com.packt.webstore.domain.Cart;
import com.packt.webstore.domain.Customer;
import com.packt.webstore.domain.repository.CustomerRepository;
import com.packt.webstore.dto.CustomerDto;
import com.packt.webstore.exception.CustomerNotFoundException;
import com.packt.webstore.exception.InvalidCartException;
import com.packt.webstore.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	

	@Override
	public List<Customer> getAllCustomers() {
		List<Customer> customers = customerRepository.getAllCustomers();
		return customers;
	}



	@Override
	public Address getAddressById(Long addressId) {
		
		return customerRepository.getAddressById(addressId);
	}
	@Override
	public Customer validate(Long customerID) {
	Customer customer = customerRepository.read(customerID);
	if(customer==null ) {
	throw new CustomerNotFoundException(customerID);
	}
	return customer;
	}


	@Override
	public Long create(Customer newCustomer) {
		return customerRepository.create(newCustomer);
	
	}


	@Override
	public Customer read(Long customerId) {
		return customerRepository.read(customerId);
	}


	


	@Override
	public Customer delete(Long customerId) {
		customerRepository.delete(customerId);
		return null;
	}


	
		
	

}
