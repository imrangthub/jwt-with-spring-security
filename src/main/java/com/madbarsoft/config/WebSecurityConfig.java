package com.madbarsoft.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myUserDetailsService);
		
	}
	
	

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//
//		 //authorize requests		
// 		http.authorizeRequests()
// 				.antMatchers("/").permitAll()
// 				.antMatchers("/home").permitAll()
// 				.antMatchers("/auth/**").permitAll()
//	     		.antMatchers("/admin/**").hasRole("ADMIN")
// 				.antMatchers("/user").hasAnyAuthority("USER","ADMIN")
// 				.anyRequest()
// 				.authenticated().and().csrf().disable();
//	}
	
	 @Bean
	 public static NoOpPasswordEncoder passwordEncoder() {
	  return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	 }

	
//	@Autowired
//	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
//	    BCryptPasswordEncoder encoder = passwordEncoder();
//	    auth.inMemoryAuthentication()
//	    .withUser("user").password(encoder.encode("12345")).roles("USER")
//	    .and()
//	    .withUser("admin").password(encoder.encode("123456")).roles("USER", "ADMIN");
//	}

//	@Bean
//	public BCryptPasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}

}
