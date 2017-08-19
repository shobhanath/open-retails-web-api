package com.openretails.web.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		final String findUserQuery = "select username,password,enabled " + "from T_USER " + "where username = ?";
		final String findRoles = "select username,role " + "from Roles " + "where username = ?";

		auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(findUserQuery)
				.authoritiesByUsernameQuery(findRoles);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/**").permitAll().antMatchers(HttpMethod.GET, "/**").hasRole("ADMIN")
				.anyRequest().authenticated().and().formLogin().permitAll().and().logout().permitAll();

	}
}