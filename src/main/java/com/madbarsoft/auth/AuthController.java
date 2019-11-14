package com.madbarsoft.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.madbarsoft.config.MyUserDetailsService;
import com.madbarsoft.utility.JwtUitl;

@RestController
public class AuthController {
	
	
	@Autowired
	private AuthenticationManager authenticationManager; 
	
	@Autowired
	private MyUserDetailsService userDetailsService; 
	
	
	@Autowired
	private JwtUitl jwtUitl; 
	
	
	
	@RequestMapping(value="gnr-auth-token", method= RequestMethod.POST)
	public ResponseEntity<?> createAuthToken(@RequestBody AuthenticationRequest authReq) throws Exception{
		
		try{
			
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authReq.getUserName(), authReq.getPassword())
					);
			
		}catch(BadCredentialsException e){
			throw new Exception("Incorrect username or password", e);
		}
		
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authReq.getUserName());
		
		final String jwt = jwtUitl.generateToken(userDetails);
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
		
	}
	
	
	
	@GetMapping({"/test-auth"})
	public String testMsg(){
		return "Auth Test action";
	}
	
	

}
