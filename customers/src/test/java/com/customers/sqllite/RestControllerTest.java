package com.customers.sqllite;

import com.customers.sqllite.controllers.CustomerController;
import com.customers.sqllite.dto.CustomerDto;
import com.customers.sqllite.services.FacadeService;
import com.customers.sqllite.util.Utils;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
public class RestControllerTest {

    private ServletContext servletContext;

    private Map<String, String> customers;

    private List<CustomerDto> mockCustomers;

    @MockBean
    private FacadeService facadeService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Tag("Bean")
    @DisplayName("check Controller Bean Type")
    void checkControllerBean() {

        servletContext = webApplicationContext.getServletContext();

        Assert.assertNotNull(servletContext);
        Assert.assertTrue(servletContext instanceof MockServletContext);
        Assert.assertNotNull(webApplicationContext.getBean("customerController"));
    }

    @Nested
    class getResponse {
        @Test
        @Tag("getResponse")
        void findAllTest() throws Exception {

            customers = new HashMap<>();

            CustomerDto customer1 = new CustomerDto("osman", "(237) 699209115");
            CustomerDto customer2 = new CustomerDto("ecomm", "(237) 699209115");
            mockCustomers = new ArrayList<>();
            mockCustomers.add(customer1);
            mockCustomers.add(customer2);

            Mockito.when(facadeService.getCustomersResponseList(customers)).thenReturn(mockCustomers);

            mockMvc.perform(get("/api/customers", 0L)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().json(Utils.convertListToJsonString(mockCustomers)));
        }

        @Test
        @Tag("getResponse")
        void findByCountryTest() throws Exception {

            customers = new HashMap<>();
            customers.put("country", "Cameroon");

            CustomerDto customer1 = new CustomerDto("osman", "(237) 699209115");
            mockCustomers = new ArrayList<>();
            mockCustomers.add(customer1);

            Mockito.when(facadeService.getCustomersResponseList(customers)).thenReturn(mockCustomers);

            mockMvc.perform(get("/api/customers?country=Cameroon", 0L)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().json(Utils.convertListToJsonString(mockCustomers)));

        }

        @Test
        @Tag("getResponse")
        void findByStateTest() throws Exception {

            customers = new HashMap<>();
            customers.put("state", "valid");

            CustomerDto customer1 = new CustomerDto("osman", "(237) 699209115");
            mockCustomers = new ArrayList<>();
            mockCustomers.add(customer1);

            Mockito.when(facadeService.getCustomersResponseList(customers)).thenReturn(mockCustomers);

            mockMvc.perform(get("/api/customers?state=valid", 0L)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().json(Utils.convertListToJsonString(mockCustomers)));

        }

        @Test
        @Tag("getResponse")
        void findByStateAndCountry() throws Exception {

            Map<String, String> customers = new HashMap<>();
            customers.put("state", "valid");
            customers.put("country", "Cameroon");

            CustomerDto customer1 = new CustomerDto("osman", "(237) 699209115");
            mockCustomers = new ArrayList<>();
            mockCustomers.add(customer1);

            Mockito.when(facadeService.getCustomersResponseList(customers)).thenReturn(mockCustomers);

            mockMvc.perform(get("/api/customers?state=valid&country=Cameroon", 0L)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().json(Utils.convertListToJsonString(mockCustomers)));

        }

        @Test
        @Tag("getResponse")
        void findByPhoneNumber() throws Exception {

            customers = new HashMap<>();
            customers.put("number", "(237) 699209115");

            CustomerDto customer1 = new CustomerDto("osman", "(237) 699209115");
            mockCustomers = new ArrayList<>();
            mockCustomers.add(customer1);

            Mockito.when(facadeService.getCustomersResponseList(customers)).thenReturn(mockCustomers);

            mockMvc.perform(get("/api/customers?number=(237) 699209115", 0L)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().json(Utils.convertListToJsonString(mockCustomers)));

        }

        @Test
        @Tag("getResponse")
        void findByStateAndPhoneNumber() throws Exception {

            customers = new HashMap<>();
            customers.put("number", "(237) 699209115");
            customers.put("state", "valid");

            CustomerDto customer1 = new CustomerDto("osman", "(237) 699209115");
            mockCustomers = new ArrayList<>();
            mockCustomers.add(customer1);

            Mockito.when(facadeService.getCustomersResponseList(customers)).thenReturn(mockCustomers);

            mockMvc.perform(get("/api/customers?state=valid&number=(237) 699209115", 0L)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().json(Utils.convertListToJsonString(mockCustomers)));

        }

        @Test
        @Tag("getResponse")
        void findByCountryAndPhoneNumber() throws Exception {

            customers = new HashMap<>();
            customers.put("number", "(237) 699209115");
            customers.put("country", "Cameroon");

            CustomerDto customer1 = new CustomerDto("osman", "(237) 699209115");
            mockCustomers = new ArrayList<>();
            mockCustomers.add(customer1);

            Mockito.when(facadeService.getCustomersResponseList(customers)).thenReturn(mockCustomers);

            mockMvc.perform(get("/api/customers?country=Cameroon&number=(237) 699209115", 0L)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().json(Utils.convertListToJsonString(mockCustomers)));

        }

        @Test
        @Tag("getResponse")
        void findByStateAndCountryAndPhoneNumber() throws Exception {

            customers = new HashMap<>();
            customers.put("number", "(237) 699209115");
            customers.put("country", "Cameroon");
            customers.put("state", "valid");

            CustomerDto customer1 = new CustomerDto("osman", "(237) 699209115");
            mockCustomers = new ArrayList<>();
            mockCustomers.add(customer1);

            Mockito.when(facadeService.getCustomersResponseList(customers)).thenReturn(mockCustomers);

            mockMvc.perform(get("/api/customers?state=valid&country=Cameroon&number=(237) 699209115", 0L)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().json(Utils.convertListToJsonString(mockCustomers)));

        }
    }

    @Test
    @Tag("exception")
    void badBaseUrlTest() throws Exception {

        mockMvc.perform(get("/api/customersss", 0L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());

    }



}
