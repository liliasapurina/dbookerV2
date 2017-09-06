package com.db.dao;

import com.db.model.Room;
import org.springframework.data.repository.CrudRepository;

public interface RoomDao  extends CrudRepository<Room, Integer> {
}
