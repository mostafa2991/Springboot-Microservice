package com.jumia.countryphone.mapper;

import com.jumia.countryphone.dto.CustomerDto;
import com.jumia.countryphone.entities.Customer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerMapperTest {

    @Test
    void shouldConvertEntityToDto() {
        final Customer customer = Customer.builder()
            .id(1000L)
            .name("Marcus Test")
            .phone("(251) 911168450")
            .build();

        final CustomerDto customerResponse = CustomerMapper.INSTANCE.entityToDto(customer);

        assertEquals(customer.getId(), customerResponse.getId());
        assertEquals(customer.getName(), customerResponse.getName());
        assertEquals(customer.getPhone(), customerResponse.getPhone());
    }
}
