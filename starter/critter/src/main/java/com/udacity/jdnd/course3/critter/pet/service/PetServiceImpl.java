package com.udacity.jdnd.course3.critter.pet.service;

import com.udacity.jdnd.course3.critter.common.ErrorCode;
import com.udacity.jdnd.course3.critter.common.PetException;
import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.pet.dao.PetRepository;
import com.udacity.jdnd.course3.critter.user.dao.CustomerRepository;
import com.udacity.jdnd.course3.critter.user.entity.Customer;
import com.udacity.jdnd.course3.critter.user.service.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

import static com.udacity.jdnd.course3.critter.common.ErrorCode.CUSTOMER_NOT_EXISTS;

@Service
public class PetServiceImpl implements PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public PetDTO save(PetDTO petDTO) {
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO, pet);
        Customer customer = customerRepository.findById(petDTO.getOwnerId()).orElse(null);
        if (ObjectUtils.isEmpty(customer)){
            throw new PetException(CUSTOMER_NOT_EXISTS, new Object[]{customer});
        }
        pet.setOwner(customer);
        pet = petRepository.save(pet);
        customer.getPets().add(pet);
        PetDTO savedPetDTO = new PetDTO();
        BeanUtils.copyProperties(pet, savedPetDTO);
        return savedPetDTO;
    }

    @Override
    public PetDTO update(PetDTO petDTO) {
        Pet pet = petRepository.findById(petDTO.getId()).orElse(null);
        if (ObjectUtils.isEmpty(pet)){
            throw new PetException(ErrorCode.PET_NOT_EXISTS, new Object[]{petDTO.getId()});
        }
        BeanUtils.copyProperties(petDTO, pet);
        Customer customer = customerRepository.findById(petDTO.getOwnerId()).orElse(null);
        if (ObjectUtils.isEmpty(customer)){
            throw new PetException(CUSTOMER_NOT_EXISTS, new Object[]{customer});
        }
        pet.setOwner(customer);
        pet = petRepository.save(pet);
        customer.getPets().add(pet);
        PetDTO savedPetDTO = new PetDTO();
        BeanUtils.copyProperties(pet, savedPetDTO);
        return savedPetDTO;
    }

    @Override
    public PetDTO getById(Long petId) {
        Pet pet = petRepository.findById(petId).orElse(null);
        if (ObjectUtils.isEmpty(pet)){
            throw new PetException(ErrorCode.PET_NOT_EXISTS, new Object[]{petId});
        }
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet, petDTO);
        petDTO.setOwnerId(pet.getOwner().getId());
        return petDTO;
    }

    @Override
    public List<PetDTO> getAll() {
        List<Pet> pets = (List<Pet>) petRepository.findAll();
        List<PetDTO> petDTOS = new ArrayList<>();
        for (Pet pet : pets){
            PetDTO petDTO = new PetDTO();
            BeanUtils.copyProperties(pet, petDTO);
            petDTO.setOwnerId(pet.getOwner().getId());
            petDTOS.add(petDTO);
        }
        return petDTOS;
    }

    @Override
    public List<PetDTO> getAllByOwnerId(Long ownerId) {
        List<Pet> pets = petRepository.findAllByOwnerId(ownerId);
        List<PetDTO> petDTOS = new ArrayList<>();
        for (Pet pet : pets){
            PetDTO petDTO = new PetDTO();
            BeanUtils.copyProperties(pet, petDTO);
            petDTO.setOwnerId(pet.getOwner().getId());
            petDTOS.add(petDTO);
        }
        return petDTOS;
    }
}
