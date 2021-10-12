package com.jumia.countryphone.validators;

import com.jumia.countryphone.enums.Country;
import com.jumia.countryphone.enums.PhoneStatus;

public class PhoneValidator {

    public static PhoneStatus isValid(Country country, String phone) {
        if (country != null && phone != null && phone.matches(country.getPhoneRegex())) {
            return PhoneStatus.VALID;
        }
        return PhoneStatus.INVALID;
    }
}
