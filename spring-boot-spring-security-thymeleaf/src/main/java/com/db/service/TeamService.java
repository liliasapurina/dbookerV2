package com.db.service;

import com.db.dao.TeamDao;
import com.db.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class TeamService {
    @Autowired
    private TeamDao teamDao;

    public Iterable<Team> getTeams() {
        return teamDao.findAll();
    }

    public Team addTeam(@RequestBody Team team) {
        return teamDao.save(team);
    }
}
