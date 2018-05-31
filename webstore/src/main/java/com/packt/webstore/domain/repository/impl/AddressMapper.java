package com.packt.webstore.domain.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.packt.webstore.domain.Address;
import com.packt.webstore.service.CustomerService;
import com.packt.webstore.service.ProductService;


public class AddressMapper implements RowMapper<Address> {
	
private CustomerService customerService;
	
	public AddressMapper(CustomerService customerService) {
		
		this.customerService = customerService;
	}




	@Override
	public Address mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Long id = rs.getLong("ID");
		Address address = new Address(id);
		
		address.setDoorNo(rs.getString("DOOR_NO"));
		address.setAreaName(rs.getString("AREA_NAME"));
	
		address.setStreetName(rs.getString("STREET_NAME"));
		address.setState(rs.getString("STATE"));
		address.setCountry(rs.getString("COUNTRY"));
		address.setZipCode(rs.getString("ZIP"));
		
		return address;
	}
	

}
