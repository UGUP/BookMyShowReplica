package com.example.BookMyShow.services;

import com.example.BookMyShow.exceptions.NoShowSeatTypeFound;
import com.example.BookMyShow.exceptions.ShowNotFoundException;
import com.example.BookMyShow.exceptions.ShowSeatNotFound;
import com.example.BookMyShow.exceptions.UserNotFoundException;
import com.example.BookMyShow.models.*;
import com.example.BookMyShow.repositories.BookingRepository;
import com.example.BookMyShow.repositories.ShowRepository;
import com.example.BookMyShow.repositories.ShowSeatRepository;
import com.example.BookMyShow.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    private UserRepository userRepository;
    private ShowRepository showRepository;
    private ShowSeatRepository showSeatRepository;
    private PriceCalculatorService priceCalculatorService;
    private BookingRepository bookingRepository;

    public BookingService(UserRepository userRepository,ShowRepository showRepository,ShowSeatRepository showSeatRepository,
                          PriceCalculatorService priceCalculatorService,BookingRepository bookingRepository){
        this.userRepository=userRepository;
        this.showRepository=showRepository;
        this.priceCalculatorService=priceCalculatorService;
        this.bookingRepository=bookingRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking createBooking(Long showId, Long userId, List<Long> showSeatIds) throws UserNotFoundException, ShowNotFoundException, ShowSeatNotFound, NoShowSeatTypeFound {
        Optional<User> optionalUser= userRepository.findById(userId);
        if(optionalUser.isEmpty()){
            throw new UserNotFoundException("The user id is not found");
        }
        User user= optionalUser.get();
        Optional<Show> optionalShow= showRepository.findById(showId);
        if(optionalShow.isEmpty()){
            throw new ShowNotFoundException(" Unable to find the given show");
        }
        Show show= optionalShow.get();

        List<ShowSeat> seatsRequested= showSeatRepository.findAllById(showSeatIds);

        if(optionalUser.isEmpty()){
            throw new ShowSeatNotFound(" Unable to find teh show seat id");
        }


        for(ShowSeat showseat:seatsRequested){
            if(!showseat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE)){
              throw new ShowNotFoundException("Show seat not found exception");
            }
        }

        for(ShowSeat showSeat:seatsRequested){
            showSeat.setShowSeatStatus(ShowSeatStatus.BLOCKED);
            showSeatRepository.save(showSeat);
        }

        Booking newBooking= new Booking();
        int amount= priceCalculatorService.calculatePrice(seatsRequested);
        newBooking.setBookingStatus(BookingStatus.PENDING);
        newBooking.setShow(show);
        newBooking.setUser(user);
        newBooking.setAmount(amount);
        newBooking.setLastUpdatedAt(new Date());
        newBooking.setCreatedAt(new Date());
        newBooking.setShowSeats(seatsRequested);
        bookingRepository.save(newBooking);
        return newBooking;
    }


}
