package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.model.Customer;
import com.spring.service.UserDetailService;

@RestController
public class MainController {

	@Autowired
	private UserDetailService userDetailsService;

	@PostMapping(value = "/user/registration", consumes = { "application/json" })
	public String Registration(@RequestBody Customer customer) {
		userDetailsService.saveCustomer(customer);
		return "Registered Success";
	}

	@PostMapping("/user/login")
	public String Registration(@RequestParam String username, @RequestParam String password) {

		return userDetailsService.login(username, password);
	}

	@GetMapping("/getAll")
	public List<Customer> getAllCustomer() {

		return userDetailsService.getCustomerDetails();
	}

	@GetMapping("/")
	public String welcome() {

		return "login success";
	}

}
