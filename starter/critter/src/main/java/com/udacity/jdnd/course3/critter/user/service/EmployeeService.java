package com.udacity.jdnd.course3.critter.user.service;

import com.udacity.jdnd.course3.critter.user.EmployeeDTO;

public interface EmployeeService {

    /**
     * Create new Employee
     * @param employeeDTO
     * @return
     */
    EmployeeDTO create(EmployeeDTO employeeDTO);

    /**
     * Update an existing Employee
     * @param employeeDTO
     * @return
     */
    EmployeeDTO update(EmployeeDTO employeeDTO);

    /**
     * Get Employee by Id
     * @param employeeDTO
     * @return
     */
    EmployeeDTO getById(EmployeeDTO employeeDTO);
}
