package com.example.BookMyShow.dtos;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;

@Getter
@Setter
public class UserLoginResponseDto {
    private Long userId;
    private ResponseStatus responseStatus;
}
