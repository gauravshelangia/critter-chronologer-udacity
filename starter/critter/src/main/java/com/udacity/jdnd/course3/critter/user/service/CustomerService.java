package com.udacity.jdnd.course3.critter.user.service;

import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CustomerService {

    /**
     * Create new customer
     * @param customerDTO
     * @return
     */
    CustomerDTO create(CustomerDTO customerDTO);

    /**
     * Update an existing customer
     * @param customerDTO
     * @return
     */
    CustomerDTO update(CustomerDTO customerDTO);

    /**
     * Get customer details by Id
     * @param customerId
     * @return
     */
    CustomerDTO getById(Long customerId);

    List<CustomerDTO> getAllCustomers();

    CustomerDTO getByPetId(Long petId);

}
