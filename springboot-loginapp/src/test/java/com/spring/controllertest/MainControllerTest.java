package com.spring.controllertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.controller.MainController;
import com.spring.model.AuthRequest;
import com.spring.model.Customer;
import com.spring.repo.CustomerRepo;
import com.spring.service.UserDetailService;
import com.spring.util.JwtUtil;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MainControllerTest {
	
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
	public void registrationTest() throws Exception {
		Customer customer = new Customer();
		customer.setPassword("password");
		customer.setUsername("username");
		ObjectMapper obj = new ObjectMapper();
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		String uri = "/registration";
		String jsoRequest = obj.writeValueAsString(customer);
		MvcResult mvcResult = mockMvc.perform(post(uri).content(jsoRequest).accept(MediaType.APPLICATION_JSON_VALUE)
				.header(HttpHeaders.AUTHORIZATION, "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuYW1lIiwiZXhwIjoxNjU4MjE1MzI3LCJpYXQiOjE2NTgxNzkzMjd9.8TPCqdShvR4Cue9geomgpHGAXD9HRIolfu9q5cssH80")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
//		String resultContent = mvcResult.getResponse().getContentAsString();
//		Assert.assertTrue(resultContent.equals("Registered Success"));

	}
	
	@Test
	public void getCustomerList() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		String uri = "/getAll";
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}

	@Test
	public void welcomePageTest() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		String uri = "/";
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}


}
