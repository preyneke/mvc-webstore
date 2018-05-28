package com.packt.webstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.packt.webstore.domain.Address;
import com.packt.webstore.domain.Customer;
import com.packt.webstore.domain.repository.CustomerRepository;
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
	public Long createCustomer(Customer newCustomer) {
		
		return customerRepository.createCustomer(newCustomer);
		
		
	}


	@Override
	public Customer getCustomerById(String customerId) {
		
		return customerRepository.getCustomerById(customerId);
	}


	@Override
	public Address getAddressById(Long addressId) {
		
		return customerRepository.getAddressById(addressId);
	}


	
		
	

}
