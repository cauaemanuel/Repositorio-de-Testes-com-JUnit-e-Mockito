package com.projectTest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectTest.DTO.UserDTO;
import com.projectTest.DTO.UserMapper;
import com.projectTest.domain.User;
import com.projectTest.exceptions.DataIntegratyViolationException;
import com.projectTest.exceptions.ObjectNotFoundException;
import com.projectTest.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private UserMapper mapper;
	
	public User findById(Integer id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado"));
	}
	
	public List<User> findAll(){
		return repository.findAll();
	}
	
	public User create(User obj) {
		findByEmail(obj);
		return repository.save(obj);
	}
	
	public void findByEmail(User obj) {
		Optional<User> user = repository.findByEmail(obj.getEmail());

		if(user.isPresent() && !user.get().getId().equals(obj.getId())) {
			throw new DataIntegratyViolationException("E-mail ja Cadastrado no Sistema");
		}
	}
	
	public User update(UserDTO obj) {
		
		User objUser = mapper.userDtoToUser(obj);
		findByEmail(objUser);
		return repository.save(objUser);
	}
	
	public void delete(Integer id) {
		findById(id);
		repository.deleteById(id);
	}
}
