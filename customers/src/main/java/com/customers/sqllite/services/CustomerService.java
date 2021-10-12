package com.customers.sqllite.services;

import java.util.List;
import com.customers.sqllite.entities.Customer;

public interface CustomerService {
	
	 List<Customer> findAll();
	 List<Customer> findByCountry(String country);
	 List<Customer> findByState(String state, List<Customer> categorizedcustomers);
	 List<Customer> findByNumber(String number);

}
