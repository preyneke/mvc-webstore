package com.packt.webstore.exception;

public class CustomerNotFoundException  extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4807773135661735009L;

	private Long customerId;



	public CustomerNotFoundException(Long custId) {
		this.customerId = custId;
	}

	public Long getCustomerId() {
		return customerId;
	}
	
	
}
