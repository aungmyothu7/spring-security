package com.example.springsecuritymaster.dao;

import com.example.springsecuritymaster.ds.Department;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentDao extends CrudRepository<Department,Integer> {
}
