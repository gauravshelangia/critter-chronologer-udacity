package com.udacity.jdnd.course3.critter.schedule.service;

import com.udacity.jdnd.course3.critter.common.ErrorCode;
import com.udacity.jdnd.course3.critter.common.PetException;
import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.dao.PetRepository;
import com.udacity.jdnd.course3.critter.schedule.Schedule;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import com.udacity.jdnd.course3.critter.schedule.dao.ScheduleRepository;
import com.udacity.jdnd.course3.critter.user.dao.EmployeeRepository;
import com.udacity.jdnd.course3.critter.user.entity.Employee;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PetRepository petRepository;

    @Override
    public ScheduleDTO create(ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDTO, schedule);

        if (!CollectionUtils.isEmpty(scheduleDTO.getPetIds())){
            List<Pet> pets = (List<Pet>) petRepository.findAllById(scheduleDTO.getPetIds());
            schedule.setPets(pets);
            for(Pet pet : pets){
                pet.getSchedules().add(schedule);
            }
        }

        if (!CollectionUtils.isEmpty(scheduleDTO.getEmployeeIds())){
            List<Employee> employees = (List<Employee>) employeeRepository.findAllById(scheduleDTO.getEmployeeIds());
            schedule.setEmployees(employees);
            for (Employee employee : employees){
                employee.getSchedules().add(schedule);
            }
        }

        schedule = scheduleRepository.save(schedule);
        ScheduleDTO savedScheduledDTO = new ScheduleDTO();
        BeanUtils.copyProperties(schedule, savedScheduledDTO);
        savedScheduledDTO.setEmployeeIds(schedule.getEmployees().stream()
                .map(employee -> employee.getId()).collect(Collectors.toList()));
        savedScheduledDTO.setPetIds(schedule.getPets().stream().map(pet -> pet.getId()).collect(Collectors.toList()));
        return savedScheduledDTO;
    }

    @Override
    public ScheduleDTO update(ScheduleDTO scheduleDTO) {
        Schedule schedule = scheduleRepository.findByDate(scheduleDTO.getDate());
        if (ObjectUtils.isEmpty(schedule)) {
            throw new PetException(ErrorCode.SCHDULE_NOT_EXISTS, new Object[]{scheduleDTO.getDate()});
        }
        BeanUtils.copyProperties(scheduleDTO, schedule, "id");
        if (!CollectionUtils.isEmpty(scheduleDTO.getPetIds())){
            List<Pet> pets = (List<Pet>) petRepository.findAllById(scheduleDTO.getPetIds());
            schedule.setPets(pets);
        }

        if (!CollectionUtils.isEmpty(scheduleDTO.getEmployeeIds())){
            List<Employee> employees = (List<Employee>) employeeRepository.findAllById(scheduleDTO.getEmployeeIds());
            schedule.setEmployees(employees);
        }
        schedule = scheduleRepository.save(schedule);
        ScheduleDTO savedScheduledDTO = new ScheduleDTO();
        BeanUtils.copyProperties(schedule, savedScheduledDTO);
        savedScheduledDTO.setEmployeeIds(schedule.getEmployees().stream()
                .map(employee -> employee.getId()).collect(Collectors.toList()));
        savedScheduledDTO.setPetIds(schedule.getPets().stream().map(pet -> pet.getId()).collect(Collectors.toList()));
        return savedScheduledDTO;
    }

    @Override
    public ScheduleDTO getById(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElse(null);
        if (ObjectUtils.isEmpty(schedule)) {
            throw new PetException(ErrorCode.SCHDULE_NOT_EXISTS, new Object[]{});
        }
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(schedule, scheduleDTO);
        scheduleDTO.setPetIds(schedule.getPets().stream().map(pet -> pet.getId()).collect(Collectors.toList()));
        scheduleDTO.setEmployeeIds(schedule.getEmployees().stream().map(employee -> employee.getId()).collect(Collectors.toList()));
        return scheduleDTO;
    }

    @Override
    public List<ScheduleDTO> getAll() {
        List<Schedule> schedules = (List<Schedule>) scheduleRepository.findAll();
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
        for (Schedule schedule : schedules){
            ScheduleDTO scheduleDTO = new ScheduleDTO();
            BeanUtils.copyProperties(schedule, scheduleDTO);
            scheduleDTO.setPetIds(schedule.getPets().stream().map(pet -> pet.getId()).collect(Collectors.toList()));
            scheduleDTO.setEmployeeIds(schedule.getEmployees().stream().map(employee -> employee.getId()).collect(Collectors.toList()));
            scheduleDTOS.add(scheduleDTO);
        }
        return scheduleDTOS;
    }

    @Override
    public List<ScheduleDTO> getAllByPetId(Long petId) {
        List<Schedule> schedules = scheduleRepository.findAllByPetsId(petId);
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
        for (Schedule schedule : schedules){
            ScheduleDTO scheduleDTO = new ScheduleDTO();
            BeanUtils.copyProperties(schedule, scheduleDTO);
            scheduleDTO.setPetIds(schedule.getPets().stream().map(pet -> pet.getId()).collect(Collectors.toList()));
            scheduleDTO.setEmployeeIds(schedule.getEmployees().stream().map(employee -> employee.getId()).collect(Collectors.toList()));
            scheduleDTOS.add(scheduleDTO);
        }
        return scheduleDTOS;
    }

    @Override
    public List<ScheduleDTO> getAllByCustomerId(Long customerId) {

        List<Schedule> schedules = scheduleRepository.findAllByPetsOwnerId(customerId);
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
        for (Schedule schedule : schedules){
            ScheduleDTO scheduleDTO = new ScheduleDTO();
            BeanUtils.copyProperties(schedule, scheduleDTO);
            scheduleDTO.setPetIds(schedule.getPets().stream().map(pet -> pet.getId()).collect(Collectors.toList()));
            scheduleDTO.setEmployeeIds(schedule.getEmployees().stream().map(employee -> employee.getId()).collect(Collectors.toList()));
            scheduleDTOS.add(scheduleDTO);
        }
        return scheduleDTOS;
    }

    @Override
    public List<ScheduleDTO> getAllByEmployeeId(Long employeeId) {
        List<Schedule> schedules = scheduleRepository.findAllByEmployeesId(employeeId);
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
        for (Schedule schedule : schedules){
            ScheduleDTO scheduleDTO = new ScheduleDTO();
            BeanUtils.copyProperties(schedule, scheduleDTO);
            scheduleDTO.setPetIds(schedule.getPets().stream().map(pet -> pet.getId()).collect(Collectors.toList()));
            scheduleDTO.setEmployeeIds(schedule.getEmployees().stream().map(employee -> employee.getId()).collect(Collectors.toList()));
            scheduleDTOS.add(scheduleDTO);
        }
        return scheduleDTOS;    }
}
