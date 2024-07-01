package com.example.BookMyShow.exceptions;

import com.example.BookMyShow.repositories.ShowRepository;

public class ShowNotFoundException extends Exception{
    public ShowNotFoundException(String msg){
        super(msg);
    }
}
