package com.udacity.jdnd.course3.critter.user.entity;

import com.udacity.jdnd.course3.critter.common.BaseEntity;
import com.udacity.jdnd.course3.critter.pet.Pet;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "customer")
public class Customer extends BaseEntity {

    @Column
    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column
    private String notes;

    @OneToMany(fetch = FetchType.EAGER,
            mappedBy = "owner")
    private List<Pet> pets = new ArrayList<>();
}
