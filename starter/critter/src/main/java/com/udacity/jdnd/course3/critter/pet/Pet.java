package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.common.BaseEntity;
import com.udacity.jdnd.course3.critter.schedule.Schedule;
import com.udacity.jdnd.course3.critter.user.entity.Customer;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "pet")
public class Pet extends BaseEntity {

    @Column
    private String name;

    @Column
    private PetType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private Customer ownerId;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "schedule_id", nullable = true)
    private Schedule schedule;

}
