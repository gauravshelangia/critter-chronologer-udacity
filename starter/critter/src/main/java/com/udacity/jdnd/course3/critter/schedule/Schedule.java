package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.common.BaseEntity;
import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import com.udacity.jdnd.course3.critter.user.entity.Employee;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "schedule")
public class Schedule extends BaseEntity {

    @Column
    @OneToMany(mappedBy = "schedule")
    private List<Employee> employees;

    @OneToMany(mappedBy = "schedule")
    private List<Pet> pets;

    private LocalDate date;
    private Set<EmployeeSkill> activities;
}
