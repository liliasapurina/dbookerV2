package com.db.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 * Created by lilia on 05.09.17.
 */
@Entity
@Data
@NoArgsConstructor
public class Team {
    @Id
    @GeneratedValue
    private int id;
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<User> users = new ArrayList<>();
}
