package com.packt.webstore.domain;

public class CustomeTester {

	public static void main(String[] args) {
		Customer testCust = new Customer();
		Address testAddress = testCust.getBillingAddress(testCust.getAddresses());
		
		System.out.println(testCust);
		System.out.println(testAddress);

	}

}
