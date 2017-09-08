package com.db.controller;

import com.db.dao.UserDao;
import com.db.model.User;
import com.db.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public Iterable<User> getTeam() {
        return userService.getTeam();
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }
}
