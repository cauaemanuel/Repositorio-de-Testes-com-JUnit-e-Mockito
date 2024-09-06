package com.projectTest;

import com.projectTest.DTO.UserDTO;
import com.projectTest.DTO.UserMapper;
import com.projectTest.controller.UserControler;
import com.projectTest.domain.User;
import com.projectTest.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @InjectMocks
    private UserControler controller;

    @Mock
    private UserService service;

    @Mock
    private UserMapper mapper;

    private User user;

    private UserDTO userDto;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        startUser();
    }


    @Test
    void whenFindByIdThenReturnSucess(){
        when(service.findById(anyInt())).thenReturn(user);
        when(mapper.userToUserDTO(any())).thenReturn(userDto);

        ResponseEntity<UserDTO> response = controller.findById(1);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());

        assertEquals(UserDTO.class, response.getBody().getClass());
        assertEquals(userDto.getId(), response.getBody().getId());
    }

    @Test
    void whenFindAllThenReturnAListOfUserDTO(){
        when(service.findAll()).thenReturn(List.of(user));
        when(mapper.userToUserDTO(any())).thenReturn(userDto);

        ResponseEntity<List<UserDTO>> response = controller.findAll();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ArrayList.class, response.getBody().getClass());
        assertEquals(UserDTO.class, response.getBody().get(0).getClass());
    }

    @Test
    void whenCreateTheReturnCreated(){
        when(service.create(any())).thenReturn(user);

        ResponseEntity<UserDTO> response = controller.create(userDto);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void whenUpdateTheReturnSucess(){
        when(service.update(userDto)).thenReturn(user);
        when(mapper.userToUserDTO(any())).thenReturn(userDto);

        ResponseEntity<UserDTO> response = controller.update(1, userDto);
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(UserDTO.class, response.getBody().getClass());
    }

    @Test
    void whenDeleteThenReturnSucess(){

        doNothing().when(service).delete(anyInt());

        ResponseEntity<UserDTO> response = controller.delete(1);
        assertNotNull(response);
        verify(service, times(1)).delete(anyInt());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    private void startUser(){
        user = new User(1, "valdir", "valdir@gmail.com", "123");
        userDto = new UserDTO(1, "valdir", "valdir@gmail.com", "123");
    }
}
