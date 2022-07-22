package com.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.service.UserDetailService;

@RestController
public class ResetController {
	
	@Autowired
	private UserDetailService userDetailsService;
	
	@RequestMapping("/resetpassword")
	public String resetpassword(@RequestParam String username, @RequestParam String oldpassword,
			@RequestParam String newPassword) {
		return userDetailsService.resetpassword(username, oldpassword, newPassword);
	}

}
