package com.packt.webstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.packt.webstore.domain.Customer;

import com.packt.webstore.service.CustomerService;

@RestController
@RequestMapping(value = "rest/customer")
public class CustomerRestController {

   @Autowired
   private CustomerService customerService;
   
   @RequestMapping(method = RequestMethod.POST)
   @ResponseStatus(value = HttpStatus.CREATED)
   public void create(@RequestBody Customer customer) {
     customerService.create(customer);
   }

   @RequestMapping(value = "/{customerId}", method = RequestMethod.GET)
   public Customer read(@PathVariable(value = "customerId") Long customerId) {
      return customerService.read(customerId);
      
   }

  

   @RequestMapping(value = "/{cartId}", method = RequestMethod.DELETE)
   @ResponseStatus(value = HttpStatus.OK)
   public void delete(@PathVariable(value = "cartId") Long customerId) {
      customerService.delete(customerId);
   }
}
      
  