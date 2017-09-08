package com.db.service;

import com.db.dao.ScheduleDao;
import com.db.dao.SeatDao;
import com.db.data.SchedulePeriod;
import com.db.model.Schedule;
import com.db.model.Seat;
import com.db.utils.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SeatService {
    @Autowired
    private SeatDao seatDao;

    @Autowired
    private ScheduleDao scheduleDao;

    public Iterable<Seat> getSeats() {
        return seatDao.findAll();
    }

    public Iterable<Seat> getRooms(int roomId) {
        return seatDao.findAllByRoomIdEquals(roomId);
    }

    public Seat addSeat(Seat seat) {
        return seatDao.save(seat);
    }

    public Seat updateSeatStatus(Seat seat) {
        return seatDao.save(seat);
    }

    public List<SchedulePeriod> getAvailableSeatsBetween(String startDate, String endDate) {
        List<SchedulePeriod> result = new ArrayList<>();
        Set<Integer> allSeats = seatDao.findAll().stream().filter(Seat::isAvailable).map(Seat::getId).collect(Collectors.toSet());
        Date start = DateTime.convertStringToDate(startDate);
        Date end = DateTime.convertStringToDate(endDate);
        for (Date cur = start; !cur.after(end); cur = DateTime.plusDay(cur)) {
            Set<Integer> curSeats = scheduleDao.findByDateBetween(cur, cur).stream().map(Schedule::getSeatId).collect(Collectors.toSet());
            HashSet<Integer> available = new HashSet<>(allSeats);
            available.removeAll(curSeats);
            List<Seat> availableSeats = available.stream().map(seatDao::findSeatByIdEquals).collect(Collectors.toList());
            result.add(new SchedulePeriod(DateTime.convertDateToString(cur), availableSeats));
        }
        return result;
    }
}
