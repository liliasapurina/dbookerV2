package com.db.model;

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
    private int roomId;
    private boolean available;
}
