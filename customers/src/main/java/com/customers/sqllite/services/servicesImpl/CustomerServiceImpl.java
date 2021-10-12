package com.customers.sqllite.services.servicesImpl;

import java.util.ArrayList;
import java.util.List;
import com.customers.sqllite.exceptions.NotFoundException;
import com.customers.sqllite.services.CustomerService;
import com.customers.sqllite.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.customers.sqllite.entities.Customer;
import com.customers.sqllite.repo.CustomerRepo;

@Service
public class CustomerServiceImpl implements CustomerService {

	private String code;

	private List<Customer> returnedCutomers;

	@Autowired
	CustomerRepo customerRepo;

	@Autowired
	Utils utils;

	@Override
	public List<Customer> findAll() {
		return customerRepo.findAll();
	}

	@Override
	public List<Customer> findByCountry(String country) {

		code = utils.getCountryCodeBasedOnCountry(country);
		return customerRepo.findByPhoneContaining(code);

	}

	@Override
	public List<Customer> findByState(String state, List<Customer> customers) {

		   returnedCutomers = new ArrayList<Customer>();
			if (state.equalsIgnoreCase("valid")) {
				for (int i = 0; i < customers.size(); i++) {
					boolean validateNumber = Utils.validateNumber(customers.get(i).getPhone());
//	        			check if number is valid
					if (validateNumber == true) {
						returnedCutomers.add(customers.get(i));
					}
				}
			}

			else if (state.equalsIgnoreCase("notValid")) {
				for (int i = 0; i < customers.size(); i++) {
					boolean validateNumber = Utils.validateNumber(customers.get(i).getPhone());
//	        			check if number is not valid
					if (validateNumber == false) {
						returnedCutomers.add(customers.get(i));
					}
				}
			}

			else{
				throw new NotFoundException("this state is not available");
			}

			return returnedCutomers;
	}

	@Override
	public List<Customer> findByNumber(String number) {

			customerRepo.findByPhoneContaining(number);
	        return customerRepo.findByPhoneContaining(number);

	}

}
