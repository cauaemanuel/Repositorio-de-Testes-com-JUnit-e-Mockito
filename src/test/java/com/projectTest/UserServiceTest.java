package com.projectTest;

import java.util.List;
import java.util.Optional;

import com.projectTest.exceptions.DataIntegratyViolationException;
import com.projectTest.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.projectTest.DTO.UserDTO;
import com.projectTest.DTO.UserMapper;
import com.projectTest.domain.User;
import com.projectTest.repository.UserRepository;
import com.projectTest.service.UserService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceTest {
	
	@InjectMocks
	private UserService service;
	
	@Mock
	private UserMapper mapper;
	
	@Mock
	private UserRepository repository;
	
	private User user;
	
	private UserDTO userDto;
	
	private Optional<User> userOptional;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		startUser();
	}
	
	@Test
	void whenFindByIdThenReturnAnUserInstance() {
		when(repository.findById(anyInt())).thenReturn(userOptional);
		
		User response = service.findById(1);
		assertNotNull(response);
		assertEquals(User.class, response.getClass());
	    assertEquals(1, response.getId());
	}

	@Test
	void whenFindByIdThenReturnAnObjectNotFoundException(){

		when(repository.findById(anyInt())).thenThrow(new ObjectNotFoundException("Objeto nao encontrado"));

		try{
			service.findById(1);
		}catch(Exception ex){
			assertEquals(ObjectNotFoundException.class, ex.getClass());
			assertEquals("Objeto nao encontrado", ex.getMessage());
		}
	}
	
	@Test
	void whenFindAllThenReturnAnListOfUsers() {
		when(repository.findAll()).thenReturn(List.of(user));
		List<User> response = service.findAll();

		assertNotNull(response);
		assertEquals(1, response.size());
		assertEquals(User.class, response.get(0).getClass());
	}
	
	@Test
	void whenCreateThenReturnSucess() {
		when(repository.save(any())).thenReturn(user);

		User response = service.create(user);

		assertNotNull(response);
		assertEquals(user.getId(), response.getId());
	}

	@Test
	void whenCreateThenReturnDataIntegratyViolationException() {
		when(repository.findByEmail(anyString())).thenReturn(userOptional);


		try {
			service.create(user);
		}catch (Exception ex){
			assertEquals(DataIntegratyViolationException.class, ex.getClass());
		}

	}

	@Test
	void whenUpdateThenReturnSucess() {
		when(repository.save(any())).thenReturn(user);

		User response = service.update(userDto);

		assertNotNull(response);
		assertEquals(user.getId(), response.getId());
	}

	@Test
	void whenUpdateThenReturnDataIntegratyViolationException() {
		when(repository.findByEmail(anyString())).thenReturn(userOptional);


		try {
			service.create(user);
		}catch (Exception ex){
			assertEquals(DataIntegratyViolationException.class, ex.getClass());
		}

	}
	
	@Test
	void deleteWithSucess() {
		when(repository.findById(anyInt())).thenReturn(userOptional);
		doNothing().when(repository).deleteById(anyInt());

		service.delete(1);

		verify(repository, times(1)).deleteById(anyInt());
	}

	@Test
	void deleteWithObjectNotFoundException(){
		when(repository.findById(anyInt())).thenThrow(new ObjectNotFoundException("Objeto nao encontrado"));
		try{
			service.delete(1);
		}catch(Exception e){
			assertEquals(ObjectNotFoundException.class, e.getClass());
		}
	}
	
	private void startUser() {
		user = new User(1, "valdir", "valdir@gmail.com", "123");
	    userDto = new UserDTO(1, "valdir", "valdir@gmail.com", "123");
	    userOptional = Optional.of(new User(1, "valdir", "valdir@gmail.com", "123"));
	}
}
