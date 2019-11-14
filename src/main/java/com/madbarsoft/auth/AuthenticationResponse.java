package com.madbarsoft.auth;


public class AuthenticationResponse {
	
	private final String jwtStr;
	
	public AuthenticationResponse(String jwt){
		this.jwtStr = jwt;
	}
	
	public String getJwt(){
		return jwtStr;
	}

}
