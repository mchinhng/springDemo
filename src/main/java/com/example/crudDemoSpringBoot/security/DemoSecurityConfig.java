package com.example.crudDemoSpringBoot.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {

	@Bean
	public InMemoryUserDetailsManager userDetailManager() {
		UserDetails chinh = User.builder().username("chinh").password("{noop}123").roles("EMPLOYEE").build();

		UserDetails la = User.builder().username("la").password("{noop}123").roles("EMPLOYEE", "MANAGER").build();

		UserDetails le = User.builder().username("le").password("{noop}123").roles("EMPLOYEE", "MANAGER", "ADMIN")
				.build();
		return new InMemoryUserDetailsManager(chinh, la, le);
	}

	// add support for JDBC
//	@Bean
//	public UserDetailsManager userDetailsManager(DataSource dataSource) {
//
//		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//
//		// define query to retrieve a user by username
//		jdbcUserDetailsManager.setUsersByUsernameQuery("select user_id, pw, active from members where user_id=?");
//
//		// define query to retrieve the authorities/roles by username
//		jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select user_id, role from roles where user_id=?");
//
//		// tell spring security to user jdbc authentication with our datasource
//		return jdbcUserDetailsManager;
//	}

//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		http.authorizeHttpRequests(configurer -> configurer.requestMatchers(HttpMethod.GET, "/api/employees")
//				.hasRole("EMPLOYEE").requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
//				.requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
//				.requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("MANAGER")
//				.requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ASDMIN"));
//
//		// use HTTP Basic authentication
//		http.httpBasic(Customizer.withDefaults());
//
//		// disabled Cross Site Request Forgery (CSRF)
//		http.csrf(csrf -> csrf.disable());
//		return http.build();
//	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(
				configurer -> configurer
//				.requestMatchers("/").hasRole("EMPLOYEE")
				.anyRequest().authenticated())
				.formLogin(form -> form.loginPage("/showMyLoginPage").loginProcessingUrl("/authenticateTheUser")
						.permitAll())
				.logout(logout -> logout.permitAll().logoutSuccessUrl("/showMyLoginPage"));
//				.exceptionHandling(configurer -> configurer.accessDeniedPage("/access-deined"));

		return http.build();
	}
}
