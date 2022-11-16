package com.madbarsoft.auth;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.madbarsoft.config.MyUserDetailsService;
import com.madbarsoft.utility.JwtUitl;
import com.madbarsoft.utility.MyCustomException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {	
	
	@Autowired
	private JwtUitl jwtUitl;
	
	@Autowired
	private MyUserDetailsService userDetailsService; 

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chin)
			throws ServletException, IOException {
		
		final String authorizationHeader = request.getHeader("Authorization");
		System.out.println("Authorization Header #:"+authorizationHeader);
		
		String userName = null;
		String jwtStr = null;
		

		if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
			jwtStr = authorizationHeader.substring(7);
			userName = jwtUitl.extractUserName(jwtStr);
			System.out.println("Form JWT userName: "+userName);
		}
		System.out.println("In Filter Before set #: "+SecurityContextHolder.getContext().getAuthentication());
		if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null){
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);
			if(jwtUitl.validateToken(jwtStr, userDetails)){
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
			System.out.println("In Filter After Set #: "+SecurityContextHolder.getContext().getAuthentication());
		}
		chin.doFilter(request, response);
	
	}




}
