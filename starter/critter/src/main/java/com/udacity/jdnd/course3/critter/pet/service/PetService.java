package com.udacity.jdnd.course3.critter.pet.service;

import com.udacity.jdnd.course3.critter.pet.PetDTO;

import java.util.List;

public interface PetService {

    /**
     * Create new Pet
     * @param petDTO
     * @return
     */
    PetDTO save(PetDTO petDTO);

    /**
     * Update an existing Pet
     * @param petDTO
     * @return
     */
    PetDTO update(PetDTO petDTO);

    /**
     * Get a pet details by Id
     * @param petId
     * @return
     */
    PetDTO getById(Long petId);


    /**
     * Get All pets
     * @return
     */
    List<PetDTO> getAll();

    /**
     * GetAll pet of a customer
     * @param ownerId
     * @return
     */
    List<PetDTO> getAllByOwnerId (Long ownerId);
}
