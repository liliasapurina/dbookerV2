package com.db.controller;

import com.db.data.BookSeat;
import com.db.data.SchedulePeriod;
import com.db.model.Schedule;
import com.db.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("schedule")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @GetMapping
    public Iterable<Schedule> getSchedule() {
        return scheduleService.getSchedule();
    }

    @PostMapping
    public Schedule addScheduleItem(@RequestBody Schedule scheduleItem) {
        return scheduleService.addScheduleItem(scheduleItem);
    }

    @GetMapping("between")
    public List<SchedulePeriod> getScheduleItemBetween(@RequestParam("start") String startDate, @RequestParam("end") String endDate) {
        return scheduleService.getScheduleItemBetween(startDate, endDate);
    }

    @PostMapping("between")
    public List<Schedule> addScheduleItems(@RequestBody List<BookSeat> bookSeats) {
        return scheduleService.addScheduleItems(bookSeats);
    }

    @DeleteMapping("unbook")
    public void deleteSchedule(@RequestBody String date) {
        scheduleService.deleteSchedule(date);
    }
}
