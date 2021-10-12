package com.customers.sqllite.controllers;

import java.util.List;
import java.util.Map;
import com.customers.sqllite.dto.CustomerDto;
import com.customers.sqllite.services.FacadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api")

public class CustomerController {

	@Autowired
	FacadeService facadeService;

	@GetMapping("/customers")
	public List<CustomerDto> getCustomers(@RequestParam Map<String, String> allParams) {

			return facadeService.getCustomersResponseList(allParams);

	}

}
