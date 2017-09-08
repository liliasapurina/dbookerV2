package com.db.controller;

import com.db.data.SchedulePeriod;
import com.db.model.Seat;
import com.db.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("seat")
public class SeatController {
    @Autowired
    private SeatService seatService;

    @GetMapping
    public Iterable<Seat> getSeats() {
        return seatService.getSeats();
    }

    @GetMapping("roomId={roomId}")
    public Iterable<Seat> getRooms(@PathVariable int roomId) {
        return seatService.getRooms(roomId);
    }

    @PostMapping
    public Seat addSeat(@RequestBody Seat seat) {
        return seatService.addSeat(seat);
    }

    @PutMapping("update")
    public Seat updateSeatStatus(@RequestBody Seat seat) {
        return seatService.updateSeatStatus(seat);
    }

    @GetMapping("available")
    public List<SchedulePeriod> getAvailableSeatsBetween(@RequestParam("start") String startDate, @RequestParam("end") String endDate) {
        return seatService.getAvailableSeatsBetween(startDate, endDate);
    }
}
