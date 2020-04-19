package com.udacity.jdnd.course3.critter.user.dao;

import com.udacity.jdnd.course3.critter.user.entity.Employee;
import org.springframework.data.repository.CrudRepository;


public interface EmployeeRepository extends CrudRepository<Employee, Long>,EmployeeDao {
}
