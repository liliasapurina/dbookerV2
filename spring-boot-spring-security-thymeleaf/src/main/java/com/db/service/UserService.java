package com.db.service;

import com.db.dao.UserDao;
import com.db.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public Iterable<User> getTeam() {
        return userDao.findAll();
    }

    public User addUser(User user) {
        return userDao.save(user);
    }
}
