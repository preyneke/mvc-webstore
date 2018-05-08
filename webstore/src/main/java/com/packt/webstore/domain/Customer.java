package com.packt.webstore.domain;

import java.io.Serializable;

public class Customer implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 519120743943282354L;
	
	private String customerId;
	private String name;
	private String address;
	private Long noOfOrdersMade;
	
	public Customer() {}
	
	public Customer(String name, String address) {
		this.name = name;
		this.address = address;
	}
	// getter and setters

	
	

	public String getName() {
		return name;
	}

	

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getNoOfOrdersMade() {
		return noOfOrdersMade;
	}

	public void setNoOfOrdersMade(Long noOfOrdersMade) {
		this.noOfOrdersMade = noOfOrdersMade;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", name=" + name + ", address=" + address + ", noOfOrdersMade="
				+ noOfOrdersMade + "]";
	}
	

	
	
	

}
