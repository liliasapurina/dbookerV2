package com.db.service;

import com.db.dao.CityDao;
import com.db.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class CityService {
    @Autowired
    private CityDao cityDao;

    public Iterable<City> getCities() {
        return cityDao.findAll();
    }

    public City addCity(City city) {
        return cityDao.save(city);
    }
}
