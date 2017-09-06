package com.db.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

/**
 * Created by lilia on 05.09.17.
 */
@Entity
@Data
@NoArgsConstructor
@Table(name="\"user\"")
public class User {
    @Id
    @GeneratedValue
    private int id;

    private String name;

    private int status;

   /* @ManyToOne
    @JsonIgnore
    private Schedule schedule;*/
}
