package com.example.BookMyShow.services;

import com.example.BookMyShow.exceptions.NoShowSeatTypeFound;
import com.example.BookMyShow.models.SeatType;
import com.example.BookMyShow.models.Show;
import com.example.BookMyShow.models.ShowSeat;
import com.example.BookMyShow.models.ShowSeatType;
import com.example.BookMyShow.repositories.ShowSeatRepository;
import com.example.BookMyShow.repositories.ShowSeatTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PriceCalculatorService {

    private ShowSeatTypeRepository showSeatTypeRepository;

    public PriceCalculatorService(ShowSeatTypeRepository showSeatTypeRepository){
        this.showSeatTypeRepository=showSeatTypeRepository;
    }
    public int calculatePrice(Show show, List<ShowSeat> showSeats) throws NoShowSeatTypeFound {
        int totalPrice=0;
        List<ShowSeatType> showSeatTypes= showSeatTypeRepository.findAllByShow(show);
        for (ShowSeat showSeat : showSeats) { // 10
            for (ShowSeatType showSeatType : showSeatTypes) { // 3
                if (showSeat.getSeat().getSeatType().equals(showSeatType.getSeatType())) {
                    totalPrice += showSeatType.getPrice();
                    break;
                }
            }
        }
        return totalPrice;

    }
}
