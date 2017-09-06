package com.db.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class City {
    @Id
    @GeneratedValue
    @Column(name="city_id")
    private int id;
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "city")
    private List<Room> rooms = new ArrayList<>();

    public void addRoom(Room room) {
        room.setCity(this);
        rooms.add(room);
    }
}
