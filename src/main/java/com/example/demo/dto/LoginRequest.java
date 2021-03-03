package com.example.demo.dto;

public class LoginRequest {
	
	private String username;
	private String password;
	
	public LoginRequest() {

	}
	
	public LoginRequest(String userName, String password) {
		
		this.username = userName;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String userName) {
		this.username = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
