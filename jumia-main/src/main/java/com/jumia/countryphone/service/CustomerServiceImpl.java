package com.jumia.countryphone.service;

import com.jumia.countryphone.dto.CustomerDto;
import com.jumia.countryphone.enums.Country;
import com.jumia.countryphone.enums.PhoneStatus;
import com.jumia.countryphone.mapper.CustomerMapper;
import com.jumia.countryphone.repositories.CustomerRepository;
import com.jumia.countryphone.utils.PaginationUtils;
import com.jumia.countryphone.validators.PhoneValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    /**
     * Returns customers list based on country, status and current pagination state.
     *
     * @param country
     * @param status
     * @param pageable
     * @return list of customers with pagination.
     */
    public Page<CustomerDto> getCustomers(Country country, PhoneStatus status, Pageable pageable) {

        final List<CustomerDto> customers = Optional.ofNullable(customerRepository.findAll())
                .map(entities -> entities.stream()
                        .map(CustomerMapper.INSTANCE::entityToDto)
                        .peek(CustomerServiceImpl::populateCountryAndPhoneStatus)
                        .filter(c -> country == null || c.getCountry() == country)
                        .filter(c -> status == null || c.getPhoneStatus() == status)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());

        return PaginationUtils.buildListWithPagination(customers, pageable);
    }


    private static void populateCountryAndPhoneStatus(CustomerDto customerDto) {
        customerDto.setCountry(Country.getCountryByPhone(customerDto.getPhone()));
        customerDto.setPhoneStatus(PhoneValidator.isValid(customerDto.getCountry(), customerDto.getPhone()));
    }
}
