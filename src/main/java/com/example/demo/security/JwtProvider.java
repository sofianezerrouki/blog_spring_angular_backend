package com.example.demo.security;

import java.io.IOException;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.annotation.PostConstruct;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.example.demo.exception.SpringBlogException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class JwtProvider  {
	
	private KeyStore keyStore;
	
	@PostConstruct
	private void ini() {
		
		try {
			keyStore = KeyStore.getInstance("JKS"); 
			InputStream stream =  getClass().getResourceAsStream("springblog.jks");
			keyStore.load(stream,"adminadmin".toCharArray());
		} catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException e) {
			
			throw new SpringBlogException("Exception occured while loadin keyStore :( ");
			
		}

	}
	
	public String generateToken(Authentication authentification) {
		
		User principal=(User) authentification.getPrincipal();
		
		return Jwts.builder()
					.setSubject(principal.getUsername())
					.signWith(getPrivateKey())
					.compact();
	}
	
	private Key getPrivateKey() {
		
		try {
			return (PrivateKey) keyStore.getKey("springblog","adminadmin".toCharArray());
		} catch (UnrecoverableKeyException | KeyStoreException |NoSuchAlgorithmException e) {

			throw new SpringBlogException("Exception occured while retreivin public key from keyStore ! ");
			
		}
		
	}

	public boolean validateJwt(String token) {
		Jwts.parser().setSigningKey(getPublicKey()).parseClaimsJws(token);
		
		return true;
	}

	private PublicKey getPublicKey() {

		try {
			return keyStore.getCertificate("springblog").getPublicKey();
		} catch (KeyStoreException e) {

			throw new SpringBlogException("Exception occured while retreivin public key from keyStore ! ");

		}
	}

	public String getUserNameFromJwt(String token) {
		Claims claims = Jwts.parser()
					.setSigningKey(getPublicKey())
					.parseClaimsJwt(token)
					.getBody();
		return claims.getSubject();
	}
}
