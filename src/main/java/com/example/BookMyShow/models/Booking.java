package com.example.BookMyShow.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Booking extends BaseModel{
    @ManyToOne
    private User user;
    @ManyToOne
    private Show show;
    @ManyToMany
    private List<ShowSeat> showSeats;
    private int amount;
    @OneToMany
    private List<Payment> payments;
    @Enumerated(EnumType.ORDINAL)
    private  BookingStatus bookingStatus;
}

/*
1                          1
Booking                    user
M                           1
1                   1
Booking          show      => ManyToOne  many --> left for teh class
M                  1

1                 M
Booking          showSeats
M                  1

1                   M
Booking            Payments
1                   1
 */
