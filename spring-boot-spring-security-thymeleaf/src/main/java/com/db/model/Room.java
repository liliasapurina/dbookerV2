package com.db.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue
    @Column(name="room_id")
    private int id;
    private String name;

    @ManyToOne
    @JsonIgnore
    private City city;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "room")
    private List<Seat> seats = new ArrayList<>();

    public void addSeat(Seat seat) {
        seat.setRoom(this);
        seats.add(seat);
    }
}