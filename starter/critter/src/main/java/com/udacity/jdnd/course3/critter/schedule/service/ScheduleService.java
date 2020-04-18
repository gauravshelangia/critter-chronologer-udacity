package com.udacity.jdnd.course3.critter.schedule.service;

import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleService {

    /**
     * Create new Schedule
     * @param scheduleDTO
     * @return
     */
    ScheduleDTO create(ScheduleDTO scheduleDTO);

    /**
     * Updating an existing schedule
     * @param scheduleDTO
     * @return
     */
    ScheduleDTO update(ScheduleDTO scheduleDTO);

    /**
     * Get schedule by id
     * @param scheduleId
     * @return
     */
    ScheduleDTO getById(Long scheduleId);

    /**
     * Get all schedule
     * @return
     */
    List<ScheduleDTO> getAll();

    /**
     * Get all schedule for a pet
     * @param petId
     * @return
     */
    List<ScheduleDTO> getAllByPetId(Long petId);

    /**
     * Get all schedule of a customer
     * @param customerId
     * @return
     */
    List<ScheduleDTO> getAllByCustomerId(Long customerId);

    /**
     * Get all schedule of an employee
     * @param employeeId
     * @return
     */
    List<ScheduleDTO> getAllByEmployeeId(Long employeeId);
}
