package com.example.BookMyShow.controllers;

import com.example.BookMyShow.dtos.CreateBookingRequestDto;
import com.example.BookMyShow.dtos.CreateBookingResponseDto;
import com.example.BookMyShow.dtos.ResponseStatus;
import com.example.BookMyShow.models.Booking;
import com.example.BookMyShow.services.BookingService;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;

@Controller
public class BookingController {

    private BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public CreateBookingResponseDto createBooking(CreateBookingRequestDto requestDto) {
        CreateBookingResponseDto responseDto = new CreateBookingResponseDto();

        try {
            Booking booking = bookingService.createBooking(requestDto.getShowId(), requestDto.getUserId(), requestDto.getShowSeatIds());
            responseDto.setBookingId(booking.getId());
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            responseDto.setResponseStatus(ResponseStatus.FAILED);
        }

        return responseDto;

    }

}