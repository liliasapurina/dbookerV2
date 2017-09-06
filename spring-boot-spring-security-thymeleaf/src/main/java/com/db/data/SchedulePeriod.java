package com.db.data;

import com.db.model.Seat;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SchedulePeriod {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String date;
    private List<Seat> seats;
}
