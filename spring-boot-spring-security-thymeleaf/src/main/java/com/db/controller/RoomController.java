package com.db.controller;

import com.db.dao.CityDao;
import com.db.dao.RoomDao;
import com.db.model.City;
import com.db.model.Room;
import com.db.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("room")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @GetMapping("cityId={cityId}")
    public Iterable<Room> getRooms(@PathVariable int cityId) {
        return roomService.getRooms(cityId);
    }

    @GetMapping
    public Iterable<Room> getRooms() {
        return roomService.getRooms();
    }

    @PostMapping
    public Room addRoom(@RequestBody Room room) {
        return roomService.addRoom(room);
    }
}
