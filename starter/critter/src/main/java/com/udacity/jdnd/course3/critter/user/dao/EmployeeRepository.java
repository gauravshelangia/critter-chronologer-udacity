package com.udacity.jdnd.course3.critter.user.dao;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import com.udacity.jdnd.course3.critter.user.entity.Employee;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    Set<Employee> findAllBySkillsInAndSchedulesDate(Set<EmployeeSkill> skills, LocalDate date);
}
