package com.madbarsoft.utility;

public class MyCustomException extends RuntimeException {
	
	public MyCustomException(String errorMessage) {
		super(errorMessage);
	}
}
