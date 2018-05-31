package com.packt.webstore.domain.repository.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.jayway.jsonpath.ParseContext;
import com.packt.webstore.domain.Address;
import com.packt.webstore.domain.Customer;
import com.packt.webstore.domain.repository.CustomerRepository;
import com.packt.webstore.dto.CustomerDto;
import com.packt.webstore.exception.AddressNotFoundException;
import com.packt.webstore.exception.CustomerNotFoundException;
import com.packt.webstore.service.CustomerService;




@Repository
public class InMemoryCustomerRepository implements CustomerRepository {

	 @Autowired
	   private NamedParameterJdbcTemplate jdbcTempleate;
	 @Autowired
		private CustomerService customerService;
	 
	
	   

	
	@Override
	public List<Customer> getAllCustomers() {
		Map<String, Object> params = new HashMap<String,
				Object>();
				List<Customer> result = jdbcTempleate.query("SELECT * FROM CUSTOMERS", params, new CustomerMapper(jdbcTempleate, customerService));
				return result;
		
	}
	
	


	@Override
	public Long create(Customer newCustomer) {
		long billingAddressId = saveBillingAddress(newCustomer.getBillingAddress());
		
		String SQL = "INSERT INTO CUSTOMERS(NAME,PHONE_NUMBER,BILLING_ADDRESS_ID) "
				+ "VALUES (:name, :phoneNumber, :billingAddressId)";
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("name", newCustomer.getName());
				params.put("phoneNumber", newCustomer.getPhoneNumber());
				params.put("billingAddressId", billingAddressId);
		
				SqlParameterSource paramSource = new
						MapSqlParameterSource(params);
						KeyHolder keyHolder = new GeneratedKeyHolder();
						jdbcTempleate.update(SQL, paramSource,keyHolder, new
						String[]{"CUSTOMER_ID"});
						
						return keyHolder.getKey().longValue();
						
						}
		
		
	
	private long saveBillingAddress(Address address) {
		String SQL = "INSERT INTO ADDRESS(DOOR_NO,STREET_NAME,AREA_NAME,STATE,COUNTRY,ZIP) "
		+ "VALUES (:doorNo, :streetName, :areaName, :state, :country, :zip )";
		
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
	public Customer read(Long customerId) {
		
		String SQL = "SELECT * FROM CUSTOMERS WHERE CUSTOMER_ID = :customerId ";
		Map<String, Object> params = new HashMap<>();
		params.put("customerId", customerId);
		
		
		try {
	         return jdbcTempleate.queryForObject(SQL, params, new CustomerMapper(jdbcTempleate, customerService));
	      } catch (DataAccessException e) {
	           throw new CustomerNotFoundException(customerId);
	      }      
	   }


	@Override
	public void delete(Long customerId) {
		String DELETE_CUSTOMER = "DELETE FROM CUSTOMERS WHERE CUSTOMER_ID = :cusomertId ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("customerId", customerId);
		
		jdbcTempleate.update(DELETE_CUSTOMER, params);
		
	}


	@Override
	public Customer getCustomerById(Long custId) {
		Long customerId = custId;
		String SQL = "SELECT * FROM CUSTOMERS WHERE CUSTOMER_ID= :id ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", customerId);
		try {
		return jdbcTempleate.queryForObject(SQL, params, new CustomerMapper(jdbcTempleate, customerService));
		} catch (DataAccessException e) {
		throw new CustomerNotFoundException(custId);
		}
		}




	@Override
	public Address getAddressById(Long addressId) {
		String SQL = "SELECT * FROM ADDRESS WHERE ID = :addressId";
		Map<String, Object> params = new HashMap<>();
		params.put("addressId", addressId);
		try {
			return jdbcTempleate.queryForObject(SQL, params, new AddressMapper(customerService));
			} catch (DataAccessException e) {
			throw new AddressNotFoundException(addressId);
			}
			}


	}

	





	