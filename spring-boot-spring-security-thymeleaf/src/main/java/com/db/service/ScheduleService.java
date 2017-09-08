package com.db.service;

import com.db.dao.ScheduleDao;
import com.db.dao.SeatDao;
import com.db.data.BookSeat;
import com.db.data.SchedulePeriod;
import com.db.model.Schedule;
import com.db.utils.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ScheduleService {
    @Autowired
    private ScheduleDao scheduleDao;

    @Autowired
    private SeatDao seatDao;

    public Iterable<Schedule> getSchedule() {
        return scheduleDao.findAll();
    }

    public Schedule addScheduleItem(Schedule scheduleItem) {
        return scheduleDao.save(scheduleItem);
    }

    public List<SchedulePeriod> getScheduleItemBetween(String startDate, String endDate) {
        List<SchedulePeriod> schedulePeriods = new ArrayList<>();
        List<Schedule> schedules = scheduleDao.findByDateGreaterThanEqualAndDateLessThanEqual(DateTime.convertStringToDate(startDate), DateTime.convertStringToDate(endDate));
        for (Schedule schedule : schedules) {
            schedulePeriods.add(new SchedulePeriod(DateTime.convertDateToString(schedule.getDate()), Collections.singletonList(seatDao.findSeatByIdEquals(schedule.getSeatId()))));
        }
        return schedulePeriods;
    }

    public List<Schedule> addScheduleItems(List<BookSeat> bookSeats) {
        List<Schedule> newScheduleItems = new ArrayList<>();
        for (BookSeat bookSeat : bookSeats) {
            Schedule currentSchedule = scheduleDao.findByDateEqualsAndUserIdEquals(bookSeat.getDate(), 1);
            if (currentSchedule != null){
                scheduleDao.delete(currentSchedule);
            }
            Schedule newScheduleItem = new Schedule();
            newScheduleItem.setDate(bookSeat.getDate());
            newScheduleItem.setSeatId(bookSeat.getSeatId());
            newScheduleItem.setUserId(1); // TODO: Где хранить userId ?
            newScheduleItems.add(newScheduleItem);
        }
        scheduleDao.save(newScheduleItems);
        return newScheduleItems;
    }

    public void deleteSchedule(String date) {
        Schedule schedule = scheduleDao.findByDateEqualsAndUserIdEquals(DateTime.convertStringToDate(date), 1); // TODO: Где хранить userId ?
        if (schedule != null) {
            scheduleDao.delete(schedule);
        }
    }
}
