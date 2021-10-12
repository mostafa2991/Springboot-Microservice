package com.dailycodebuffer.department.controller;

import com.dailycodebuffer.department.entity.Department;
import com.dailycodebuffer.department.service.DepartmentService;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
//import org.springframework.hateoas.server.mvc.ControllerLinkBuilder;
//import org.springframework.core.hateoas.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/departments")
@Slf4j
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/")
    public Department saveDepartment(@Valid @RequestBody Department department) {
        log.info("Inside saveDepartment method of DepartmentController");
        return  departmentService.saveDepartment(department);
    }

    @GetMapping(value = "/{id}" , produces = { "application/hal+json" })
    public Department findDepartmentById(@PathVariable("id") Long departmentId) {
        log.info("Inside findDepartmentById method of DepartmentController");
        Department department = departmentService.findDepartmentById(departmentId);
		return department;
    }
//    @GetMapping(value = "/{id}" , produces = { "application/hal+json" })
//    public CollectionModel<Department> findDepartmentById(@PathVariable("id") Long departmentId) {
//    	log.info("Inside findDepartmentById method of DepartmentController");
//    	Department department = departmentService.findDepartmentById(departmentId);
//    	//Self link
//    	List<Department> departments = departmentService.findAllDepartment();
//    	Link link = new Link("http://localhost:8080/spring-security-rest/api/customers/10A");
////        Link link = linkTo(methodOn(DepartmentController.class).getOrdersForCustomer(customerId)).withSelfRel();
//
//    	CollectionModel<Department> result = CollectionModel.of(departments, link);
//    	
//    	return result;
//    }

}
