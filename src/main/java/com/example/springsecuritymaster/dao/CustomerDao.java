package com.example.springsecuritymaster.dao;

import com.example.springsecuritymaster.ds.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerDao extends CrudRepository<Customer,Integer> {
}
