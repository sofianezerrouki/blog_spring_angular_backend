package com.example.demo.service;

//lombok don't work with me

public class AuthontificationResponse {
	private String authontificationToken;
	private String username;
	public AuthontificationResponse(String authontificationToken, String username) {
		super();
		this.authontificationToken = authontificationToken;
		this.username = username;
	}
	public String getAuthontificationToken() {
		return authontificationToken;
	}
	public void setAuthontificationToken(String authontificationToken) {
		this.authontificationToken = authontificationToken;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
