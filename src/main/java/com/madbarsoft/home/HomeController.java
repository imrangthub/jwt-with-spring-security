package com.madbarsoft.home;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@GetMapping({"/home"})
	public String testMsg(){
		return "This message for all";
	}
	
	@GetMapping({"/user"})
	public String userPage(){
		return "This message for user !";
	}
	
	
	@GetMapping({"/admin"})
	public String adminPage(){
		return "This message for Admin !";
	}
	
	

}

