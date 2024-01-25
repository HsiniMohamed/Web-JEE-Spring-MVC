package org.sid.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Autowired
	private PasswordEncoder passwordEncoder;

	
		//In Memory Authentifcation method
	@Bean
	public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
		
		return new InMemoryUserDetailsManager(
				User.withUsername("user1").password(passwordEncoder.encode("1234")).roles("USER").build(),
				User.withUsername("user2").password(passwordEncoder.encode("1234")).roles("USER").build(),
				User.withUsername("admin").password(passwordEncoder.encode("1234")).roles("USER","ADMIN").build()
				);
	}
	
	
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		
		//utlisation de formulaire d'authentification
		httpSecurity.formLogin().loginPage("/login").defaultSuccessUrl("/",true).permitAll();
		
		httpSecurity.rememberMe();

		//permission d'accées au webjars "bootstrap".....
		httpSecurity.authorizeHttpRequests().requestMatchers("/webjars/**").permitAll();

		//gestions des roles et droit d'accés ou Avec les annotations
		
		//httpSecurity.authorizeHttpRequests().requestMatchers("/user/**").hasRole("USER");
		//httpSecurity.authorizeHttpRequests().requestMatchers("/admin/**").hasRole("ADMIN");

		//toutes les requetes depend une authentification
		httpSecurity.authorizeHttpRequests().anyRequest().authenticated();
				
		//Redirection dans le cas non autorisé
		httpSecurity.exceptionHandling().accessDeniedPage("/notAuthorized");
		return httpSecurity.build();
	}
	
}
