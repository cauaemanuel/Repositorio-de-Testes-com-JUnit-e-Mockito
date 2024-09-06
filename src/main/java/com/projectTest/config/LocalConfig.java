package com.projectTest.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.projectTest.domain.User;
import com.projectTest.repository.UserRepository;

import jakarta.annotation.PostConstruct;

@Component
public class LocalConfig {

	@Autowired
	private UserRepository repository;
	
	@PostConstruct
	public void startDB() {
	   User u1 = new User(null, "caua", "emanuelcaua@gmail.com", "123");
	   User u2 = new User(null, "ryan", "ryan@gmail.com", "123");
	   
	   repository.saveAll(List.of(u1, u2));

	}
	
	
}
