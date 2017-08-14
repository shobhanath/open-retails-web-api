package com.openretails.web.interceptor.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.openretails.web.interceptor.AuditInterceptor;
import com.openretails.web.interceptor.AuthenticationInterceptor;

@Configuration
public class RegisterInterceptor extends WebMvcConfigurerAdapter{

	@Autowired
	private AuthenticationInterceptor authenticationInterceptor;
	@Autowired
	private AuditInterceptor auditInterceptor;


	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authenticationInterceptor);
		registry.addInterceptor(auditInterceptor);
	}
}
