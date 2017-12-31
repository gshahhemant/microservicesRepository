package com.microservices.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

/**
 * 
 * @author Hemant Shah
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static String REALM_NAME = "RESTFUL_REALM";

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers(HttpMethod.POST, "/services/v2/**")
				.hasAnyAuthority("ADMIN").antMatchers(HttpMethod.PUT, "/services/v2/**").hasAnyAuthority("ADMIN")
				.antMatchers(HttpMethod.DELETE, "/services/v2/**").hasAnyAuthority("ADMIN")
				.antMatchers(HttpMethod.GET, "/services/v2/**").hasAnyAuthority("ADMIN").and().formLogin().permitAll()
				.and().httpBasic().realmName(REALM_NAME).authenticationEntryPoint(getBasicAuthEntryPoint());
		// .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);//
		// default it is statefull
	}

	@Bean
	public BasicAuthenticationEntryPoint getBasicAuthEntryPoint() {
		BasicAuthenticationEntryPoint basicAuthEntryPoint = new BasicAuthenticationEntryPoint();
		basicAuthEntryPoint.setRealmName(REALM_NAME);
		return basicAuthEntryPoint;
	}

}
