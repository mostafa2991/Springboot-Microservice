package com.customers.sqllite;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.customers.sqllite.services.servicesImpl.CustomerServiceImpl;
import com.customers.sqllite.exceptions.NotFoundException;
import com.customers.sqllite.repo.CustomerRepo;
import com.customers.sqllite.util.Utils;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import com.customers.sqllite.entities.Customer;
import static org.junit.jupiter.api.Assertions.assertAll;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest

class CustomersServiceTest {

	private List<Customer> mockCustomers;

	@Mock
	private CustomerRepo mockRepo;

	@InjectMocks
	private CustomerServiceImpl customerServiceImpl;

	@Mock
	private Utils mockUtils;

	@BeforeEach
	@DisplayName("Inject Mocks Before EveryMethod")
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Nested
	class Search {
		@Test
		@Tag("search")
		@DisplayName("Find All")
		public void findAllTest() {

			Customer customer1 = new Customer(1, "osman", "(237) 699209115");
			Customer customer2 = new Customer(2, "e-comm", "(237) 699209114");
			mockCustomers = new ArrayList<>();
			mockCustomers.add(customer1);
			mockCustomers.add(customer2);

			Mockito.when(mockRepo.findAll()).thenReturn(Stream.of(customer1, customer2).collect(Collectors.toList()));
			Assertions.assertIterableEquals(mockCustomers, customerServiceImpl.findAll(), "Should Find All Users");

		}

		@Test
		@Tag("search")
		@DisplayName("Find By Phone Number")
		public void findByPhoneNumber() {

			Customer customer1 = new Customer(1, "osman", "(237) 699209115");
			mockCustomers = new ArrayList<>();
			mockCustomers.add(customer1);

			Mockito.when(mockRepo.findByPhoneContaining("(212) 6007989253")).thenReturn(mockCustomers);
			Assertions.assertIterableEquals(mockCustomers, customerServiceImpl.findByNumber("(212) 6007989253"), "Should Find By Phone Number");

		}

		@Test
		@Tag("search")
		@DisplayName("Find By State")
		public void findByState() {

			Customer validCustomer = new Customer();
			validCustomer.setPhone("(237) 699209115");

			Customer invalidCustomer = new Customer();
			invalidCustomer.setPhone("(237) 6209115");

			Customer invalidCustomer2 = new Customer();
			invalidCustomer2.setPhone("(237) 6209115");

			List<Customer> allMockCustomers = new ArrayList<>();
			List<Customer> validMockCustomers = new ArrayList<>();
			List<Customer> invalidMockCustomers = new ArrayList<>();

			allMockCustomers.add(validCustomer);
			allMockCustomers.add(invalidCustomer);
			allMockCustomers.add(invalidCustomer2);

			validMockCustomers.add(validCustomer);

			invalidMockCustomers.add(invalidCustomer);
			invalidMockCustomers.add(invalidCustomer2);

			assertAll(
					() -> Assertions.assertIterableEquals(validMockCustomers, customerServiceImpl.findByState("valid", allMockCustomers), "Should Find By Valid State"),
					() -> Assertions.assertIterableEquals(invalidMockCustomers, customerServiceImpl.findByState("notValid", allMockCustomers), "Should Find By InValid State")
					);

		}

		@Test
		@Tag("search")
		@DisplayName("Find By Country")
		public void findByCountryTest() {

			Customer customer1 = new Customer(1, "osman", "(237) 699209115");
			mockCustomers = new ArrayList<>();
			mockCustomers.add(customer1);

			Mockito.when(mockUtils.getCountryCodeBasedOnCountry("Cameroon")).thenReturn("(237)");
			Mockito.when(mockRepo.findByPhoneContaining("(237)")).thenReturn(mockCustomers);

			Assertions.assertIterableEquals(mockCustomers, customerServiceImpl.findByCountry("Cameroon"), "Should Find By Country");

		}

	}

	@Test
	@Tag("exception")
	@DisplayName("check Not Found State")
	public void notFoundStateTest() {

		List<Customer> customers = new ArrayList<>();

		NotFoundException notFoundException =
				Assertions.assertThrows(
						NotFoundException.class,
						() -> customerServiceImpl.findByState("validd", customers));

		Assertions.assertEquals("this state is not available", notFoundException.getMessage(), "Should Throw Exception Because This State Is UnAvailable");

	}

}
