package com.jumia.countryphone.controller;

import com.jumia.countryphone.dto.CustomerDto;
import com.jumia.countryphone.dto.FilterDto;
import com.jumia.countryphone.enums.Country;
import com.jumia.countryphone.enums.PhoneStatus;
import com.jumia.countryphone.service.CustomerService;
import com.jumia.countryphone.utils.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/")
    public String getCustomers(Model model, Country country, PhoneStatus state, Integer page, Integer size) {

        int currentPage = Optional.ofNullable(page).orElse(1);
        int pageSize = Optional.ofNullable(size).orElse(5);

        Page<CustomerDto> customersPage = customerService
                .getCustomers(country, state, PageRequest.of(currentPage - 1, pageSize));

        final List<Integer> pageNumbers = PaginationUtils.buildPageNumbers(customersPage);

        model.addAttribute("filter", new FilterDto(country, state));
        model.addAttribute("customersPage", customersPage);
        model.addAttribute("pageNumbers", pageNumbers);

        return "home";
    }
}
