package com.madbarsoft.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.madbarsoft.auth.JwtRequestFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myUserDetailsService);	
	}

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests().antMatchers("/home").permitAll()
		.antMatchers("/authentication").permitAll()
	    .antMatchers("/user").hasAuthority("USER")
 		.antMatchers("/admin").hasAuthority("ADMIN")
	    .antMatchers("/super-admin").hasAuthority("SUPER_ADMIN")
		.anyRequest().authenticated()
		.and().sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		
		http.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/home");
			
	}
	
	@Bean
	@Override
	public  AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	 @Bean
	 public static NoOpPasswordEncoder passwordEncoder() {
	  return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	 }
	
	

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//
//		 //authorize requests		
// 		http.authorizeRequests()
	//	.and().httpBasic();
// 				.antMatchers("/").permitAll()
// 				.antMatchers("/home").permitAll()
// 				.antMatchers("/gnr-auth-token").permitAll()
// 				.antMatchers("/auth/**").permitAll()
//	     		.antMatchers("/admin/**").hasRole("ADMIN")
// 				.antMatchers("/user").hasAnyAuthority("USER","ADMIN")
// 				.anyRequest()
// 				.authenticated().and().csrf().disable();
//
//				http.formLogin()
//				.loginPage("/login");
//
//				http.logout()
//				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//				//.deleteCookies("JSESSIONID")
//				.logoutSuccessUrl("/home");
//			
//	}
	


	
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
