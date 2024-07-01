package com.example.BookMyShow.services;


import com.example.BookMyShow.exceptions.InvalidPasswordException;
import com.example.BookMyShow.exceptions.UserAleadyExistsException;
import com.example.BookMyShow.exceptions.UserNotFoundException;
import com.example.BookMyShow.models.User;
import com.example.BookMyShow.repositories.UserRepository;
import jakarta.el.ELManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public User signUp(String username,String email, String password) throws UserAleadyExistsException {
        Optional<User> optionalUser= userRepository.findByEmail(email);
        if(optionalUser.isPresent()){
            throw new UserAleadyExistsException("User already exits with the email "+email);
        }
        User user= new User();
        user.setName(username);
        user.setEmail(email);
        BCryptPasswordEncoder bCryptPasswordEncoder= new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(password));
        userRepository.save(user);
        return user;
    }

    public User  login(String email,String password) throws UserNotFoundException, InvalidPasswordException {
        Optional<User> optionalUser= userRepository.findByEmail(email);
        if(optionalUser.isEmpty()){
            throw new UserNotFoundException(" unable to find user with email"+email);
        }
        User actualUser= optionalUser.get();
        BCryptPasswordEncoder bCryptPasswordEncoder= new BCryptPasswordEncoder();
        if(bCryptPasswordEncoder.matches(password,actualUser.getPassword())){
            return actualUser;
        }else{
            throw new InvalidPasswordException("The entered password is invalid");
        }
    }
}
