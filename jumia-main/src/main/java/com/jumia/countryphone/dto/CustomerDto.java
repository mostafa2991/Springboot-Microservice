package com.jumia.countryphone.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jumia.countryphone.enums.Country;
import com.jumia.countryphone.enums.PhoneStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDto {

    private Long id;
    private String name;
    private String phone;
    private Country country;
    private PhoneStatus phoneStatus;
}
