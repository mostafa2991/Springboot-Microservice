package com.jumia.countryphone.mapper;

import com.jumia.countryphone.dto.CustomerDto;
import com.jumia.countryphone.entities.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDto entityToDto(Customer customer);
}
