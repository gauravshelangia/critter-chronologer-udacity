package com.udacity.jdnd.course3.critter.user.service;

import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeRequestDTO;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

public interface EmployeeService {

    /**
     * Create new Employee
     * @param employeeDTO
     * @return
     */
    EmployeeDTO save(EmployeeDTO employeeDTO);

    /**
     * Update an existing Employee
     * @param employeeDTO
     * @return
     */
    EmployeeDTO update(EmployeeDTO employeeDTO);

    /**
     * Get Employee by Id
     * @param employeeId
     * @return
     */
    EmployeeDTO getById(Long employeeId);

    /**
     * Add employee availability
     * @param daysAvailable
     * @param employeeId
     */
    void setAvailability(Set<DayOfWeek> daysAvailable, Long employeeId);

    /**
     * Get all employee for a given service and time
     * @param employeeRequestDTO
     * @return
     */
    List<EmployeeDTO> getAllByServiceAndTime(EmployeeRequestDTO employeeRequestDTO);
}
