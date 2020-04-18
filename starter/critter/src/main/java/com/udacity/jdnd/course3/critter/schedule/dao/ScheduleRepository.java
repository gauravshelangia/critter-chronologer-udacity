package com.udacity.jdnd.course3.critter.schedule.dao;

import com.udacity.jdnd.course3.critter.schedule.Schedule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ScheduleRepository extends CrudRepository<Schedule, Long> {

    Schedule findByDate(LocalDate date);

    List<Schedule> findAllByEmployeesId(Long employeeId);

    List<Schedule> findAllByPetsId(Long petId);

    List<Schedule> findAllByPetsIdIn(List<Long> petIds);

    List<Schedule> findAllByPetsOwnerId(Long customerId);
}
