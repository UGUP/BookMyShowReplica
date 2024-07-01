package com.example.BookMyShow.services;

import com.example.BookMyShow.exceptions.NoShowSeatTypeFound;
import com.example.BookMyShow.models.SeatType;
import com.example.BookMyShow.models.ShowSeat;
import com.example.BookMyShow.models.ShowSeatType;
import com.example.BookMyShow.repositories.ShowSeatTypeRepository;

import java.util.List;
import java.util.Optional;

public class PriceCalculatorService {

    private ShowSeatTypeRepository showSeatTypeRepository;

    public PriceCalculatorService(ShowSeatTypeRepository showSeatTypeRepository){
        this.showSeatTypeRepository=showSeatTypeRepository;
    }
    public int calculatePrice(List<ShowSeat> showSeats) throws NoShowSeatTypeFound {
        int totalPrice=0;

        for(int i=0;i<showSeats.size();i++){
            Long showId= showSeats.get(i).getShow().getId();
             Optional<ShowSeatType> optionalShowSeatType=showSeatTypeRepository.findById(showId);
             if(optionalShowSeatType.isEmpty()){
                 throw new NoShowSeatTypeFound(" No show seat type object found for the show");
             }
             ShowSeatType showSeatType= optionalShowSeatType.get();
             totalPrice=totalPrice+showSeatType.getPrice();
        }
        return totalPrice;

    }
}
