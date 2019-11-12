package com.madbarsoft.user;

import java.util.ArrayList;
import java.util.List;


public class UserModel {
	
	public UserModel(){
		
	}
	
	public UserModel(String userName, String password, List<String> roles) {
		this.userName = userName;
		this.password = password;
		this.roles = roles;
	}

	private String userName;
	
	private String password;
	
	private boolean active;

	
	
	private List<String> roles = new ArrayList<>();
	
	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
	
	
	

}
