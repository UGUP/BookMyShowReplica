package com.example.BookMyShow.controllers;

import com.example.BookMyShow.dtos.*;
import com.example.BookMyShow.exceptions.InvalidPasswordException;
import com.example.BookMyShow.exceptions.UserAleadyExistsException;
import com.example.BookMyShow.exceptions.UserNotFoundException;
import com.example.BookMyShow.models.User;
import com.example.BookMyShow.services.UserService;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService=userService;
    }


    public UserSignupResponseDto signUp(UserSignupRequestDto requestDto){
        UserSignupResponseDto responseDto= new UserSignupResponseDto();
        try {
          User user=  userService.signUp(requestDto.getUsername(), requestDto.getEmail(), requestDto.getPassword());
           responseDto.setUserId(user.getId());
           responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }catch (UserAleadyExistsException ex){
            responseDto.setResponseStatus(ResponseStatus.FAILED);
            System.out.println("The password is invalid");
        }
        return  responseDto;
    }

    public  UserLoginResponseDto login(UserLoginRequestDto requestDto){
        UserLoginResponseDto responseDto= new UserLoginResponseDto();
        try{
            User user = userService.login(requestDto.getEmail(),requestDto.getPassword());
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
            responseDto.setUserId(user.getId());
        }catch (UserNotFoundException ex){
            responseDto.setResponseStatus(ResponseStatus.FAILED);
            System.out.println("User not found exception");
        }
        catch (InvalidPasswordException exe){
            responseDto.setResponseStatus(ResponseStatus.FAILED);
            System.out.println("INVALID PASSWORD");
        }catch(Exception exe){
            responseDto.setResponseStatus(ResponseStatus.FAILED);
            System.out.println(" Unknown exception has occured");
        }

        return responseDto;
    }

}
