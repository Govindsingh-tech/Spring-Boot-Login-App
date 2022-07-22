package com.spring.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.model.Customer;



@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {
	
	Customer findByUsername(String username);

	Object findByUsernameAndPassword(String username, String password);


}
