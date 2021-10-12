package com.jumia.countryphone.validators;

import com.jumia.countryphone.enums.Country;
import com.jumia.countryphone.enums.PhoneStatus;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PhoneValidatorTest {

    @Test
    void shouldReturnValidPhoneStatus() {

        final PhoneStatus statusPhone1 = PhoneValidator.isValid(Country.MOZAMBIQUE, "(258) 847651504");
        final PhoneStatus statusPhone2 = PhoneValidator.isValid(Country.ETHIOPIA, "(251) 914148181");
        final PhoneStatus statusPhone3 = PhoneValidator.isValid(Country.MOROCCO, "(212) 691933626");
        final PhoneStatus statusPhone4 = PhoneValidator.isValid(Country.CAMEROON, "(237) 673122155");
        final PhoneStatus statusPhone5 = PhoneValidator.isValid(Country.UGANDA, "(256) 714660221");

        assertEquals(PhoneStatus.VALID, statusPhone1);
        assertEquals(PhoneStatus.VALID, statusPhone2);
        assertEquals(PhoneStatus.VALID, statusPhone3);
        assertEquals(PhoneStatus.VALID, statusPhone4);
        assertEquals(PhoneStatus.VALID, statusPhone5);
    }

    @Test
    void shouldReturnInvalidPhoneStatus() {

        final PhoneStatus statusPhone1 = PhoneValidator.isValid(Country.MOZAMBIQUE, "(258) 84330678235");
        final PhoneStatus statusPhone2 = PhoneValidator.isValid(Country.ETHIOPIA, "(251) 9119454961");
        final PhoneStatus statusPhone3 = PhoneValidator.isValid(Country.MOROCCO, "(212) 6007989253");
        final PhoneStatus statusPhone4 = PhoneValidator.isValid(Country.CAMEROON, "(237) 6A0311634");
        final PhoneStatus statusPhone5 = PhoneValidator.isValid(Country.UGANDA, "(256) 7734127498");

        assertEquals(PhoneStatus.INVALID, statusPhone1);
        assertEquals(PhoneStatus.INVALID, statusPhone2);
        assertEquals(PhoneStatus.INVALID, statusPhone3);
        assertEquals(PhoneStatus.INVALID, statusPhone4);
        assertEquals(PhoneStatus.INVALID, statusPhone5);
    }
}
