package com.packt.webstore.config;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.xml.MarshallingView;
import org.springframework.web.util.UrlPathHelper;
import org.springframework.webflow.action.FormAction;

import com.packt.webstore.domain.Customer;
import com.packt.webstore.domain.Product;
import com.packt.webstore.interceptor.ProcessingTimeLogInterceptor;
import com.packt.webstore.interceptor.PromoCodeInterceptor;
import com.packt.webstore.validator.CustomerValidator;
//import com.packt.webstore.validator.ProductImageValidator;
import com.packt.webstore.validator.ProductValidator;
import com.packt.webstore.validator.UnitsInStockValidator;
import com.packt.webstore.validator.BillingAddressValidator;

@Configuration
@EnableWebMvc
@ComponentScan("com.packt.webstore")
public class WebApplicationContextConfig extends WebMvcConfigurerAdapter {
	
	 


	public void configureDefaultServletHandeling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Bean
	public InternalResourceViewResolver getInternalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setViewClass(JstlView.class);
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		
		return resolver;
		
	}
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource resource = new ResourceBundleMessageSource();
		resource.setBasename("messages");
		return resource;
		}

	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		UrlPathHelper urlPathHelper = new UrlPathHelper();
		urlPathHelper.setRemoveSemicolonContent(false);
	
		
		configurer.setUrlPathHelper(urlPathHelper);
	}
	
	@Bean
	public CommonsMultipartResolver multipartResolver() {
	CommonsMultipartResolver resolver=new
	CommonsMultipartResolver();
	resolver.setDefaultEncoding("utf-8");
	resolver.setMaxUploadSize(10240000);
	return resolver;
	}
	
	// JSON view
	
	@Bean
	public MappingJackson2JsonView jsonView() {
		
		MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
		jsonView.setPrettyPrint(true);
		return jsonView;
	}
	
	// XML view
	
	@Bean
	public MarshallingView xmlView () {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setClassesToBeBound(Product.class, Customer.class);
		MarshallingView xmlView = new MarshallingView(marshaller);
		return xmlView;
	}
	
	// Configure ContentNegotiatingView Resolver
	
	@Bean
	public ViewResolver contentNegotiatingViewResolver(
						ContentNegotiationManager manager) {
	
		ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
		resolver.setContentNegotiationManager(manager);
		ArrayList<View> views = new ArrayList<>();
		views.add(jsonView());
		views.add(xmlView());
		resolver.setDefaultViews(views);
		return resolver;
	}
	
	
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry
	registry) {
	registry.addResourceHandler("/img/**")
	.addResourceLocations("/resources/images/");
	registry.addResourceHandler("/pdf/**").addResourceLocations("/resources/pdf/");
	registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	
	// Interceptor
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new ProcessingTimeLogInterceptor());
		// add support for Internationalization
		LocaleChangeInterceptor localeChangeInterceptor = new
				LocaleChangeInterceptor();
				localeChangeInterceptor.setParamName("language");
				registry.addInterceptor(localeChangeInterceptor);
				registry.addInterceptor(promoCodeInterceptor())
				.addPathPatterns("/**/market/products/specialOffer");
	}
	
	// Internationalization
	@Bean
	public LocaleResolver localeResolver(){
	SessionLocaleResolver resolver = new
	SessionLocaleResolver();
	resolver.setDefaultLocale(new Locale("en"));
	return resolver;
	}
	
	// bean deffinition for PromoCodeInterceptor
	
	@Bean
	public HandlerInterceptor promoCodeInterceptor() {
		PromoCodeInterceptor promoCodeInterceptor = new PromoCodeInterceptor();
		
		promoCodeInterceptor.setPromoCode("OFF3R");
		promoCodeInterceptor.setOfferRedirect("market/products");
		promoCodeInterceptor.setErrorRedirect("invalidPromoCode");
		return promoCodeInterceptor;
	}
	
	@Bean(name = "validator")
	public LocalValidatorFactoryBean validator() {
	LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
	bean.setValidationMessageSource(messageSource());
	return bean;
	}
	@Override
	public Validator getValidator(){
	return validator();
	}
	
	// spring validators and bean validators
	@Bean
	public ProductValidator productValidator () {
	Set<Validator> springValidators = new HashSet<>();
	springValidators.add(new UnitsInStockValidator());
	//springValidators.add(new ProductImageValidator());
	ProductValidator productValidator = new
	ProductValidator();
	productValidator.setSpringValidators(springValidators);
	return productValidator;
	}
	
	@Bean
	public CustomerValidator customerValidator () {
		Set<Validator> springValidators = new HashSet<>();
		springValidators.add(new BillingAddressValidator());
		
	CustomerValidator customerValidator = new CustomerValidator();
	customerValidator.setSpringValidators(springValidators);
	return customerValidator;
	}
	
	
	
	
	
	
}
