package com.madbarsoft.auth;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
	private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationFailureHandler.class);

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		String contextPath = request.getContextPath();
		logger.error(exception.toString());

		System.out.println("From Auth Filur Handler");
		if (exception instanceof AccountExpiredException) {
			System.out.println("Sorry, your account has expired.");
			request.getSession().setAttribute("failMsg", "Sorry, your account has expired.");
		} else if (exception instanceof CredentialsExpiredException) {
			System.out.println("Sorry, your password has expired.");
			request.getSession().setAttribute("failMsg", "Sorry, your password has expired.");
		} else if (exception instanceof DisabledException) {
			System.out.println("Sorry, your account is disabled.");
			request.getSession().setAttribute("failMsg", "Sorry, your account is disabled.");

		} else if (exception instanceof LockedException) {
			System.out.println("Sorry, your account is locked.");
			request.getSession().setAttribute("failMsg", "Sorry, your account is locked.");
		}

		else if (exception instanceof BadCredentialsException) {
			System.out.println("Sorry, username or password not found");
			request.getSession().setAttribute("failMsg", "Sorry, username or password not found");
		}

		else if (exception instanceof InternalAuthenticationServiceException) {
			System.out.println(exception.getMessage());
			request.getSession().setAttribute("failMsg", exception.getMessage());
		}

		response.sendRedirect(contextPath + "/auth/login?error=true");
	}

}
