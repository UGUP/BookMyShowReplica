package com.example.BookMyShow.models;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="seats")
public class Seat extends  BaseModel{
    private int rowNum;
    private int colNum;
    private String number;
    @Enumerated(EnumType.ORDINAL)
    private SeatType seatType;
}
