package com.example.BookMyShow.exceptions;

public class UserAleadyExistsException extends Exception{
    public UserAleadyExistsException(String msg){
        super(msg);
    }
}
