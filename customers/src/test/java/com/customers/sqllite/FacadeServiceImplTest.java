package com.customers.sqllite;

import com.customers.sqllite.dto.CustomerDto;
import com.customers.sqllite.entities.Customer;
import com.customers.sqllite.exceptions.BadUrlException;
import com.customers.sqllite.services.servicesImpl.CustomerServiceImpl;
import com.customers.sqllite.services.servicesImpl.FacadeServiceImpl;
import com.customers.sqllite.util.Utils;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FacadeServiceImplTest {

    private List<Customer> mockCustomers;

    private Map<String, String> customers;

    @Mock
    private Utils mockUtils;

    @Mock
    private CustomerServiceImpl mockCustomerServiceImpl;

    @InjectMocks
    private FacadeServiceImpl facadeServiceImpl;

    @BeforeEach
    @DisplayName("Inject Mocks Before EveryMethod")
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Nested
    class Filteration {

        @Test
        @Tag("filteration")
        @DisplayName("Search By Phone Number Only)")

        public void filterByPhoneNumberOnly() {

            customers = new HashMap<>();
            customers.put("number", "(237) 699209115");

            Customer customer1 = new Customer(1, "osman", "(237) 699209115");
            mockCustomers = new ArrayList<>();
            mockCustomers.add(customer1);

            Mockito.when(mockCustomerServiceImpl.findByNumber("(237) 699209115")).thenReturn(mockCustomers);
            Assertions.assertIterableEquals(mockCustomers, facadeServiceImpl.getCustomersList(customers), "Should Search By Phone Number");

        }

        @Test
        @Tag("filteration")
        @DisplayName("Search By Phone Number And State")
        public void filterByPhoneNumberAndState() {

            customers = new HashMap<>();
            customers.put("state", "valid");
            customers.put("number", "(237) 699209115");

            Customer customer1 = new Customer(1, "osman", "(237) 699209115");
            mockCustomers = new ArrayList<>();
            mockCustomers.add(customer1);

            Mockito.when(mockCustomerServiceImpl.findByNumber("(237) 699209115")).thenReturn(mockCustomers);
            Assertions.assertIterableEquals(mockCustomers, facadeServiceImpl.getCustomersList(customers), "Should Search By Phone Number");

        }

        @Test
        @Tag("filteration")
        @DisplayName("Search By Phone Number And Country")

        public void filterByPhoneNumberAndCountry() {

            customers = new HashMap<>();
            customers.put("country", "Cameroon");
            customers.put("number", "(237) 699209115");

            Customer customer1 = new Customer(1, "osman", "(237) 699209115");
            mockCustomers = new ArrayList<>();
            mockCustomers.add(customer1);

            Mockito.when(mockCustomerServiceImpl.findByNumber("(237) 699209115")).thenReturn(mockCustomers);
            Assertions.assertIterableEquals(mockCustomers, facadeServiceImpl.getCustomersList(customers), "Should Search By Phone Number");

        }

        @Test
        @Tag("filteration")
        @DisplayName("Search By Phone Number, State And Country)")

        public void filterByPhoneNumberAndCountryAndState() {

            customers = new HashMap<>();
            customers.put("country", "Cameroon");
            customers.put("number", "(237) 699209115");
            customers.put("state", "valid");

            Customer customer1 = new Customer(1, "osman", "(237) 699209115");
            mockCustomers = new ArrayList<>();
            mockCustomers.add(customer1);

            Mockito.when(mockCustomerServiceImpl.findByNumber("(237) 699209115")).thenReturn(mockCustomers);
            Assertions.assertIterableEquals(mockCustomers, facadeServiceImpl.getCustomersList(customers), "Should Search By Phone Number");

        }


        @Test
        @Tag("filteration")
        @DisplayName("Search By Country Only)")

        public void filterByCountryOnly() {

            customers = new HashMap<>();
            customers.put("country", "Cameroon");

            Customer customer1 = new Customer(1, "osman", "(237) 699209115");
            mockCustomers = new ArrayList<>();
            mockCustomers.add(customer1);

            Mockito.when(mockCustomerServiceImpl.findByCountry("Cameroon")).thenReturn(mockCustomers);
            Assertions.assertIterableEquals(mockCustomers, facadeServiceImpl.getCustomersList(customers), "Should Search By Country");

        }

        @Test
        @Tag("filteration")
        @DisplayName("Search By State Only)")

        public void filterByStateOnly() {

            customers = new HashMap<>();
            customers.put("state", "valid");

            Customer customer1 = new Customer(1, "osman", "(237) 699209115");
            Customer customer2 = new Customer(2, "e-comm", "(237) 11111");
            mockCustomers = new ArrayList<>();
            mockCustomers.add(customer1);

            Mockito.when(mockCustomerServiceImpl.findAll()).thenReturn(Stream.of(customer1, customer2).collect(Collectors.toList()));
            Mockito.when(mockCustomerServiceImpl.findByState("valid", mockCustomerServiceImpl.findAll())).thenReturn(mockCustomers);
            Assertions.assertIterableEquals(mockCustomers, facadeServiceImpl.getCustomersList(customers), "Should Search By State");

        }

        @Test
        @Tag("filteration")
        @DisplayName("Search By State And Country)")

        public void filterByStateAndCountry() {

            customers = new HashMap<>();
            customers.put("state", "valid");

            Customer customer1 = new Customer(1, "osman", "(237) 699209115");
            Customer customer2 = new Customer(2, "e-comm", "(237) 11111");
            mockCustomers = new ArrayList<>();
            mockCustomers.add(customer1);
            mockCustomers.add(customer2);

            Mockito.when(mockCustomerServiceImpl.findByState("valid", mockCustomerServiceImpl.findByCountry("Cameroon"))).thenReturn(mockCustomers);
            Assertions.assertIterableEquals(mockCustomers, facadeServiceImpl.getCustomersList(customers), "Should Search By Country And State");

        }
    }

    @Test
    @Tag("exception")
    @DisplayName("check Bad URL")
    public void badUrlTest() {

        customers = new HashMap<>();
        customers.put("try", "Cameroon");
        BadUrlException badUrlException=
                Assertions.assertThrows(
                        BadUrlException.class,
                        () -> facadeServiceImpl.getCustomersResponseList(customers));

        Assertions.assertEquals("Bad Url", badUrlException.getMessage(), "Should Get Bad Url Exception Because Of InValid Key");

    }

    @Test
    @Tag("exception")
    @DisplayName("check empty value")
    public void badValueTest() {

        customers = new HashMap<>();
        customers.put("country", "");
        BadUrlException badUrlException=
                Assertions.assertThrows(
                        BadUrlException.class,
                        () -> facadeServiceImpl.getCustomersResponseList(customers));

        Assertions.assertEquals("Bad Url", badUrlException.getMessage(), "Should Get Bad Url Exception Because Of Empty Value");

    }

    @Test
    @Tag("helperMethods")
    @DisplayName("Check Entity To Dto Convert)")

    public void getCustomersResponseListTest() {

        customers = new HashMap<>();
        customers.put("number", "(237) 699209115");

        Customer customer1 = new Customer(1, "osman", "(237) 699209115");
        mockCustomers = new ArrayList<>();
        mockCustomers.add(customer1);

        CustomerDto customerDto1 = new CustomerDto("osman", "(237) 699209115");
        List<CustomerDto> mockDtoCustomers = new ArrayList<>();
        mockDtoCustomers.add(customerDto1);

        Mockito.when(mockUtils.entityListToDto(mockCustomers, CustomerDto.class)).thenReturn(mockDtoCustomers);
        Mockito.when(mockCustomerServiceImpl.findByNumber("(237) 699209115")).thenReturn(mockCustomers);
        Assertions.assertIterableEquals(mockDtoCustomers, facadeServiceImpl.getCustomersResponseList(customers), "Should Convert Entity To Dto");

    }


}
