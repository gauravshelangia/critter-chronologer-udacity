package com.udacity.jdnd.course3.critter.user.dao;

import com.udacity.jdnd.course3.critter.user.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.user.entity.Employee;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Employee> getAllbyAvailability(EmployeeRequestDTO employeeRequestDTO) {
        Query availableQuery = manager
                .createNativeQuery("SELECT E.* FROM EMPLOYEE E JOIN " +
                        "EMPLOYEE_SKILLS ES ON E.ID = ES.EMPLOYEE_ID JOIN " +
                        "EMPLOYEE_DAYS_AVAILABLE EDA ON EDA.EMPLOYEE_ID = E.ID " +
                        "WHERE ES.SKILLS IN (:skills ) AND EDA.DAYS_AVAILABLE = :dayOfWeek " +
                        "GROUP BY E.ID HAVING COUNT(DISTINCT ES.SKILLS)>= :count", Employee.class);
        availableQuery.setParameter("dayOfWeek", employeeRequestDTO.getDate().getDayOfWeek().ordinal());
        availableQuery.setParameter("count", employeeRequestDTO.getSkills().size());
        List<String> skillList = employeeRequestDTO.getSkills().stream().map(employeeSkill ->
                employeeSkill.name()).collect(Collectors.toList());
        availableQuery.setParameter("skills", skillList);

        return availableQuery.getResultList();
    }
}
