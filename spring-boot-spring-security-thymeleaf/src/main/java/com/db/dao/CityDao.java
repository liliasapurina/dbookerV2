package com.db.dao;

import com.db.model.City;
import org.springframework.data.repository.CrudRepository;

public interface CityDao extends CrudRepository<City, Integer> {
}
