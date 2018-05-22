package com.packt.webstore.domain.repository.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.packt.webstore.domain.Address;
import com.packt.webstore.domain.Customer;
import com.packt.webstore.domain.Order;
import com.packt.webstore.domain.ShippingDetail;
import com.packt.webstore.domain.repository.OrderRepository;
import com.packt.webstore.service.CartService;

@Repository
public class InMemoryOrderRepository implements OrderRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTempleate;
	@Autowired
	private CartService cartService;

	@Override
	public long saveOrder(Order order) {
		String customerId = saveCustomer(order.getCustomer());
		Long shippingDetailId = saveShippingDetail(order.getShippingDetail());
		order.getCustomer().setCustomerId(customerId);
		order.getShippingDetail().setId(shippingDetailId);
		long createdOrderId = createOrder(order);
		cartService.clearCart(order.getCart().getId());
		return createdOrderId;
	}
		private long saveShippingDetail (ShippingDetail shippingDetail) {
			long addressId = saveAddress(shippingDetail.getShippingAddress());
					String SQL = "INSERT INTO SHIPPING_DETAIL(NAME,SHIPPING_DATE,SHIPPING_ADDRESS_ID) "
					+ "VALUES (:name, :shippingDate, :addressId)";
					
					Map<String, Object> params = new HashMap<String, Object>();
					
					params.put("name", shippingDetail.getName());
					params.put("shippingDate", shippingDetail.getShippingDate());
					params.put("addressId", addressId);
					SqlParameterSource paramSource = new
					MapSqlParameterSource(params);
					KeyHolder keyHolder = new GeneratedKeyHolder();
					jdbcTempleate.update(SQL, paramSource,keyHolder, new String[]{"ID"});
					return keyHolder.getKey().longValue();
					}
		private String saveCustomer(Customer customer) {
			
			String SQL = "INSERT INTO CUSTOMER(CUSTOMER_ID, NAME,PHONE_NUMBER, BILLING_ADDRESS) "
			+ "VALUES (:custId, :name, :phoneNumber :billingAddress";
			long addressId = saveAddress(customer.getBillingAddress());
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("name", customer.getName());
			params.put("phoneNumber", customer.getPhoneNumber());
			params.put("custId", customer.getCustomerId());
			params.put("billingAddress", addressId);
			jdbcTempleate.update(SQL, params);
			
			
			return customer.getCustomerId();
			}
		
		private long saveAddress(Address address) {
			String SQL = "INSERT INTO ADDRESS(DOOR_NO,STREET_NAME,AREA_NAME,STATE,COUNTRY,ZIP,CUSTOMER_ID) "
			+ "VALUES (:doorNo, :streetName, :areaName, :state, :country, :zip, :customerId)";
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("doorNo", address.getDoorNo());
			params.put("streetName", address.getStreetName());
			params.put("areaName", address.getAreaName());
			params.put("state", address.getState());
			params.put("country", address.getCountry());
			params.put("zip", address.getZipCode());
			params.put("customerId", address.getCustomerId());
			SqlParameterSource paramSource = new
			MapSqlParameterSource(params);
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTempleate.update(SQL, paramSource,keyHolder, new
			String[]{"ID"});
			return keyHolder.getKey().longValue();
			}
		
		
		private long createOrder(Order order) {
			String SQL = "INSERT INTO ORDERS(CART_ID,CUSTOMER_ID,SHIPPING_DETAIL_ID) "
			+ "VALUES (:cartId, :custId, :shippingDetailId)";
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("id", order.getOrderId());
			params.put("cartId", order.getCart().getId());
			params.put("custId", order.getCustomer().getCustomerId());
			params.put("shippingDetailId",
			order.getShippingDetail().getId());
			SqlParameterSource paramSource = new
			MapSqlParameterSource(params);
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTempleate.update(SQL, paramSource,keyHolder, new
			String[]{"ID"});
			return keyHolder.getKey().longValue();
			}
			
		
	}


