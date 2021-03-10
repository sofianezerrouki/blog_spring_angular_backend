package com.example.demo.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthontificationResponse {
	private String authontificationToken;
	private String username;
	public AuthontificationResponse(String authontificationToken, String username) {
		super();
		this.authontificationToken = authontificationToken;
		this.username = username;
	}
	
}
