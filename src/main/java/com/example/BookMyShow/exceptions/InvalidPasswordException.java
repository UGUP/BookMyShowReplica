package com.example.BookMyShow.exceptions;

public class InvalidPasswordException extends Exception{
    public InvalidPasswordException(String msg){
        super(msg);
    }
}
