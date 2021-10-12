package com.customers.sqllite.services;

import com.customers.sqllite.dto.CustomerDto;
import com.customers.sqllite.entities.Customer;
import java.util.List;
import java.util.Map;

public interface FacadeService {

    List<CustomerDto> getCustomersResponseList(Map<String, String> customers);
    List<Customer> getCustomersList(Map<String, String> parameters);
}
