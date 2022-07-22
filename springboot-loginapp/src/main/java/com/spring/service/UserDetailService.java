package com.spring.service;

import java.util.List;

import com.spring.model.Customer;

public interface UserDetailService {

	public Customer saveCustomer(Customer customer);

	Customer findByUserName(String username);

	public String login(String username, String password);

	List<Customer> getCustomerDetails();

	String resetpassword(String username, String oldpassword, String newPassword);

}
