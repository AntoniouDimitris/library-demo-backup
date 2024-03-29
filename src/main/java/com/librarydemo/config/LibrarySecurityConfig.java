package com.librarydemo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class LibrarySecurityConfig extends WebSecurityConfigurerAdapter {

	// add a reference to our security data source
	@Autowired
	private DataSource securityDataSource;
			
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// use jdbc authentication!
		auth.jdbcAuthentication().dataSource(securityDataSource);
	}

		
	@Override
	protected void configure(HttpSecurity http) throws Exception {
			
		http.csrf().disable();
			
		http.authorizeRequests()
		.antMatchers("/").hasRole("LENDER")
	.and()
	.formLogin()
		.loginPage("/showMyLoginPage")
		.loginProcessingUrl("/authenticateTheUser")
		.permitAll()
	.and()
	.logout().permitAll()
	.and()
	.exceptionHandling().accessDeniedPage("/access-denied");
		}
}
