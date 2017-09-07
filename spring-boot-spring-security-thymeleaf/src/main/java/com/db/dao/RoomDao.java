package com.db.dao;

import com.db.model.City;
import com.db.model.Room;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoomDao  extends CrudRepository<Room, Integer> {
    List<Room> findAllByCityIdEquals(int cityId);
}
