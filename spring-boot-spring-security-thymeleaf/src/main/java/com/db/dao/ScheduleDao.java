package com.db.dao;

import com.db.model.Schedule;
import com.db.model.Seat;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;
import java.util.Set;

public interface ScheduleDao extends CrudRepository<Schedule, Integer> {
    List<Schedule> findByDateBetween(Date startDate, Date endDate);

    List<Schedule> findByDateGreaterThanEqualAndDateLessThanEqual(@Temporal(TemporalType.DATE) Date startDate, @Temporal(TemporalType.DATE) Date endDate);

    List<Schedule> findAll();
}
