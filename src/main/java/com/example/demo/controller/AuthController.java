package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.security.JwtProvider;
import com.example.demo.service.AuthService;

@RestController
@RequestMapping("/api/auth")
//@CrossOrigin(origins ="http://localhost:4200",allowedHeaders = "*")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private JwtProvider jwt;
	
	@PostMapping("/signup")
	public ResponseEntity signup(@RequestBody RegisterRequest registerRequest) {
		
		authService.signup(registerRequest);
		
		return new ResponseEntity(HttpStatus.OK);
		
	}
	
	@PostMapping("/login")
	public String loginUser(@RequestBody LoginRequest loginRequest){
		
		return authService.login(loginRequest);
		
	}
}
