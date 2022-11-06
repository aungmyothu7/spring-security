package com.example.springsecuritymaster.dao;

import com.example.springsecuritymaster.ds.Customers;
import org.springframework.data.repository.CrudRepository;

public interface CustomerDao extends CrudRepository<Customers,Integer> {
}
