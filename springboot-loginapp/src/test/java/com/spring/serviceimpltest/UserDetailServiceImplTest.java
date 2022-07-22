package com.spring.serviceimpltest;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.spring.controller.MainController;
import com.spring.model.AuthRequest;
import com.spring.model.Customer;
import com.spring.repo.CustomerRepo;
import com.spring.service.UserDetailService;
import com.spring.util.JwtUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDetailServiceImplTest {
	
	public MockMvc mockMvc;

	@Autowired
	public JwtUtil jwtUtil;
	
	@Test
	void contextLoads() {

	}

	@InjectMocks
	private MainController mainController;

	@InjectMocks
	private AuthRequest authRequest;
	@InjectMocks
	private Customer customer;

	@Autowired
	private UserDetailService userDetailsService;

	@MockBean
	private CustomerRepo customerrepo;

	@Autowired
	WebApplicationContext webApplicationContext;

	@Test
	public void getCustomerDetailsTest() {
		when(customerrepo.findAll())
				.thenReturn(Stream.of(new Customer(1, "name", "password")).collect(Collectors.toList()));
		assertEquals(1, userDetailsService.getCustomerDetails().size());
	}

	@Test
	public void findByUserNameTest() {
		String username = "name";
		when(customerrepo.findByUsername(username)).thenReturn(new Customer(1, "name", "password"));
		assertNotNull(userDetailsService.findByUserName(username));
	}
	
	@Test
	public void findByUsernameAndPassword() {
		String username = "name";
		String password = "password";
		when(customerrepo.findByUsernameAndPassword(username, password)).thenReturn(new Customer(1, "name", "password"));
		assertNotNull(userDetailsService.login(username, password));
	}

	@Test
	public void loginTest() {
		String username = "name";
		String password = "password";
		when(customerrepo.findByUsernameAndPassword(username, password))
				.thenReturn(new Customer(1, "name", "password"));
		assertNotNull(userDetailsService.login(username, password));
	}

	@Test
	public void updatePasswordTest() {
		String username = "name";
		String password = "password";
		when(customerrepo.save(new Customer(1, "name", "password")))
				.thenReturn(new Customer(1, "name", "password"));
		assertNotNull(userDetailsService.resetpassword(username, password, password));
	}

}
