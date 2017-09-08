package com.db.service;

import com.db.dao.RoomDao;
import com.db.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {
    @Autowired
    private RoomDao roomDao;

    public Iterable<Room> getRooms(int cityId) {
        return roomDao.findAllByCityIdEquals(cityId);
    }

    public Iterable<Room> getRooms() {
        return roomDao.findAll();
    }

    public Room addRoom(Room room) {
        return roomDao.save(room);
    }
}
