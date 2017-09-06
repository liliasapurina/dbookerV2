package com.db.data;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookSeat {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private int seatId;
}
