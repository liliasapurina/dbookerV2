package com.db.controller;

import com.db.dao.TeamDao;
import com.db.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by lilia on 05.09.17.
 */
@RestController
@RequestMapping("team")
public class TeamController {
    @Autowired
    private TeamDao teamDao;

    @GetMapping
    public Iterable<Team> getTeams() {
        return teamDao.findAll();
    }

    @PostMapping
    public Team addTeam(@RequestBody Team team) {
        return teamDao.save(team);
    }
}