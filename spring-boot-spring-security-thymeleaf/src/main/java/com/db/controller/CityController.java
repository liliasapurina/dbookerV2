package com.db.controller;

import com.db.dao.CityDao;
import com.db.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("city")
public class CityController {
    @Autowired
    private CityDao cityDao;

    @GetMapping
    public Iterable<City> getCities() {
        return cityDao.findAll();
    }

    @PostMapping
    public City addCity(@RequestBody City city) {
        return cityDao.save(city);
    }
}
