package com.jumia.countryphone.service;

import com.jumia.countryphone.dto.CustomerDto;
import com.jumia.countryphone.entities.Customer;
import com.jumia.countryphone.enums.Country;
import com.jumia.countryphone.enums.PhoneStatus;
import com.jumia.countryphone.repositories.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService = new CustomerServiceImpl();

    @Test
    void shouldReturnAllCustomersByPage() {

        final List<Customer> customersMock = Arrays.asList(Customer.builder()
                .id(1L)
                .name("Tanvi Sachdeva")
                .phone("(258) 849181828")
                .build(),
                Customer.builder()
                .id(2L)
                .name("Yonatan Tekelay")
                .phone("(251) 911168450")
                .build(),
                Customer.builder()
                .id(3L)
                .name("Fanetahune Abaia")
                .phone("(212) 691933626")
                .build(),
                Customer.builder()
                .id(4L)
                .name("Nelson Nelson")
                .phone("(251) 911168450")
                .build());

        when(customerRepository.findAll()).thenReturn(customersMock);

        final Page<CustomerDto> customers = customerService.getCustomers(null, null,
            PageRequest.of(0, 3));

        assertNotNull(customers);
        assertEquals(4, customers.getTotalElements());
        assertEquals(2, customers.getTotalPages());
        assertEquals(3, customers.getSize());

        assertEntity(customersMock.get(0), customers.toList().get(0), null, null);
        assertEntity(customersMock.get(1), customers.toList().get(1), null, null);
        assertEntity(customersMock.get(2), customers.toList().get(2), null, null);
    }

    @Test
    void shouldFilterCustomersByCountryAndStatus() {

        final List<Customer> customersMock = Arrays.asList(Customer.builder()
                .id(1L)
                .name("Tanvi Sachdeva")
                .phone("(258) 849181828")
                .build(),
            Customer.builder()
                .id(2L)
                .name("Fanetahune Abaia")
                .phone("(212) 691933626")
                .build(),
            Customer.builder()
                .id(3L)
                .name("Nelson Nelson")
                .phone("(251) 911168450")
                .build(),
            Customer.builder()
                .id(4L)
                .name("Walid Hammadi")
                .phone("(212) 6007989253")
                .build(),
                Customer.builder()
                .id(5L)
                .name("Chouf Malo")
                .phone("(212) 691933626")
                .build());

        when(customerRepository.findAll()).thenReturn(customersMock);

        final Page<CustomerDto> customers = customerService.getCustomers(Country.MOROCCO, PhoneStatus.VALID,
            PageRequest.of(0, 3));

        assertNotNull(customers);
        assertEquals(2, customers.getTotalElements());
        assertEquals(1, customers.getTotalPages());
        assertEquals(3, customers.getSize());

        assertEntity(customersMock.get(1), customers.toList().get(0), Country.MOROCCO, PhoneStatus.VALID);
        assertEntity(customersMock.get(4), customers.toList().get(1), Country.MOROCCO, PhoneStatus.VALID);
    }

    private void assertEntity(Customer customer, CustomerDto savedEntity, Country country,
        PhoneStatus status) {

        assertNotNull(savedEntity.getId());
        assertEquals(customer.getId(), savedEntity.getId());
        assertEquals(customer.getName(), savedEntity.getName());
        assertEquals(customer.getPhone(), savedEntity.getPhone());
        assertNotNull(savedEntity.getCountry());
        assertNotNull(savedEntity.getPhoneStatus());

        if (country != null) {
            assertEquals(country, savedEntity.getCountry());
        }
        if (status != null) {
            assertEquals(status, savedEntity.getPhoneStatus());
        }
    }
}
