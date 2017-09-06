package com.db.controller;

import com.db.dao.ScheduleDao;
import com.db.dao.SeatDao;
import com.db.data.BookSeat;
import com.db.data.SchedulePeriod;
import com.db.model.Schedule;
import com.db.utils.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("schedule")
public class ScheduleController {
    @Autowired
    private ScheduleDao scheduleDao;

    @Autowired
    private SeatDao seatDao;

    @GetMapping
    public Iterable<Schedule> getSchedule() {
        return scheduleDao.findAll();
    }

    @PostMapping
    public Schedule addScheduleItem(@RequestBody Schedule scheduleItem) {
        return scheduleDao.save(scheduleItem);
    }

    @GetMapping("between")
    public List<SchedulePeriod> getScheduleItemBetween(@RequestParam("start") String startDate, @RequestParam("end") String endDate) {
        List<SchedulePeriod> schedulePeriods = new ArrayList<>();
        List<Schedule> schedules = scheduleDao.findByDateGreaterThanEqualAndDateLessThanEqual(DateTime.convertStringToDate(startDate), DateTime.convertStringToDate(endDate));
        for (Schedule schedule : schedules) {
            schedulePeriods.add(new SchedulePeriod(DateTime.convertDateToString(schedule.getDate()), Collections.singletonList(seatDao.findSeatByIdEquals(schedule.getSeatId()))));
        }
        return schedulePeriods;
    }

    @PostMapping("between")
    public List<Schedule> addScheduleItems(@RequestBody List<BookSeat> bookSeats) {
        List<Schedule> newScheduleItems = new ArrayList<>();
        for (BookSeat bookSeat : bookSeats) {
            Schedule scheduleItem = new Schedule();
            scheduleItem.setDate(bookSeat.getDate());
            scheduleItem.setSeatId(bookSeat.getSeatId());
            scheduleItem.setUserId(1); // TODO: Где хранить userId ?
            newScheduleItems.add(scheduleItem);
        }
        scheduleDao.save(newScheduleItems);
        return newScheduleItems;
    }
}
