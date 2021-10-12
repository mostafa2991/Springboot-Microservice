package com.customers.sqllite;

import com.customers.sqllite.dto.CustomerDto;
import com.customers.sqllite.util.Utils;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.assertEquals;

public class UtilsTest {

    private boolean check ;

    private List<CustomerDto> mockDtoCustomers;

    @Mock
    Utils mockUtils;

    @BeforeEach
    @DisplayName("Inject Mocks Before EveryMethod")
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @Tag("validation")
    @DisplayName("Phone Numbers Matcher Test")
    public void validateNumberTest() {

        check = true;

        String[] validPhoneNumbers  = {"(212) 6007989253","(258) 84330678235",
                "(212) 633963130","(237) 699209115","(237) 6622284920","(237) 677046616"};

        for ( String phoneNumber : validPhoneNumbers ) {
            check = Utils.validateNumber(phoneNumber);
            if(check == false){
                assertEquals("Un Valid Number Passed The Validation", false ,check);
            }
            else{
                assertEquals("A Valid Number DidNot Pass The Validation", true, check);
            }

        }
    }


    @Test
    @Tag("validation")
    @DisplayName("check Keys Test")
    public void checkKeysTest() {

        Map<String, String> validCustomers = new HashMap<>();
        Map<String, String> invaildCustomers = new HashMap<>();

        validCustomers.put("number", "(237) 699209115");
        invaildCustomers.put("numberr", "(237) 699209115");

        check = Utils.checkKeys(validCustomers);
        assertEquals("Valid State Didnot Pass", true, check);

        check = Utils.checkKeys(invaildCustomers);
        assertEquals("UN Valid State Passed", false, check);

    }

    @Test
    @Tag("helperMethods")
    @DisplayName("check json convert")
    public void convertObjectToJsonStringTest() {

        CustomerDto customerDto1 = new CustomerDto("osman", "(237) 699209115");
        mockDtoCustomers = new ArrayList<>();
        mockDtoCustomers.add(customerDto1);

        String mockDtoCustomersJson = Utils.convertListToJsonString(mockDtoCustomers);

        assertEquals("It Should Convert List To Json Format", mockDtoCustomersJson, "[{\"name\":\"osman\",\"phone\":\"(237) 699209115\"}]");

    }

}
