package com.packt.webstore.config;


import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.webflow.config.AbstractFlowConfiguration;
import org.springframework.webflow.definition.registry.FlowDefinitionRegistry;
import org.springframework.webflow.engine.builder.support.FlowBuilderServices;
import org.springframework.webflow.executor.FlowExecutor;
import org.springframework.webflow.mvc.servlet.FlowHandlerAdapter;
import org.springframework.webflow.mvc.servlet.FlowHandlerMapping;

import com.packt.webstore.validator.BillingAddressValidator;
import com.packt.webstore.validator.CustomerValidator;



@Configuration
public class WebFlowConfig extends AbstractFlowConfiguration {
	

	@Bean
	public FlowDefinitionRegistry flowRegistry() {
		return getFlowDefinitionRegistryBuilder(flowBuilderService())
				.setBasePath("/WEB-INF/flows")
				.addFlowLocationPattern("/**/*-flow.xml")		
				.build();
	}
	@Bean
	public FlowExecutor flowExecutor() {
		return getFlowExecutorBuilder(flowRegistry()).build();
	}
	
	
	
	@Bean
	public FlowHandlerMapping flowHandlerMapping() {
		FlowHandlerMapping handlerMapping = new FlowHandlerMapping();
		handlerMapping.setOrder(-1);
		handlerMapping.setFlowRegistry(flowRegistry());
		return handlerMapping;
	}
	
	@Bean
	public FlowHandlerAdapter flowHandlerAdapter() {
		FlowHandlerAdapter handlerAdapter = new FlowHandlerAdapter();
		handlerAdapter.setFlowExecutor(flowExecutor());
		handlerAdapter.setSaveOutputToFlashScopeOnRedirect(true);
		return handlerAdapter;
	}
	@Bean
	public CustomerValidator customerValidator () {
		Set<Validator> springValidators = new HashSet<>();
		springValidators.add(new BillingAddressValidator());
		
	CustomerValidator customerValidator = new CustomerValidator();
	customerValidator.setSpringValidators(springValidators);
	return customerValidator;
	}
	
	@Bean
	public FlowBuilderServices flowBuilderService() {
		
		return getFlowBuilderServicesBuilder().setValidator(customerValidator()).build();
	}
	
}


