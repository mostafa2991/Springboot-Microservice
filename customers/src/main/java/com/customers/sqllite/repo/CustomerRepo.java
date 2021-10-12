package com.customers.sqllite.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.customers.sqllite.entities.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {

	List<Customer> findByPhoneContaining(String code);


}
