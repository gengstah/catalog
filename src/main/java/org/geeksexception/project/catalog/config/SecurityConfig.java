package org.geeksexception.project.catalog.config;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private @Inject UserDetailsService userDetailsService;
	
	public SecurityConfig() { }
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder(11));
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		/* Dev */
//		http
//		.authorizeRequests()
//			.antMatchers("/api/service/**/**").permitAll()
//			.and()
//		.csrf().disable();
		
		/* Prod */
		http
			.authorizeRequests()
				.antMatchers("/api/service/user/authenticate", "/api/service/lender/*/register", "/api/service/borrower/register", "/api/service/lender/getAll", "/api/service/user/forgotpassword").permitAll()
				.antMatchers(HttpMethod.OPTIONS, "/api/service/lender/**", "/api/service/borrower/**", "/api/service/loancriteria/**", "/api/service/loanrequest/**", "/api/service/loan/**").permitAll()
				.antMatchers("/api/service/user/**", "/api/service/contact/**", "/api/service/countydetail/**", "/api/service/loan/**").authenticated()
				.antMatchers("/api/service/lender/**", "/api/service/loancriteria/**").hasRole("LENDER")
				.antMatchers("/api/service/borrower/**", "/api/service/loanrequest/**").hasRole("BORROWER")
				.antMatchers("/api/service/admin/**").hasRole("ADMIN")
				.and()
			.csrf().disable()
			.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
		
	}
	
	@Bean(name="authenticationManager")
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
}