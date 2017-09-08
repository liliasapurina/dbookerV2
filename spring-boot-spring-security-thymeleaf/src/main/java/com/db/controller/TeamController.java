package com.db.controller;

import com.db.dao.TeamDao;
import com.db.model.Team;
import com.db.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by lilia on 05.09.17.
 */
@RestController
@RequestMapping("team")
public class TeamController {
    @Autowired
    private TeamService teamService;

    @GetMapping
    public Iterable<Team> getTeams() {
        return teamService.getTeams();
    }

    @PostMapping
    public Team addTeam(@RequestBody Team team) {
        return teamService.addTeam(team);
    }
}