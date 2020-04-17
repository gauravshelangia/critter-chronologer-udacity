package com.udacity.jdnd.course3.critter.user.service.impl;

import com.udacity.jdnd.course3.critter.common.PetException;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.dao.CustomerRepository;
import com.udacity.jdnd.course3.critter.user.entity.Customer;
import com.udacity.jdnd.course3.critter.user.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.udacity.jdnd.course3.critter.common.ErrorCode.CUSTOMER_NOT_EXISTS;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);


    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CustomerDTO create(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        customer = customerRepository.save(customer);
        BeanUtils.copyProperties(customer, customerDTO);
        return customerDTO;
    }

    @Override
    public CustomerDTO update(CustomerDTO customerDTO) {
        Optional<Customer> customerOptional = customerRepository.findById(customerDTO.getId());
        if (!customerOptional.isPresent()){
            throw new PetException(CUSTOMER_NOT_EXISTS, new Object[]{customerDTO.getId()});
        }
        Customer customer = customerOptional.get();
        BeanUtils.copyProperties(customerDTO, customer);
        customer = customerRepository.save(customer);
        BeanUtils.copyProperties(customer, customerDTO);
        return customerDTO;
    }

    @Override
    public CustomerDTO getById(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (ObjectUtils.isEmpty(customer)){
            throw new PetException(CUSTOMER_NOT_EXISTS, new Object[]{customer});
        }
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);
        return customerDTO;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = (List<Customer>) customerRepository.findAll();
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        if (!CollectionUtils.isEmpty(customers)){
            for (Customer customer : customers){
                CustomerDTO customerDTO = new CustomerDTO();
                BeanUtils.copyProperties(customer, customerDTO);
                customerDTOS.add(customerDTO);
            }
        }
        return customerDTOS;
    }

    @Override
    public CustomerDTO getByPetId(Long petId) {
        Customer customer = customerRepository.findByPetsId(petId);
        if (ObjectUtils.isEmpty(customer)){
            throw new PetException(CUSTOMER_NOT_EXISTS, new Object[]{customer});
        }
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);
        return customerDTO;
    }

}
