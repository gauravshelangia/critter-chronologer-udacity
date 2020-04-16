package com.udacity.jdnd.course3.critter.user.entity;

import com.udacity.jdnd.course3.critter.common.BaseEntity;
import com.udacity.jdnd.course3.critter.schedule.Schedule;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import lombok.Data;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.Set;

@Data
@Entity
@Table(name = "employee")
public class Employee extends BaseEntity {

    @Column
    private String name;

    @Column
    private Set<EmployeeSkill> skills;

    @Column
    private Set<DayOfWeek> daysAvailable;

    @Column
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

}
