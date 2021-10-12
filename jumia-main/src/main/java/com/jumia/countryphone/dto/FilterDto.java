package com.jumia.countryphone.dto;

import com.jumia.countryphone.enums.Country;
import com.jumia.countryphone.enums.PhoneStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FilterDto {

    private Country country;
    private PhoneStatus status;
}
