package com.packt.webstore.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;





public class Customer implements Serializable {
	private static final long serialVersionUID = 2284040482222162898L;
	

	private Long customerId;
	private String name;
	private String phoneNumber;
	private Set<Address>addresses= new HashSet<Address>(); 

	

	public Customer() {
		
		
		
	}
	
	

	public Customer(Long customerId) {
		
		this.customerId = customerId;
		
	}
	
	public void addAddress (Address address) {
		addresses.add(address);
		
	}
	public boolean removeAddress(Address address) {
		for(Address custAddress : addresses) {
			if(custAddress == address) {
				addresses.remove(custAddress);
				return true;
			} 
				
			} return false;
	}
	
	public Address getAddressById (Long addressId) {
		return addresses.stream().filter(address -> address.getId().equals(addressId)).findAny().orElse(null);
	}
	
	public Address getBillingAddress(Set<Address> addresses) {
		this.addresses = addresses;
		for(Address custAddress : addresses) {
			if (custAddress.isBillingAddress()) {
				return custAddress;
			}
		}
		return null;
	}
		
	

	public Long getCustomerId() {
		return customerId;
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
	public Set<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", name=" + name + ", phoneNumber=" + phoneNumber + ", addresses="
				+ addresses + "]";
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
		
	}



	

	
}