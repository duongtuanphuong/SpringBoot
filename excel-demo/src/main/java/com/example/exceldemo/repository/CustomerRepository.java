package com.example.exceldemo.repository;

import com.example.exceldemo.model.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
}
