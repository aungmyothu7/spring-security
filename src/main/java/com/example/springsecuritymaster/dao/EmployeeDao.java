package com.example.springsecuritymaster.dao;

import com.example.springsecuritymaster.ds.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeDao extends CrudRepository<Employee,Integer> {
}
