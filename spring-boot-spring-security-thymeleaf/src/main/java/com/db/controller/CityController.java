package com.db.controller;

import com.db.model.City;
import com.db.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("city")
public class CityController {
    @Autowired
    private CityService cityService;

    @GetMapping
    public Iterable<City> getCities() {
        return cityService.getCities();
    }

    @PostMapping
    public City addCity(@RequestBody City city) {
        return cityService.addCity(city);
    }
}
