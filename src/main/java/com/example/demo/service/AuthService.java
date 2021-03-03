package com.example.demo.service;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepo;
import com.example.demo.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
	
	@Autowired
	private UserRepo myRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	public void signup(RegisterRequest register) {
		
		User user = new User();
		
		user.setUserName(register.getUsername());
		user.setEmail(register.getEmail());
		user.setPassword(
				encodePassword(register.getPassword())
		);
		
		myRepo.save(user);
		
	}
	
	private String encodePassword(String password) {
		
		return passwordEncoder.encode(password);
		
	}

	public String login(LoginRequest loginRequest) {

		Authentication authentificate = 
				authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
															loginRequest.getUsername(),
															loginRequest.getPassword())
										   );
		SecurityContextHolder.getContext().setAuthentication(authentificate);
		
		return jwtProvider.generateToken(authentificate);
		
	}
	
}
