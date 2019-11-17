//package com.madbarsoft.config;
//
//import java.util.Collection;
//
//import org.assertj.core.util.Arrays;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//public class MyUserDetails implements UserDetails {
//
//	private String userName;
//
//	public MyUserDetails() {
//	}
//
//	public MyUserDetails(String userName) {
//		this.userName = userName;
//	}
//
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		return (Collection<? extends GrantedAuthority>) Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
//	}
//
//	@Override
//	public String getPassword() {
//		return "pass";
//	}
//
//	@Override
//	public String getUsername() {
//		return userName;
//	}
//
//	@Override
//	public boolean isAccountNonExpired() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//}
