package com.example.BookMyShow.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignupResponseDto {
    ResponseStatus responseStatus;
    Long userId;
}
