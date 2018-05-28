package com.packt.webstore.domain.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.packt.webstore.domain.Address;
import com.packt.webstore.domain.Customer;
import com.packt.webstore.service.CustomerService;

public class CustomerMapper implements RowMapper<Customer> {
	

	private AddressMapper addressMapper;

	
	
	public CustomerMapper( ) {
		
		this.addressMapper = new AddressMapper();
	}

	
	public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
		
			Long id = rs.getLong("ID");
			Customer customer = new Customer(id);
			
			
			customer.setName(rs.getString("NAME"));
			customer.setPhoneNumber(rs.getString("PHONE_NUMBER"));
			
			Address address = this.addressMapper.mapRow(rs, rowNum);
			customer.addAddress(address);
			
			


			return customer;
	
}
}


