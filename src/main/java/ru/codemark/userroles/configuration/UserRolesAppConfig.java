package ru.codemark.userroles.configuration;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import ru.codemark.userroles.service.UserService;

import javax.validation.Validator;
import javax.xml.ws.Endpoint;

@Configuration
public class UserRolesAppConfig {

	@Autowired
	private Bus bus;

	@Autowired
	UserService userService;
	
	@Bean
	public Endpoint userServiceEndpoint() {
		EndpointImpl endpoint = new EndpointImpl(bus, userService);
		endpoint.publish("/UserService");
		return endpoint;
	}

	@Bean
	public Validator localValidatorFactoryBean() {
		return new LocalValidatorFactoryBean();
	}

}
