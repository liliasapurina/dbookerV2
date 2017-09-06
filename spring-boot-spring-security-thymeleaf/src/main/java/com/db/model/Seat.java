package com.db.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Seat {
    @Id
    @GeneratedValue
    private int id;
    private String name;

    @ManyToOne
    @JsonIgnore
    private Room room;
}