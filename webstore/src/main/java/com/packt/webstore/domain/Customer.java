package com.packt.webstore.domain;

import java.io.Serializable;

import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

import com.packt.webstore.validator.CustomerId;





@XmlRootElement
public class Customer implements Serializable {
	private static final long serialVersionUID = 2284040482222162898L;
	
	
	private Long customerId;
	@NotEmpty( message="Please enter your name")
	private String name;
	@Pattern(regexp="\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}", message="please enter valid phone number")
	private String phoneNumber;
	private Address billingAddress;
	 

	

	public Customer() {
		this.billingAddress = new Address();
	
		
	}
	
	

	public Customer(Long customerId) {
		
		this.customerId = customerId;
		
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
		return "Customer [customerId=" + customerId + ", name=" + name + ", phoneNumber=" + phoneNumber + "]";
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
		
	}



	public Address getBillingAddress() {
		return billingAddress;
	}



	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	

	

	
}