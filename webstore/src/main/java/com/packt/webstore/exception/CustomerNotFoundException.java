package com.packt.webstore.exception;

public class CustomerNotFoundException  extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4807773135661735009L;

	private String customerId;

	public CustomerNotFoundException(String customerId) {
		
		this.customerId = customerId;
	}

	public String getCustomerId() {
		return customerId;
	}
	
	
}
