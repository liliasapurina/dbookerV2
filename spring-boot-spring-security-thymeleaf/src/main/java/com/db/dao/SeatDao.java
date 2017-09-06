package com.db.dao;

import com.db.model.Seat;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface SeatDao extends CrudRepository<Seat, Integer> {
    Seat findSeatByIdEquals(Integer id);
    Set<Seat> findAll();
}
