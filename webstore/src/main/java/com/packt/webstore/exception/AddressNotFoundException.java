package com.packt.webstore.exception;

public class AddressNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2646495305935408694L;
	
	private Long addressId;
	
public AddressNotFoundException(Long addressId) {
		
		this.addressId = addressId;
	}

	public Long getAddressId() {
		return addressId;
	}
	
	

}
