package com.jumia.countryphone.service;

import com.jumia.countryphone.dto.CustomerDto;
import com.jumia.countryphone.enums.Country;
import com.jumia.countryphone.enums.PhoneStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {

    Page<CustomerDto> getCustomers(Country country, PhoneStatus status, Pageable pageable);
}
