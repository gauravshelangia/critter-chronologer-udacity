package com.udacity.jdnd.course3.critter.user.service.impl;

import com.udacity.jdnd.course3.critter.common.ErrorCode;
import com.udacity.jdnd.course3.critter.common.PetException;
import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.dao.EmployeeRepository;
import com.udacity.jdnd.course3.critter.user.entity.Employee;
import com.udacity.jdnd.course3.critter.user.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.DayOfWeek;
import java.util.Set;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDTO save(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
        employee = employeeRepository.save(employee);
        BeanUtils.copyProperties(employee, employeeDTO);
        return employeeDTO;
    }

    @Override
    public EmployeeDTO update(EmployeeDTO employeeDTO) {
        Employee employee = employeeRepository.findById(employeeDTO.getId()).orElse(null);
        if (ObjectUtils.isEmpty(employee)){
            throw new PetException(ErrorCode.EMPLOYEE_NOT_EXISTS, new Object[]{employeeDTO.getId()});
        }
        BeanUtils.copyProperties(employeeDTO, employee);
        employee = employeeRepository.save(employee);
        BeanUtils.copyProperties(employee, employeeDTO);
        return employeeDTO;
    }

    @Override
    public EmployeeDTO getById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        if (ObjectUtils.isEmpty(employee)){
            throw new PetException(ErrorCode.EMPLOYEE_NOT_EXISTS, new Object[]{employeeId});
        }
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(employee, employeeDTO);
        return employeeDTO;
    }

    @Override
    public void setAvailability(Set<DayOfWeek> daysAvailable, Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        if (ObjectUtils.isEmpty(employee)){
            throw new PetException(ErrorCode.EMPLOYEE_NOT_EXISTS, new Object[]{employeeId});
        }
        employee.setDaysAvailable(daysAvailable);
        employeeRepository.save(employee);
    }


}
