package com.example.BookMyShow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ShowSeatType extends  BaseModel{
    @ManyToOne
    private Show show;
    @Enumerated(EnumType.ORDINAL)
    private SeatType seatType;
    int price;
}


/*

 all mapping class will have many is to one cardinality

 1                               1
 showseattype                    show
 M                                 1

 */
