package com.db.dao;

import com.db.model.Schedule;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

public interface ScheduleDao extends CrudRepository<Schedule, Integer> {
    List<Schedule> findByDateBetween(Date startDate, Date endDate);

    List<Schedule> findByDateGreaterThanEqualAndDateLessThanEqual(@Temporal(TemporalType.DATE) Date startDate, @Temporal(TemporalType.DATE) Date endDate);
}
