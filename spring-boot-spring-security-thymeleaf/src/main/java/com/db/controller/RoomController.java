package com.db.controller;

import com.db.dao.RoomDao;
import com.db.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("room")
public class RoomController {
    @Autowired
    private RoomDao roomDao;

    @GetMapping
    public Iterable<Room> getRooms() {
        return roomDao.findAll();
    }

    @PostMapping
    public Room addRoom(@RequestBody Room room) {
        return roomDao.save(room);
    }
}
