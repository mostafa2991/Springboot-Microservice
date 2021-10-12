package com.jumia.countryphone.utils;

import com.jumia.countryphone.dto.CustomerDto;
import com.jumia.countryphone.enums.Country;
import com.jumia.countryphone.enums.PhoneStatus;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PaginationUtilsTest {

    @Test
    void shouldBuildListWithPagination() {

        final List<CustomerDto> customers = Arrays.asList(CustomerDto.builder()
                .id(1L)
                .name("Nelson Nelson")
                .phone("(251) 911168450")
                .country(Country.ETHIOPIA)
                .phoneStatus(PhoneStatus.INVALID)
                .build(),
                CustomerDto.builder()
                .id(2L)
                .name("Tanvi Sachdeva")
                .phone("(258) 849181828")
                .country(Country.MOZAMBIQUE)
                .phoneStatus(PhoneStatus.INVALID)
                .build(),
                CustomerDto.builder()
                .id(3L)
                .name("Yonatan Tekelay")
                .phone("(251) 911168450")
                .country(Country.ETHIOPIA)
                .phoneStatus(PhoneStatus.INVALID)
                .build(),
                CustomerDto.builder()
                .id(4L)
                .name("Fanetahune Abaia")
                .phone("(212) 691933626")
                .country(Country.MOROCCO)
                .phoneStatus(PhoneStatus.VALID)
                .build());

        final Page<CustomerDto> customersWithPagination = PaginationUtils
            .buildListWithPagination(customers, PageRequest.of(0, 3));

        assertNotNull(customers);
        assertEquals(4, customersWithPagination.getTotalElements());
        assertEquals(2, customersWithPagination.getTotalPages());
        assertEquals(3, customersWithPagination.getSize());

        assertEntity(customers.get(0), customersWithPagination.toList().get(0));
        assertEntity(customers.get(1), customersWithPagination.toList().get(1));
        assertEntity(customers.get(2), customersWithPagination.toList().get(2));
    }

    @Test
    void shouldBuildPageNumbers() {

        final List<CustomerDto> customers = Arrays.asList(CustomerDto.builder()
                .id(1L)
                .name("Nelson Nelson")
                .phone("(251) 911168450")
                .country(Country.ETHIOPIA)
                .phoneStatus(PhoneStatus.INVALID)
                .build(),
                CustomerDto.builder()
                .id(2L)
                .name("Tanvi Sachdeva")
                .phone("(258) 849181828")
                .country(Country.MOZAMBIQUE)
                .phoneStatus(PhoneStatus.INVALID)
                .build(),
                CustomerDto.builder()
                .id(3L)
                .name("Yonatan Tekelay")
                .phone("(251) 911168450")
                .country(Country.ETHIOPIA)
                .phoneStatus(PhoneStatus.INVALID)
                .build(),
                CustomerDto.builder()
                .id(4L)
                .name("Fanetahune Abaia")
                .phone("(212) 691933626")
                .country(Country.MOROCCO)
                .phoneStatus(PhoneStatus.VALID)
                .build());

        final Page<CustomerDto> customersWithPagination = PaginationUtils
            .buildListWithPagination(customers, PageRequest.of(0, 3));

        final List<Integer> pageNumbers = PaginationUtils.buildPageNumbers(customersWithPagination);

        assertNotNull(pageNumbers);
        assertEquals(2, pageNumbers.size());
        assertEquals(1, pageNumbers.get(0));
        assertEquals(2, pageNumbers.get(1));
    }

    private void assertEntity(CustomerDto expectedCustomer, CustomerDto responseCustomer) {
        assertNotNull(responseCustomer.getId());
        assertEquals(expectedCustomer.getId(), responseCustomer.getId());
        assertEquals(expectedCustomer.getName(), responseCustomer.getName());
        assertEquals(expectedCustomer.getPhone(), responseCustomer.getPhone());
    }
}
