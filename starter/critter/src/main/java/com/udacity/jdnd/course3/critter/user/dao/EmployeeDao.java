package com.udacity.jdnd.course3.critter.user.dao;

import com.udacity.jdnd.course3.critter.user.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.user.entity.Employee;

import java.util.List;

public interface EmployeeDao {

    List<Employee> getAllbyAvailability(EmployeeRequestDTO employeeRequestDTO);
}
