package com.portfolio.rest.webservices.restfulwebservices.security;
//import static org.springframework.security.config.Customizer.*;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class SpringSecurityConfiguration {
//
//	//@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		//overiding default filter chain
//		//1) all requests should be Authenticated
//		http.authorizeHttpRequests(
//				auth->auth.anyRequest().authenticated()
//				);
//		//2)if a request is not autherized, a web page is shown
//		http.httpBasic(withDefaults());
//		//3 CSRF->Post,put
//		http.csrf().disable();
//		return http.build();
//		
//	
//	}
//}
