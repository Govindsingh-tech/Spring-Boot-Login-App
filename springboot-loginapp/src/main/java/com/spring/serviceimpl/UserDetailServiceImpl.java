package com.spring.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.encoding.PasswordHashing;
import com.spring.model.Customer;
import com.spring.repo.CustomerRepo;
import com.spring.service.UserDetailService;
import com.spring.util.JwtUtil;

@Service
public class UserDetailServiceImpl implements UserDetailsService, UserDetailService {
	
	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private JwtUtil jwtUtil;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer customer = customerRepo.findByUsername(username);
		if(customer!=null) {
			return new User(customer.getUsername(), customer.getPassword(), new ArrayList<>());
	
		}
		return null;

	}
	public Customer saveCustomer(Customer customer) {
		customer.setPassword(PasswordHashing.hasPassword(customer.getPassword()));
		return customerRepo.save(customer);
	}
	public List<Customer> getCustomerDetails(){
		List<Customer> customerList = customerRepo.findAll();
		return customerList;
	}
	@Override
	public Customer findByUserName(String username) {
		Customer customerList = customerRepo.findByUsername(username);
		return customerList;
	}

	@Override
	public String login(String username, String password) {

		Customer customer = customerRepo.findByUsername(username);

		if (customer != null) {
			String stored_hash = customer.getPassword();
			
			if (PasswordHashing.checkPassword(password, stored_hash))
				return jwtUtil.generateToken(username);
			else
				return "Failed to Login";
		}
		return "Failed to Login";
	}
	
	@Override
	public String resetpassword(String username, String password, String newPassword) {
		Customer customer = customerRepo.findByUsername(username);

		if (customer != null) {
			if (PasswordHashing.checkPassword(password, customer.getPassword())) {
				customer.setPassword(PasswordHashing.hasPassword(newPassword));
				customerRepo.save(customer);
				return "Password Updated successfully";
			}else {
				return "Password is incorrect";
			}
		}
		return "Failed to Update Password";
	}

}
