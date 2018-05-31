package com.packt.webstore.dto;

import java.io.Serializable;


import com.packt.webstore.domain.Address;

public class CustomerDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9193967661248261214L;
	
	private Long customerId;
	private String name;
	private String phoneNumber;
	private Address billingAddress;
	
	
	public CustomerDto() {
		this.billingAddress = new Address();
	
		
	}
	
	

	public CustomerDto(Long customerId) {
		
		this.customerId = customerId;
		
	}
	
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Address getBillingAddress() {
		return billingAddress;
	}
	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	
	
	
}
