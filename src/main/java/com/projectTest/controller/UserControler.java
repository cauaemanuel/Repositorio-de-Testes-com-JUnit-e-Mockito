package com.projectTest.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.projectTest.DTO.UserDTO;
import com.projectTest.DTO.UserMapper;
import com.projectTest.domain.User;
import com.projectTest.service.UserService;

@RestController
@RequestMapping("/user")
public class UserControler {
	
	@Autowired
	private UserMapper mapper;
	
	@Autowired
	private UserService service;

	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable Integer id){
		return ResponseEntity.ok().body(mapper.userToUserDTO(service.findById(id)));
	}
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll(){
		List<UserDTO> listdto =  service.findAll().stream().map(x -> mapper.userToUserDTO(x)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listdto);
	}
	
	@PostMapping
	public ResponseEntity<UserDTO> create(@RequestBody UserDTO obj){
		
		User newobj = service.create(mapper.userDtoToUser(obj));
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newobj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UserDTO> update(@PathVariable Integer id, @RequestBody UserDTO obj){
		obj.setId(id);
		
		User newUser = service.update(obj);
				
		return ResponseEntity.ok().body(mapper.userToUserDTO(newUser));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<UserDTO> delete(@PathVariable Integer id){
		
		service.delete(id);
		
		return ResponseEntity.noContent().build();
	}
}
