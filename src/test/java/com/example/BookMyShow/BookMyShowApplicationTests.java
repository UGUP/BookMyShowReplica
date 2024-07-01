package com.example.BookMyShow;

import com.example.BookMyShow.controllers.UserController;
import com.example.BookMyShow.dtos.UserLoginRequestDto;
import com.example.BookMyShow.dtos.UserLoginResponseDto;
import com.example.BookMyShow.dtos.UserSignupRequestDto;
import com.example.BookMyShow.dtos.UserSignupResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookMyShowApplicationTests {

	@Autowired
	private UserController userController;

	@Test
	void contextLoads() {
	}

	@Test
	void testSignupFunctionality(){
		UserSignupRequestDto requestDto= new UserSignupRequestDto();
		requestDto.setUsername("monalisa");
		requestDto.setEmail("monalisa@gmail.com");
		requestDto.setPassword("monalisa");
		UserSignupResponseDto responseDto= userController.signUp(requestDto);
		System.out.println(responseDto.getUserId());
		System.out.println(responseDto.getResponseStatus());
	}

	@Test
	void testLoginFunctionality(){
		UserLoginRequestDto requestDto= new UserLoginRequestDto();
		requestDto.setEmail("monalisa@gmail.com");
		requestDto.setPassword("monalisa");
		UserLoginResponseDto responseDto= userController.login(requestDto);
		System.out.println(responseDto.getResponseStatus());

	}

}
