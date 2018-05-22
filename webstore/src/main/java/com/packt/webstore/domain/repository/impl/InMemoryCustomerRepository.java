package com.packt.webstore.domain.repository.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.packt.webstore.domain.Address;
import com.packt.webstore.domain.Customer;
import com.packt.webstore.domain.repository.CustomerRepository;
import com.packt.webstore.exception.CustomerNotFoundException;



@Repository
public class InMemoryCustomerRepository implements CustomerRepository {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTempleate;
	
	
	@Override
	public List<Customer> getAllCustomers() {
		Map<String, Object> params = new HashMap<String,
				Object>();
				List<Customer> result = jdbcTempleate.query("SELECT * FROM CUSTOMERS", params, new CustomerMapper());
				return result;
		
	}
	
	


	@Override
	public void createCustomer(Customer customer) {
		long addressId = saveAddress(customer.getBillingAddress());
		
		String SQL = "INSERT INTO CUSTOMERS (CUSTOMER_ID,"
				+"NAME,"
				+"PHONE_NUMBER, BILLING_ADDRESS)"
				+" VALUES( :custId, :name, :phoneNumber, :billingAddress)";
		
		Map<String, Object> params = new HashMap<>();
		params.put("custId", customer.getCustomerId());
		params.put("name", customer.getName());
		params.put("phoneNumber", customer.getPhoneNumber());
		params.put("billingAddress", addressId);
		
		jdbcTempleate.update(SQL, params);
		
		
	}
	private long saveAddress(Address address) {
		String SQL = "INSERT INTO ADDRESS(DOOR_NO,STREET_NAME,AREA_NAME,STATE,COUNTRY,ZIP) "
		+ "VALUES (:doorNo, :streetName, :areaName, :state, :country, :zip)";
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("doorNo", address.getDoorNo());
		params.put("streetName", address.getStreetName());
		params.put("areaName", address.getAreaName());
		params.put("state", address.getState());
		params.put("country", address.getCountry());
		params.put("zip", address.getZipCode());
		SqlParameterSource paramSource = new
		MapSqlParameterSource(params);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTempleate.update(SQL, paramSource,keyHolder, new
		String[]{"ID"});
		return keyHolder.getKey().longValue();
		}


	@Override
	public List<Customer> readCustomer(String custId) {
		String SQL = "SELECT * WHERE CUSTOMER_ID = :cust_Id";
		Map<String, Object> params = new HashMap<>();
		params.put("cust_Id", custId);
		return jdbcTempleate.query(SQL, params, new CustomerMapper());
		
		
	}


	@Override
	public void deleteCustomer(String customerId) {
		String DELETE_CUSTOMER = "DELETE FROM CUSTOMER WHERE CUSTOMER_ID= :cusomertId";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("customerId", customerId);
		
		jdbcTempleate.update(DELETE_CUSTOMER, params);
		
	}


	@Override
	public Customer getCustomerById(String customerId) {
		String SQL = "SELECT * FROM CUSTOMERS WHERE CUSTOMER_ID = :id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", customerId);
		try {
		return jdbcTempleate.queryForObject(SQL, params, new CustomerMapper());
		} catch (DataAccessException e) {
		throw new CustomerNotFoundException(customerId);
		}
		}
	


}


	