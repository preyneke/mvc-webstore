package com.packt.webstore.domain.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.packt.webstore.domain.Address;
import com.packt.webstore.domain.Customer;
import com.packt.webstore.service.CustomerService;
import com.packt.webstore.service.ProductService;

public class CustomerMapper implements RowMapper<Customer> {
	

	private AddressMapper addressMapper;
	private NamedParameterJdbcTemplate jdbcTempleate;

	
	
	public CustomerMapper(NamedParameterJdbcTemplate jdbcTempleate, CustomerService customerService ) {
		
		this.jdbcTempleate = jdbcTempleate;
		this.addressMapper = new AddressMapper(customerService);
	}

	
	public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
		
			Long id = rs.getLong("CUSTOMER_ID");
			
			Customer customer = new Customer(id);
			
			
			customer.setName(rs.getString("NAME"));
			customer.setPhoneNumber(rs.getString("PHONE_NUMBER"));
			
			String SQL = String.format("SELECT * FROM ADDRESS WHERE ID = :id");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("id", rs.getInt("BILLING_ADDRESS_ID"));
			Address billingAddress = jdbcTempleate.queryForObject(SQL, params, addressMapper);
			customer.setBillingAddress(billingAddress);
			


			return customer;
	
}
}


