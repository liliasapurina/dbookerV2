package com.db.dao;

import com.db.model.Team;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by lilia on 05.09.17.
 */
public interface TeamDao extends CrudRepository<Team, Integer> {
    Team findByName(String name);
}
