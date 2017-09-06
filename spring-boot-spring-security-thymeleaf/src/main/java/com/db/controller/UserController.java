package com.db.controller;

import com.db.dao.UserDao;
import com.db.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserDao userDao;

    @GetMapping
    public Iterable<User> getTeam() {
        return userDao.findAll();
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        return userDao.save(user);
    }
}
