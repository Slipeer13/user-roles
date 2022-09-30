package ru.codemark.userroles;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.codemark.userroles.service.UserServiceImpl;

import javax.xml.ws.Endpoint;

@Configuration
public class UserRolesAppConfig {

	@Autowired
	private Bus bus;
	
	@Bean
	public Endpoint helloEndpoint() {
		EndpointImpl endpoint = new EndpointImpl(bus, new UserServiceImpl());
		endpoint.publish("/UserService");
		return endpoint;
	}

}
