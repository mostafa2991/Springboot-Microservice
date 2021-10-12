package com.customers.sqllite.services.servicesImpl;

import com.customers.sqllite.dto.CustomerDto;
import com.customers.sqllite.entities.Customer;
import com.customers.sqllite.exceptions.BadUrlException;
import com.customers.sqllite.services.CustomerService;
import com.customers.sqllite.services.FacadeService;
import com.customers.sqllite.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class FacadeServiceImpl implements FacadeService {

    private List<Customer> categorizedCustomers ;
    private List<CustomerDto> customersDtoList;

    @Autowired
    CustomerService customerService;

    @Autowired
    Utils utils;

    @Override
    public List<Customer> getCustomersList(Map<String, String> parameters) {

// check for bad params
        if(! Utils.checkKeys(parameters)){
            throw new BadUrlException("Bad Url");
        }

//check for empty values
        for (Map.Entry<String,String> entry : parameters.entrySet()) {
            if(entry.getValue().trim().isEmpty()){
                throw new BadUrlException("Bad Url");
            }
        }

// get the params
        String country = parameters.get("country");
        String state = parameters.get("state");
        String number = parameters.get("number");

// check for the existence

//if a user searches by a phone number and another param
        if ( number != null) {
            categorizedCustomers = customerService.findByNumber(number);
        }
        else {
//if a user searches by a country
            if (country != null) {
                categorizedCustomers = customerService.findByCountry(country);
            }
//get all users to show all users or show all users with a state in next step
            else {
                categorizedCustomers = customerService.findAll();
            }
//get users with a state (all or filtered by countries)
            if (state != null) {
                categorizedCustomers = customerService.findByState(state, categorizedCustomers);
            }
        }

        return categorizedCustomers;

    }

    public List<CustomerDto> getCustomersResponseList(Map<String, String> parameters) {

        getCustomersList(parameters);
        customersDtoList = utils.entityListToDto( getCustomersList(parameters), CustomerDto.class);
        return customersDtoList;

    }


}



