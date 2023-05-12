package com.example.Great_Indian_Bazaar.repository;

import com.example.Great_Indian_Bazaar.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Customer findByContact(String contact);
}
